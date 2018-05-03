/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package p2pserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INSECT
 */

public class Server{

    //server soketi eklemeliyiz
    public static ServerSocket serverSocket;
    // Serverın dileyeceği port
    public static int port = 0;

    public static void Start(int openport) {

            try {
                Server.port = openport;
                // serversoket nesnesi
                Server.serverSocket = new ServerSocket(Server.port);
                
                while (true) {
                Server.Display("Client Bekleniyor...");

                Socket clientSocket = Server.serverSocket.accept();

                Server.Display("Client Geldi...");

                ObjectOutputStream sOutput = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream sInput = new ObjectInputStream(clientSocket.getInputStream());

                sOutput.writeObject("Server:Hosgeldin");
                
                String message = sInput.readObject().toString(); 
                //Server.Display();
                
                if(message!=null && message.equals("İleri gidiyor"))
                    System.out.println("ula ileri gidiyi");
                else if(message!=null && message.equals("Geri gidiyor"))
                        System.out.println("ula geri gidiyi");
                
            }

            } catch (IOException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public static void Display(String msg) {

        System.out.println(msg);

    }

}