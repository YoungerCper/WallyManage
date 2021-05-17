package com.example.wally;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import io.github.controlwear.virtual.joystick.android.JoystickView;

public class Joystick extends Fragment implements SeekBar.OnSeekBarChangeListener, OnCheckedChangeListener
{

    private SeekBar rh;
    private SeekBar lh;
    private SeekBar hh;
    private SeekBar eh;

    private TextView rt;
    private TextView lt;
    private TextView ht;
    private TextView et;

    private ToggleButton rht;
    private ToggleButton lht;

    private JoystickView joystick;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private SwitchCompat aSwitch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joystick,
                container, false);

        this._Init(view);
        this._Listner();

        return view;
    }

    private void _Init(View view){
        rh = view.findViewById(R.id.righthand);
        lh = view.findViewById(R.id.lefthand);
        hh = view.findViewById(R.id.headrotate);
        eh = view.findViewById(R.id.eye);

        rt = view.findViewById(R.id.rhg);
        lt = view.findViewById(R.id.lhg);
        ht = view.findViewById(R.id.hrg);
        et = view.findViewById(R.id.erg);

        lht = view.findViewById(R.id.lht);
        rht = view.findViewById(R.id.rht);
        joystick = (JoystickView) view.findViewById(R.id.joystickView);

        aSwitch = view.findViewById(R.id.switcher);
    }

    private void _Listner(){
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int a, int s) {
                Activity activity = getActivity();
                if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
                    ((MainActivity) activity).giveCommand("J" + " " + Integer.toString(a) + " " +  Integer.toString(s));
                }
            }
        });

        rh.setOnSeekBarChangeListener(this);
        lh.setOnSeekBarChangeListener(this);
        hh.setOnSeekBarChangeListener(this);
        eh.setOnSeekBarChangeListener(this);

        rht.setOnCheckedChangeListener(this);
        lht.setOnCheckedChangeListener(this);

        aSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        String command = "";
        switch (seekBar.getId()){
            case R.id.righthand:
                rt.setText(String.valueOf(seekBar.getProgress()));
                command = "R " + String.valueOf(seekBar.getProgress());
                break;
            case R.id.lefthand:
                lt.setText(String.valueOf(seekBar.getProgress()));
                command = "L " + String.valueOf(seekBar.getProgress());
                break;
            case R.id.headrotate:
                ht.setText(String.valueOf(seekBar.getProgress()));
                command = "H " + String.valueOf(seekBar.getProgress());
                break;
            case R.id.eye:
                et.setText(String.valueOf(seekBar.getProgress()));
                command = "E " + String.valueOf(seekBar.getProgress());
                break;
        }
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
            ((MainActivity) activity).giveCommand(command);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.lht || buttonView.getId() == R.id.rht) {
            String command = "T";

            if (isChecked) {
                switch (buttonView.getId()) {
                    case R.id.lht:
                        command += " L 1";
                        break;
                    case R.id.rht:
                        command += " R 1";
                        break;
                }
            } else {
                switch (buttonView.getId()) {
                    case R.id.lht:
                        command += " L 0";
                        break;
                    case R.id.rht:
                        command += " R 0";
                        break;
                }
            }
            Activity activity = getActivity();
            if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
                ((MainActivity) activity).giveCommand(command);
            }
        }
        if(buttonView.getId() == R.id.switcher){
            Activity activity2 = getActivity();
            if (activity2 != null && !activity2.isFinishing() && activity2 instanceof MainActivity) {
                String command = "";
                boolean checked = isChecked;
                if (checked){
                    command = "M A";
                }
                else{
                    command = "M H";
                }
                ((MainActivity) activity2).giveCommand(command);
            }
        }
    }
}
