package com.example.albumstolistentoatwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_album.view.*

class AlbumAdapter(val oldAlbums : List<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_album, parent, false)
        )
    }

    override fun getItemCount() = oldAlbums.size



    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = oldAlbums[position]

        holder.view.textViewAlbumName.text = album.name
        holder.view.textViewArtistName.text = album.artist

    }


    class AlbumViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}