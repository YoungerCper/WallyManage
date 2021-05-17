package com.example.wally;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class VoiceManage extends Fragment implements View.OnClickListener{

    ImageButton[] com = new ImageButton[10];

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voice,
                container, false);
        com[0] = view.findViewById(R.id.b0);
        com[1] = view.findViewById(R.id.b1);
        com[2] = view.findViewById(R.id.b2);
        com[3] = view.findViewById(R.id.b3);
        com[4] = view.findViewById(R.id.b4);
        com[5] = view.findViewById(R.id.b5);
        com[6] = view.findViewById(R.id.b6);
        com[7] = view.findViewById(R.id.b7);
        com[8] = view.findViewById(R.id.b8);


        com[0].setOnClickListener(this);
        com[1].setOnClickListener(this);
        com[2].setOnClickListener(this);
        com[3].setOnClickListener(this);
        com[4].setOnClickListener(this);
        com[5].setOnClickListener(this);
        com[6].setOnClickListener(this);
        com[7].setOnClickListener(this);
        com[8].setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String command = "";
        switch (v.getId()){
            case R.id.b0:
                command = "V 0";break;
            case R.id.b1: command = "V 1";break;
            case R.id.b2: command = "V 2";break;
            case R.id.b3: command = "V 3";break;
            case R.id.b4: command = "V 4";break;
            case R.id.b5: command = "V 5";break;
            case R.id.b6: command = "V 6";break;
            case R.id.b7: command = "V 7";break;
            case R.id.b8: command = "V 8";break;


        }
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
            //String a = adress.getText().toString();
            //int p = Integer.parseInt(port.getText().toString());
            ((MainActivity) activity).giveCommand(command);
        }
    }

}
