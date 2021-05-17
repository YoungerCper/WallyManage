package com.example.wally;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import androidx.fragment.app.Fragment;

public class Setting extends Fragment implements View.OnClickListener
{
    private Button connect;
    private EditText port;
    private EditText adress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,
                container, false);

        this._Init(view);
        this._Listner();

        return view;
    }

    @Override
    public void onClick(View v) {
            if(v.getId() == R.id.connect_but) {
                Activity activity = getActivity();
                if (port.getText().length() != 0) {
                    int p = Integer.parseInt(port.getText().toString());
                    String a = adress.getText().toString();

                    if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
                        ((MainActivity) activity).portAndAdress(a, p);
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Client.result.substring(0, 14).equals("Your connected")) {
                    Toast t = Toast.makeText(activity.getApplicationContext(),
                            "Подключенно", Toast.LENGTH_SHORT);
                    t.show();
                    Client.result = "123456789012334533643534";
                } else {
                    Toast t = Toast.makeText(activity.getApplicationContext(),
                            "Соединение не установленно", Toast.LENGTH_SHORT);
                    t.show();
                }
            }



    }

    private void _Init(View view){
        connect = view.findViewById(R.id.connect_but);

        port = view.findViewById(R.id.port);
        adress = view.findViewById(R.id.adress);


    }

    private void _Listner(){
        connect.setOnClickListener(this);

    }

}
