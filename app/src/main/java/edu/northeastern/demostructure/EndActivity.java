package edu.northeastern.demostructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    boolean boy;
    int ending;
    WebView imageView;
    Images images;
    ImageView endchar;
    TextView endStory;
    Boolean soundFlag;

    String cook = "Guided by a passion for culinary creativity, you rise to fame as a master chef, delighting palates with your delectable dishes and innovative flavors. Your culinary creations bring joy and warmth to all who taste them, a testament to the transformative power of food and love.";
    String magic = "Embracing the arcane arts, you master the ancient secrets of magic, wielding its power with grace and precision. With each incantation spoken and spell cast, you reshape reality itself, a sorcerer of unparalleled skill and wisdom.";
    String charm = "Armed with charisma and charm, you navigate the complexities of human connection with ease, winning over hearts and minds wherever you go. Your magnetic personality leaves a lasting impression on all who cross your path, transforming even the darkest of days into moments of joy.";
    String knowledge = "Driven by a thirst for knowledge, you delve deep into the realm of science, unlocking the mysteries of the universe with each discovery. Your brilliance shines bright, illuminating the path for future generations to follow in your footsteps.";
    String fighter = "Fueled by the fire of resilience, you emerge as a formidable fighter, channeling the pain of your past into strength and determination. With every battle won, you carve out a place for yourself in the world, a beacon of hope for others facing similar struggles.";
    String lowSan = "Lost in the depths of despair, you succumb to madness, the echoes of bullying and isolation haunting your every thought. Alone and forgotten, you wander the halls of your school, a ghost of your former self, lost to the darkness within.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        endchar = findViewById(R.id.imageView9);
        imageView = findViewById(R.id.ending);
        endStory = findViewById(R.id.endStory);
        endStory.setMovementMethod(new ScrollingMovementMethod());

        boy = getIntent().getBooleanExtra("boy", true);
        ending = getIntent().getIntExtra("image",-1);
        soundFlag = getIntent().getBooleanExtra("soundFlag",true);

        if(boy){
            endchar.setImageResource(R.drawable.male);
        } else {
            endchar.setImageResource(R.drawable.female);
        }
        String endString = "";
        switch (ending){
            case -1 : endString = lowSan; break;
            case 1 : endString = fighter; break;
            case 2 : endString = knowledge; break;
            case 3 : endString = charm; break;
            case 4 : endString = magic; break;
            case 5 : endString = cook; break;
        }
        endStory.setText(endString);

        images = new Images(boy);

        String endingURL = images.getEnding(ending);
        imageView.getSettings().setLoadWithOverviewMode(true);
        imageView.getSettings().setUseWideViewPort(true);
        imageView.loadUrl(endingURL);

        if(soundFlag){
            MediaPlayer mp;
            if (ending == -1){
                mp = MediaPlayer.create(this, R.raw.lost);
            } else {
                mp = MediaPlayer.create(this, R.raw.win);
            }
            mp.start();
        }
    }

    public void tryAgain(View view){
        Intent intent = new Intent(this, into.class);
        startActivity(intent);
    }
}

