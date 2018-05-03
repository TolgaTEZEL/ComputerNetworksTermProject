package com.example.burak_000.client;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button forward;
    Button back;
    TextView text;
    private Socket s;
    private ServerSocket ss;
    private static InputStreamReader isr;
    private static BufferedReader br;
    private static PrintWriter pr;

    String message="";
    static String ip="192.168.1.102";
    static int port=5000;

    final Context context = this;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        forward = (Button) findViewById(R.id.ileri);
        back = (Button) findViewById(R.id.geri);
        text = (TextView)findViewById(R.id.textView);

        MyTask mt = new MyTask();
        mt.execute();

    }

    class MyTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params){
            forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                text.setText("İleri gidiyor");

                    message = text.getText().toString();
                    Client.Start(ip, port,message);

                    /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);
                    //message = messageBox.getText().toString();
                    Client.Start(ip, port,message);
                    alertDialogBuilder.setTitle(message);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();*/

                }
            });

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    text.setText("Geri gidiyor");
                    message = text.getText().toString();
                    Client.Start(ip, port,message);
                }
            });

            return null;
        }

    }

}
