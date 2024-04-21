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
    boolean boy;
    boolean fullSetUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundFlag = true;

        settingsImg = findViewById(R.id.imageView5);
    }


    public void startGame(View view) {
        if(fullSetUp) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("boy", boy);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this,"Choose your character", Toast.LENGTH_LONG).show();
        }
    }

    public void maleCharacter(View view) {
        fullSetUp = true;
        boy = true;
    }

    public void femaleCharacter(View view) {
        fullSetUp = true;
        boy = false;
    }

    public void sound(View view) {
        if(soundFlag){
            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
            settingsImg.setImageResource(R.drawable.soundoff);
            soundFlag = false;
        } else{
            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
            settingsImg.setImageResource(R.drawable.soundon);
            soundFlag = true;
        }
    }
}