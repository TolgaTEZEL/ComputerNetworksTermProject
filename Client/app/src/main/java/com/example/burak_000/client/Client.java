/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.burak_000.client;

import android.widget.Button;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INSECT
 */
public class Client {

    //her clientın bir soketi olmalı
    public static Socket socket;

    //verileri almak için gerekli nesne
    public static ObjectInputStream sInput;
    //verileri göndermek için gerekli nesne
    public static ObjectOutputStream sOutput;

    public static void Start(String ip, int port,String message) {
        MainActivity m = new MainActivity();
        MainActivity.text.setText("Connected to server");
        try {
            // Client Soket nesnesi

            if( (Client.socket = new Socket(ip, port))!=null ){
                Client.Display("Connected to server");
                Client.sInput = new ObjectInputStream(Client.socket.getInputStream());
                // output stream
                Client.sOutput = new ObjectOutputStream(Client.socket.getOutputStream());
                Client.Display(Client.sInput.readObject().toString());
                Client.sOutput.writeObject(message);

                sOutput.flush();
                sOutput.close();
                socket.close();

            }else{
                MainActivity.text.setText("Unable to connect to server");
            }
            //Client.socket = new Socket(ip, port);


        } catch (IOException ex) {
            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            Client.Display("Unable to connect to server");
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            Client.Display("Unable to connect to server");
        }
    }

    public static void Display(String msg) {
        System.out.println(msg);
    }

}