package com.example.wally;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private Fragment j = new Joystick();
    private Fragment set = new Setting();
    private Fragment m = new VoiceManage();
    private BottomNavigationView bottonnav;

    final private String _addressCode = "APP_PREFERENCES_ADDRESS";
    final private String _portCode = "APP_PREFERENCES_PORT";
    final static private String _nameSetting = "ADDRESS_AND_PORT";

    private SharedPreferences _addressAndPort;

    private String _adress;
    private int _port = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottonnav = findViewById(R.id.bottomNavigationView);
        bottonnav.setOnNavigationItemSelectedListener(navListner);

        this._addressAndPort = getSharedPreferences(MainActivity._nameSetting, Context.MODE_PRIVATE);

        Fragment selectedFragment = j;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, selectedFragment).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = this._addressAndPort.edit();
        editor.putString(this._addressCode, this._adress);
        editor.putInt(this._portCode, this._port);
        editor.apply();
    }

    @Override
    protected void onResume(){
        super.onResume();

        if(this._addressAndPort.contains(this._addressCode)){
            this._adress = this._addressAndPort.getString(this._addressCode, "");
        }
        if(this._addressAndPort.contains(this._portCode)){
            this._port = this._addressAndPort.getInt(this._portCode, -1);
        }
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.joystick:
                            selectedFragment = j;
                            break;
                        case R.id.setting:
                            selectedFragment = set;
                            break;
                        case R.id.voice:
                            selectedFragment = m;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, selectedFragment).commit();
                    return true;
                }
            };

    public boolean portAndAdress(String adress, int port){
        boolean result = true;
        this._adress = adress;
        this._port = port;
        if(this._adress != "" && this._port != -1) {
            Client c = new Client("test", this._adress, this._port);
            c.start();
        }
        return result;
    }

    public void giveCommand(String command){
        if(this._adress != "" && this._port != -1) {
            Client c = new Client(command, this._adress, this._port);
            c.start();
        }
    }

}