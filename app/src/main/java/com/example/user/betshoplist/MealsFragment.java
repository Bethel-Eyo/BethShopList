package com.example.user.betshoplist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 3/27/2017.
 */

public class MealsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewer = inflater.inflate(R.layout.fragment_meals, container, false);
        return viewer;
    }

    public static MealsFragment newInstance() {

        Bundle args = new Bundle();

        MealsFragment fragment = new MealsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
