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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean soundFlag;
    PopupMenu popupMenu;
    ImageView settingsImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundFlag = true;

        settingsImg = findViewById(R.id.imageView5);
    }


    public void startGame(View view) {
        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
    }

    public void maleCharacter(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void femaleCharacter(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
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