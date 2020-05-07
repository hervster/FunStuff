package com.example.albumstolistentoatwork_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class AlbumActivity extends AppCompatActivity {

    private ImageView generatedAlbumImage;
    private TextView generatedAlbumName;
    private TextView generatedArtistName;
    private TextView generatedAlbumYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        generatedAlbumImage = findViewById(R.id.generatedAlbumImage);
        generatedAlbumName = findViewById(R.id.generatedAlbumName);
        generatedAlbumYear = findViewById(R.id.generatedAlbumYear);
        generatedArtistName = findViewById(R.id.generatedArtistName);

        Intent intent = getIntent();
        String albumName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_ALBUM_NAME);
        String albumYear = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_ALBUM_YEAR);
        String artistName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_ARTIST);
        generatedAlbumName.setText(albumName);
        generatedAlbumYear.setText(albumYear);
        generatedArtistName.setText(artistName);

        // Load image
       /* Glide.with(this)
                .load(getDrawable())
                .into(generatedAlbumImage);
            */
    }

}
