package edu.northeastern.demostructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class into extends AppCompatActivity {

    String intro1 = "In the midst of school's bustling chaos, you felt the sting of isolation and bullying, pleading for help to no avail. But you refused to surrender to despair, instead embarking on a journey of self-empowerment. Immersing yourself in books and study guides, you ignited a spark of determination, fueling your quest for academic excellence. Concurrently, you embraced the challenge of physical fitness, sculpting your body into a fortress against the onslaught of adversity.";
    String intro2 = "As you navigate through the game, you'll encounter various challenges and opportunities for growth. Your choices will shape your destiny, leading to one of five possible endings. Will you prioritize knowledge, physical strength, relationships, culinary skills, or even delve into the mystical realm of magic? The path you choose will determine your fate. Are you ready to embark on this transformative journey and discover which ending awaits you?";


    String name;
    Button next;
    TextView text;
    ImageView image;
    int time = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into);

        text = findViewById(R.id.introBox);
        image = findViewById(R.id.introImage);

        text.setText(intro1);
        image.setImageResource(R.drawable.b_intro);
    }

    public void next(View view){
        if (time>0){
            text.setText(intro2);
            image.setImageResource(R.drawable.g_intro);
            time--;
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}