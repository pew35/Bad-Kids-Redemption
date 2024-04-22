package edu.northeastern.demostructure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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

        if(boy == true){
            endchar.setImageResource(R.drawable.male);
        } else {
            endchar.setImageResource(R.drawable.female);
        }
        String path = getIntent().getStringExtra("path");
        endStory.setText(path);

        images = new Images(boy);

        String endingURL = images.getEnding(ending);
        imageView.getSettings().setLoadWithOverviewMode(true);
        imageView.getSettings().setUseWideViewPort(true);
        imageView.loadUrl(endingURL);
    }
}

