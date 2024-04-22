package edu.northeastern.demostructure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    String study = "studying";
    String exercise = "exercising";
    String date = "dating";
    String learnMagic = "learningMagic";
    String cooking = "cooking";
    int currentIndex;
    LinkedList<String> acts;
    Status status;
    boolean boy;
    ProgressBar respectProgressBar;
    ProgressBar energyProgressBar;
    Images images;
    ImageView imageView;
    WebView webView;
    int energy;
    Handler energyHandler;
    Runnable energyRunnable;
    Handler statusHandler;
    Runnable statusRunnable;
    String user;
    TextView usertv;
    String path;
    TextView pathtxt;
    DatabaseReference db ;
    boolean soundFlag;
     String pathFromFB;


    Runnable calculateRunnable = new Runnable() {
        @Override
        public void run() {
            status.calculate();
        }
    };

    int energyIncreaseInterval = 6000; // Interval in milliseconds for energy increase (e.g., 6 seconds)
    int energyIncreaseAmount = 10; // Amount of energy to increase each time


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        usertv = findViewById(R.id.user);
        respectProgressBar = findViewById(R.id.progressBar3);
        energyProgressBar = findViewById(R.id.progressBar2);
        webView = findViewById(R.id.image);

        Bundle bundle = getIntent().getExtras();

        user = bundle.getString("userName").toString().trim();
        usertv.setText(user);

        pathtxt = findViewById(R.id.path);
        //pathtxt.setMovementMethod(new ScrollingMovementMethod());
        soundFlag = bundle.getBoolean("sound");

        FirebaseApp.initializeApp(this);
        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://finalproj-c26a1-default-rtdb.firebaseio.com/");
        firebaseData();

        pathtxt.setText(pathFromFB);


        boy = getIntent().getBooleanExtra("boy", true);
        images = new Images(boy);
        status = new Status();
        imageView = findViewById(R.id.character);
        if(boy){
            imageView.setImageResource(R.drawable.male);
        }else {
            imageView.setImageResource(R.drawable.female_selector);
        }

        acts = new LinkedList<>();
        acts.add(exercise);
        acts.add(study);
        acts.add(date);
        acts.add(learnMagic);
        acts.add(cooking);

        currentIndex = 0;
        energy = 100;

        showEnergy();
        energyHandler = new Handler();
        energyRunnable = new Runnable() {
            @Override
            public void run() {
                energy += energyIncreaseAmount;
                if (energy > 100) {
                    energy = 100;
                }
                showEnergy();
                energyHandler.postDelayed(this, energyIncreaseInterval);
            }
        };
        energyHandler.postDelayed(energyRunnable, energyIncreaseInterval);

        showRespect();

        statusHandler = new Handler();
        statusRunnable = new Runnable() {
            @Override
            public void run() {
                boolean end = checkStatus();
                if(!end) {
                    statusHandler.postDelayed(this, 5000);
                }
            }
        };
        statusHandler.postDelayed(statusRunnable, 5000);

    }


    public void next(View view) {
        if(soundFlag == true){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.click1);
            mp.start();
        }
        currentIndex++;
        if(currentIndex >= acts.size()){
            currentIndex = 0;
        }
        showRespect();
    }

    public void showRespect(){
        respectProgressBar.setProgress(status.getStatus(currentIndex));
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(images.getEvents(currentIndex));
    }
    private void showEnergy(){
        energyProgressBar.setProgress(energy);
    }


    public void previous(View view) {
        if(soundFlag == true){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.click1);
            mp.start();
        }
        currentIndex--;
        if(currentIndex < 0 ){
            currentIndex = acts.size()-1;
        }
        showRespect();
    }

    public void addEvent(View view){
        if(soundFlag == true){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.work);
            mp.start();
        }
        if(energy >= 30) {
            String selection = acts.get(currentIndex);
            status.addMovement(selection);
            if(path == null || path.equals("")){
                path = "Path = "+ selection + " -> ";
                pathtxt.setText(path);
                db.child("User").child(user).child("path").setValue(path);
            } else {
                path += selection + " -> ";
                pathtxt.setText(path);
                db.child("User").child(user).child("path").setValue(path);
            }

            Log.i("%%%%%%%%%%%%%",path);
            //Toast.makeText(this, acts.get(currentIndex), Toast.LENGTH_SHORT).show();
            energy -= 30;
            showEnergy();
        }else {
            Toast.makeText(this, "nO EnrGy...@#$%", Toast.LENGTH_SHORT).show();
            path += "nO EnrGy...@#$%";
            pathtxt.setText(path);
        }
        new Thread(calculateRunnable).start();
    }

    private boolean checkStatus(){
        int num;
        num = status.checkStatus();
        if (num != 0){
            Intent intent = new Intent(this, EndActivity.class);
            db.child("User").child(user).child("path").setValue(path);
            intent.putExtra("image", num);
            intent.putExtra("boy", boy);
            intent.putExtra("soundFlag",soundFlag);
            startActivity(intent);
            return true;
        }
        return false;
    }

    public void firebaseData(){

        db.child("User").child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.child("path").getValue() != null){
                        pathFromFB = snapshot.child("path").getValue().toString().trim();
                        final String str = snapshot.child("path").getValue().toString().trim();
                        pathtxt.setText(str);
                    }

                   // pathtxt.setText(pathFromFB);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}