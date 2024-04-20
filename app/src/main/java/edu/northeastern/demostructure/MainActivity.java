package edu.northeastern.demostructure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void settings(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}