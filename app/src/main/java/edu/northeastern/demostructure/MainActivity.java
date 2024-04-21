package edu.northeastern.demostructure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean soundFlag;
    PopupMenu popupMenu;
    ImageView settingsImg;
    DatabaseReference db ;
    boolean boy;
    boolean fullSetUp = false;
    EditText username;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundFlag = true;
        FirebaseApp.initializeApp(this);

        settingsImg = findViewById(R.id.imageView5);
        username = findViewById(R.id.username);
        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://finalproj-c26a1-default-rtdb.firebaseio.com/");
        db.push().setValue("aditya");
    }


    public void startGame(View view) {
        user = username.getText().toString().trim();


        if(fullSetUp) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("boy", boy);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this,"Choose your character", Toast.LENGTH_LONG).show();
        }
    }

    public void maleCharacter(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        fullSetUp = true;
        boy = true;
    }

    public void femaleCharacter(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        fullSetUp = true;
        boy = false;
    }

    public void sound(View view) {
        if(soundFlag == true){
            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
            settingsImg.setImageResource(R.drawable.soundoff);
            soundFlag = false;
        } else if(soundFlag == false){
            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
            settingsImg.setImageResource(R.drawable.soundon);
            soundFlag = true;
        }
    }
}