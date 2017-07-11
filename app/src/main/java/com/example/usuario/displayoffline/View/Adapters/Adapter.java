package com.example.usuario.displayoffline.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.displayoffline.Model.POJO.Track;
import com.example.usuario.displayoffline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter implements View.OnClickListener {

    private List<Track> tracksListList;
    private Context context;
    private View.OnClickListener myListener;

    //Constructor
    public Adapter(List<Track> tracksList, Context context) {
        this.tracksListList = tracksList;
        this.context = context;
    }

    //setters
    public void setMyListener(View.OnClickListener myListener) {
        this.myListener = myListener;
    }

    public void setTracksList(List<Track> tracksList) {
        this.tracksListList = tracksList;
        notifyDataSetChanged();
    }

    //adapter methods
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Track track = tracksListList.get(position);
        ViewHolder myViewHolder = (ViewHolder) holder;
        myViewHolder.loadData(track);
    }

    @Override
    public int getItemCount() {
        return tracksListList.size();
    }

    public Track getItem(Integer position) {
        return tracksListList.get(position);
    }

    @Override
    public void onClick(View v) {
        if (myListener != null) {
            myListener.onClick(v);
        }
    }

    //ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewAlbumId;
        private TextView textViewId;
        private TextView textViewTitle;
//        TextView textViewUrl;
        private ImageView imageViewThumbnailUrl;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewAlbumId = (TextView) itemView.findViewById(R.id.textView1);
            textViewId = (TextView) itemView.findViewById(R.id.textView2);
            textViewTitle = (TextView) itemView.findViewById(R.id.textView3);
//            textViewUrl = (TextView) itemView.findViewById(R.id.textView4);
            imageViewThumbnailUrl = (ImageView) itemView.findViewById(R.id.image);
        }
        public void loadData(Track track) {
            textViewAlbumId.setText(track.getAlbumId().toString());
            textViewId.setText(track.getId().toString());
            textViewTitle.setText(track.getTitle());
//            textViewUrl.setText(tracks.getUrl());
            Picasso.with(itemView.getContext())
                    .load(track.getImageUrl())
                    .placeholder(R.drawable.sand)
                    .into(imageViewThumbnailUrl);
        }
    }
}

