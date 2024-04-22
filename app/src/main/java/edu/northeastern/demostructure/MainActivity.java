package edu.northeastern.demostructure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
    ImageView maleChar;
    ImageView femaleChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundFlag = true;
        FirebaseApp.initializeApp(this);

        settingsImg = findViewById(R.id.imageView5);
        username = findViewById(R.id.username);
        maleChar = findViewById(R.id.imageView2);
        femaleChar = findViewById(R.id.imageView4);
        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://finalproj-c26a1-default-rtdb.firebaseio.com/");
    }


    public void startGame(View view) {
        if(soundFlag == true){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.click1);
            mp.start();
        }
        user = username.getText().toString().trim();
        db.child("User").child("Name").setValue(user);
        if(boy ==true){
            db.child("User").child(user).child("character").setValue("boy");
        } else if(boy == false){
            db.child("User").child(user).child("character").setValue("girl");
        }

        if(fullSetUp) {
            Intent intent = new Intent(this, GameActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("userName",user);
            intent.putExtras(bundle);
            intent.putExtra("boy", boy);
            intent.putExtra("sound",soundFlag);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this,"Choose your character", Toast.LENGTH_LONG).show();
        }
    }

    public void maleCharacter(View view) {
        if(soundFlag == true){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.click1);
            mp.start();
        }
        maleChar.setImageResource(R.drawable.male_pressed);
        femaleChar.setImageResource(R.drawable.female);
        fullSetUp = true;
        boy = true;
        if(user != null){
            db.child("User").child(user).child("character").setValue("boy");
        } else{
            Toast.makeText(MainActivity.this,"Enter character name", Toast.LENGTH_LONG).show();
        }
    }

    public void femaleCharacter(View view) {
        if(soundFlag == true){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.click1);
            mp.start();
        }
        femaleChar.setImageResource(R.drawable.female_pressed);
        maleChar.setImageResource(R.drawable.male);
        fullSetUp = true;
        boy = false;
        if(user != null){
            db.child("User").child(user).child("character").setValue("girl");
        } else{
            Toast.makeText(MainActivity.this,"Enter character name", Toast.LENGTH_LONG).show();
        }
    }

    public void sound(View view) {
        if(soundFlag == true){
            settingsImg.setImageResource(R.drawable.soundoff);
            soundFlag = false;
        } else if(soundFlag == false){
            settingsImg.setImageResource(R.drawable.soundon);
            soundFlag = true;
            MediaPlayer mp = MediaPlayer.create(this, R.raw.click1);
            mp.start();
        }
    }
}