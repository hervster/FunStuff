package com.example.albumstolistentoatwork

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URI

class MainActivity : AppCompatActivity() {


    // Declare references to firebase
    private val albumsRef: CollectionReference = FirebaseFirestore.getInstance().collection("albums");
    private val oldAlbumsRef: CollectionReference = FirebaseFirestore.getInstance().collection("oldAlbums");
    private val albumCountRef: DocumentReference = FirebaseFirestore.getInstance().collection("Information").document("Collection Information")
    private var albumCount: Long = 0
    private var artistName = findViewById<TextView>(R.id.generatedArtistName)
    private var albumName = findViewById<TextView>(R.id.generatedAlbumName)
    private var albumYear = findViewById<TextView>(R.id.generatedAlbumYear)
    private var generatedAlbumImage = findViewById<ImageView>(R.id.generatedAlbumImage)
    private lateinit var albumCoverURI : Uri

    // Document reference to specific document, might make undeclared variable to be defined later
    // private val docRef: DocumentReference = FirebaseFirestore.getInstance().collection("albums").document("test1");

    // Declare reference to firebase storage
    private val storageRef : StorageReference = FirebaseStorage.getInstance().getReference();
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_album.*


class MainActivity : AppCompatActivity() {

    //declare storage
    lateinit var storage:FirebaseStorage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val albums = listOf(
            Album(1,"TESTING", "ASAP Rocky", false),
            Album(1,"TESTING", "ASAP Rocky", false),
            Album(1,"TESTING", "ASAP Rocky", false),
            Album(1,"TESTING", "ASAP Rocky", false),
            Album(1,"TESTING", "ASAP Rocky", false),
            Album(1,"TESTING", "ASAP Rocky", false) )

        recyclerViewAlbums.layoutManager = LinearLayoutManager(this)
        recyclerViewAlbums.adapter = AlbumAdapter(albums)
    }

    fun LoadImage(){ //If loading from storage into image view doesn't work
        //init storage
        storage = FirebaseStorage.getInstance()
        var storageRef = storage.reference
        var pathRef = storageRef.child("TESTING.jpg") //Need handling for if file is png or jpeg
        //Something like if pathRef does not exist, path inside child is "Album name" .png
        val ONE_MEGABYTE: Long = 1024*1024 //Set sizing
        pathRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            //Image is returned
            //albumView = set albumView to image returned here
        }.addOnFailureListener{
            //Handle errors
        }
    }
    fun goBack(v: View) {   // Should i set an onclick listener instead?
        setContentView(R.layout.activity_main)
    }

    // This gets the album count field from firestore successfully, doesn't pass it back to anyone tho
    private fun getAlbumCount(): Long {
       albumCountRef.get()
            .addOnSuccessListener { albumInfoDoc ->
                if (albumInfoDoc != null) {
                    Log.d("infoExist", "DocumentSnapshot data: ${albumInfoDoc.data}")
                    this.albumCount = albumInfoDoc.getLong("Album Count")!!
                    println("Album count is: $albumCount")
                } else {
                    Log.d("infoNotExist", "Information does not exist")
                }
            }
        return albumCount
    }

    private fun updateCount() : Int {
        var count = 0
        albumsRef.get()
            .addOnSuccessListener { albums ->
                if (albums != null) {
                    Log.d("exist", "DocumentSnapshot data: ${albums.documents.size}")
                    for (album in albums) {
                        println(album.data.get("Album Year"))
                        // Probably should use a setter
                        count++
                        println(count)
                    }
                    println("Count after loop is: $count")
                    albumCountRef.update("Album Count", count)
                } else {
                    Log.d("noexist", "No documents found")
                }
                if (count == 0) {
                    println(
                        "This shit ain't working g"
                    )
                }
            }
            .addOnFailureListener{ exception ->
                Log.d("dberror", "get failed with", exception)
            }
        return count
    }

    fun randomizeAlbum(v: View) {
        // On click randomly choose an album from firestore and switch view to display album and info, add new album to prev album page and add album to oldAlbums collection

        // This code gets a document and verifies it exists
       /* docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                println("Document exists");
            } else {
                println("No such document")
            }
        } */
       var test : Long = getAlbumCount()
        updateCount()
        if (test.equals(0)) {
            getAlbumCount()
        }
        // This code checks the albums collection for an album that matches the field and value then grabs it
        albumsRef.whereEqualTo("Album Year", "albumYear").get().addOnSuccessListener { albums ->
            for (album in albums) {
                println(album)
                println(album.data.getValue("Name"))
                println(album.data.getValue("Artist"))
            }
            println("Number is $albumCount")
        }
        setContentView(R.layout.randomized_album)
        println("Number is $albumCount")
    }

}

