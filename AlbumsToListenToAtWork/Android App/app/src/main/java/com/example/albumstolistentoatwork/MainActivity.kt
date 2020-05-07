package com.example.albumstolistentoatwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

}
