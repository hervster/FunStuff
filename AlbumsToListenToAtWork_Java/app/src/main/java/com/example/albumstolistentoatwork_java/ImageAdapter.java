package com.example.albumstolistentoatwork_java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Album> mAlbums;

    public ImageAdapter(Context context, List<Album> albums) {
        mContext = context;
        mAlbums = albums;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.album, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Album albumCurrent = mAlbums.get(position);
        holder.textPrevAlbumName.setText(albumCurrent.getAlbumName());
        holder.textPrevAlbumYear.setText(albumCurrent.getAlbumYear());
        holder.textPrevAlbumYear.setText(albumCurrent.getArtistName());
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textPrevAlbumName;
        public TextView textPrevArtist;
        public TextView textPrevAlbumYear;
        public ImageView albumView;

        public ImageViewHolder( View itemView) {
            super(itemView);

            textPrevAlbumName = itemView.findViewById(R.id.prevAlbumName);
            textPrevAlbumYear = itemView.findViewById(R.id.prevAlbumYear);
            textPrevArtist = itemView.findViewById(R.id.prevArtist);
            albumView = itemView.findViewById(R.id.prevAlbumImage);

        }
    }
}
