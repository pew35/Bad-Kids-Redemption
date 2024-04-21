package edu.northeastern.demostructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

        currentIndex = 0;
        energy = 100;
        respectProgressBar = findViewById(R.id.progressBar3);
        energyProgressBar = findViewById(R.id.progressBar2);
        webView = findViewById(R.id.image);
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
        currentIndex--;
        if(currentIndex < 0 ){
            currentIndex = acts.size()-1;
        }
        showRespect();
    }

    public void addEvent(View view){
        if(energy >= 30) {
            status.addMovement(acts.get(currentIndex));
            Toast.makeText(this, acts.get(currentIndex), Toast.LENGTH_SHORT).show();
            energy -= 30;
            showEnergy();
        }else {
            Toast.makeText(this, "nO EnrGy...@#$%", Toast.LENGTH_SHORT).show();
        }
        new Thread(calculateRunnable).start();
    }

    private boolean checkStatus(){
        int num;
        num = status.checkStatus();
        if (num != 0){
            Intent intent = new Intent(this, EndActivity.class);
            intent.putExtra("image", num);
            intent.putExtra("boy", boy);
            startActivity(intent);
            return true;
        }
        return false;
    }

}