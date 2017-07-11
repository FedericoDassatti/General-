package com.example.usuario.displayoffline.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.displayoffline.Model.POJO.Track;
import com.example.usuario.displayoffline.Controller.ControllerTrack;
import com.example.usuario.displayoffline.R;
import com.example.usuario.displayoffline.View.Adapters.Adapter;
import com.example.usuario.displayoffline.util.ResultListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentRecycler extends Fragment {

    private List<Track> tracksLists;
    private Adapter adapter;
    private NotifyActivities notifyActivities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        tracksLists = new ArrayList<>();
        adapter = new Adapter(tracksLists, getContext());


        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(adapter);

        ControllerTrack controllerTrack = new ControllerTrack();
        controllerTrack.getListTTrack(view.getContext(), new ResultListener<List<Track>>() {
            @Override
            public void finish(List<Track> resultado) {
                adapter.setTracksList(resultado);
                adapter.notifyDataSetChanged();
            }
        });
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer position = recyclerView.getChildAdapterPosition(v);
                Track aTrack = adapter.getItem(position);
                notifyActivities.receiveMessaje(aTrack);
            }
        };
        adapter.setMyListener(listener);
        return view;
    }

    public interface NotifyActivities {
        public void receiveMessaje(Track aTrack);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.notifyActivities = (NotifyActivities) context;
    }

}
