package edu.northeastern.demostructure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class EndActivity extends AppCompatActivity {
    boolean boy;
    int ending;
    WebView imageView;
    Images images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        boy = getIntent().getBooleanExtra("boy", true);
        ending = getIntent().getIntExtra("image",-1);

        imageView = findViewById(R.id.ending);
        images = new Images(boy);

        String endingURL = images.getEnding(ending);
        imageView.getSettings().setLoadWithOverviewMode(true);
        imageView.getSettings().setUseWideViewPort(true);
        imageView.loadUrl(endingURL);
    }
}

