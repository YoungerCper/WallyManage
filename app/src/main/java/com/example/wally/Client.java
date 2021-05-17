package com.example.wally;
import android.content.res.Resources;
import android.widget.TextView;
import android.widget.Toast;

import java.net.*;
import java.io.*;

import androidx.appcompat.app.AppCompatActivity;

public class Client extends Thread{

    public static String result = "123456789012334533643534";

    private String _data = "";
    private String _address;
    private int _port;
    private TextView t = null;

    public Client(String command, String address, int port){
        this._data = command;
        this._address = address;
        this._port = port;

    }

    public void run(){
        try {
            this.main();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void main() throws IOException {
        Socket client_socket = new Socket(this._address, this._port);
        client_socket.getOutputStream().write(this._data.getBytes());
        if (this._data == "test") {
            byte[] b = new byte[100];
            client_socket.getInputStream().read(b);
            Client.result = new String(b, "utf-8");
        }
        client_socket.close();
    }

}
