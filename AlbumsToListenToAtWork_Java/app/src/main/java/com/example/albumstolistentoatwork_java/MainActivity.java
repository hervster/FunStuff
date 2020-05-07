package com.example.albumstolistentoatwork_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_ARTIST = "com.example.albumstolistentoatwork_java.MESSAGE1";
    public static final String EXTRA_MESSAGE_ALBUM_NAME = "com.example.albumstolistentoatwork_java.MESSAGE2";
    public static final String EXTRA_MESSAGE_ALBUM_YEAR = "com.example.albumstolistentoatwork_java.MESSAGE3";

    // First view variables
    private Button randomizeButton;
    private static final String TAG = "DocSnippets";

    public Album generatedAlbumClass = new Album();
    public int albumCount;
    public String randomAlbumName;

  // Create db and firebase storage references
    public StorageReference albumCoversRef;
    private CollectionReference albumsRef;
    private CollectionReference oldAlbumsRef;
    private DocumentReference albumInfo;
    public Map<String, Map<String, Object>> albumList = new HashMap<>();
    public Map<String, Object> oldAlbumList = new HashMap<>();
    public Map<Integer, String> albumArrayMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomizeButton = findViewById(R.id.randomizeButton);

        albumCoversRef = FirebaseStorage.getInstance().getReference();
        albumsRef = FirebaseFirestore.getInstance().collection("albums");
        oldAlbumsRef = FirebaseFirestore.getInstance().collection("oldAlbums");
        albumInfo = FirebaseFirestore.getInstance().collection("Information").document("Collection Information");


        randomizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomizeAlbum();
                //openRandomizedAlbumActivity();
            }
        });

    }

    public void randomizeAlbum() {

        testDB();

        System.out.println("The random album name again hopefully is :" + randomAlbumName);
        //openRandomizedAlbumActivity();
    }

    // Open new activity and pass album class data
    public void openRandomizedAlbumActivity() {
        Intent intent = new Intent( this, AlbumActivity.class);
        //generatedAlbumClass.setAlbumName(randomAlbumName);

        intent.putExtra(EXTRA_MESSAGE_ALBUM_NAME, generatedAlbumClass.getAlbumName());
        System.out.println(generatedAlbumClass.getAlbumName());
        intent.putExtra(EXTRA_MESSAGE_ALBUM_YEAR, generatedAlbumClass.getAlbumYear());
        intent.putExtra(EXTRA_MESSAGE_ARTIST, generatedAlbumClass.getArtistName());
        startActivity(intent);
    }

    // First make an arraylist with all the names of the albums
    // Then make a randomizer that chooses a random index in the array list and returns the string name
    // Then check if that string name exists in a hashmap of all the old albums
    // If it does run again, if not grab the data from albums db using name of album and pass data to intent
    // Then add to old albums db

    /**
     *  Checks old albums map to see if passed string is a key in the map
     * @param album album name to be passed
     * @return boolean to be returned
     */
    public boolean checkOldAlbumList(String album) {
        return oldAlbumList.containsKey(album);
    }

    public void testDB() {
        System.out.println("The random album name again is :" + randomAlbumName);
        // This grabs all of the albums in the db and adds them to a hashmap
        albumsRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                albumList.put(document.getId(), document.getData());
                                albumArrayMap.put(albumCount, document.getId());
                                //albumArrayMap2.put(albumCount, document.toObject(Album.class)); // To object works but the class needs to match the fields in firestore
                                //System.out.println(albumArrayMap2);
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                albumCount++; // Update albumCount later
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        oldAlbumsRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                oldAlbumList.put(document.getId(), document.getData());
                            }

                            int size = albumArrayMap.size();
                            System.out.println("In old albums ref map size: " + size);
                            int randNum = (int) Math.floor(Math.random() * size);
                            String temp = albumArrayMap.get(randNum);
                            System.out.println("Testing get first: " + temp);

                            while (checkOldAlbumList(temp)) {
                                randNum = (int) Math.floor(Math.random() * size);
                                temp = albumArrayMap.get(randNum);
                                System.out.println("The current temporary album is: " + temp);
                            }
                            System.out.println("The final temporary album is: " + temp);
                            randomAlbumName = temp;
                           generatedAlbumClass.setAlbumName(temp);
                            // How do i get this string out of here though
                            generatedAlbumClass.setArtistName(albumList.get(temp).get("Artist").toString().trim());
                            System.out.println(albumList.get(temp));
                            generatedAlbumClass.setAlbumYear(albumList.get(temp).get("Album Year").toString().trim());

                        }
                        System.out.println("The random album name is :" + randomAlbumName);
                        System.out.println("The generated album name is :" + generatedAlbumClass.getAlbumName());

                        openRandomizedAlbumActivity();
                    }
                });

        System.out.println("The random album name again is :" + randomAlbumName); // This is asynchronus, how do we wait for other
        // Or is it failing outright outside of scope
        System.out.println("The generated album name outside is :" + generatedAlbumClass.getAlbumName());
    }

}
