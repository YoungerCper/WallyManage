package com.example.wally;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Stream extends Fragment implements View.OnClickListener
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stream,
                container, false);
        return view;
    }
    @Override
    public void onClick(View v) {

    }
}
