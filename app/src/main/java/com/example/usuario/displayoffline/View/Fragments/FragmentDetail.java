package com.example.usuario.displayoffline.View.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.displayoffline.R;
import com.squareup.picasso.Picasso;


public class FragmentDetail extends Fragment {

    public static final String ID = "id";
    public static final String IMAGEURL = "imageUrl";
    public static final String TITLE = "title";
    public static final String ALBUM = "album";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();

        Integer id = bundle.getInt(ID);
        String imageUrl = bundle.getString(IMAGEURL);
        String title = bundle.getString(TITLE);
        Integer album = bundle.getInt(ALBUM);

        ImageView imageViewTrack = (ImageView) view.findViewById(R.id.imageDetail);
        TextView textviewId = (TextView) view.findViewById(R.id.trackDetail);
        TextView textViewTitle = (TextView) view.findViewById(R.id.titleDetail);
        TextView textViewAlbum = (TextView) view.findViewById(R.id.albumDetail);

        textviewId.setText("Track: " + id.toString());
        textViewTitle.setText(title);
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.sand)
                .into(imageViewTrack);
        textViewAlbum.setText("Album: " + album.toString());


        return view;
    }
}
