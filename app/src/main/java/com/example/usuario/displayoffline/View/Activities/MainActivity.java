package com.example.usuario.displayoffline.View.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.displayoffline.Model.POJO.Track;
import com.example.usuario.displayoffline.R;
import com.example.usuario.displayoffline.View.Fragments.FragmentDetail;
import com.example.usuario.displayoffline.View.Fragments.FragmentRecycler;

public class MainActivity extends AppCompatActivity implements FragmentRecycler.NotifyActivities {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this makes the travel
        FragmentRecycler fragmentRecycler = new FragmentRecycler();
        sendFragment(fragmentRecycler, false);
    }

    @Override
    public void receiveMessaje(Track aTrack) {
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentDetail.ID, aTrack.getId());
        bundle.putString(FragmentDetail.IMAGEURL, aTrack.getImageUrl());
        bundle.putString(FragmentDetail.TITLE, aTrack.getTitle());
        bundle.putInt(FragmentDetail.ALBUM, aTrack.getAlbumId());

        FragmentDetail fragmentDetail = new FragmentDetail();
        fragmentDetail.setArguments(bundle);
        sendFragment(fragmentDetail, true);

    }


    public void sendFragment(Fragment unFragment, Boolean back) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerInMain, unFragment);
        if (back) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}



