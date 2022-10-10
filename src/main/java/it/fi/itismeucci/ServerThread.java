package it.fi.itismeucci;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    public ServerThread(Socket socket)
    {
        this.client=socket;
    }

    protected Socket attendi() {
        try {
            System.out.println("1-Il Server è partito in esecuzione. . .");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient=new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);

        }
        return client;
    }

    public void comunica() throws IOException {
       inDalClient=new BufferedReader(new InputStreamReader(client.getInputStream()));
       outVersoClient=new DataOutputStream(client.getOutputStream());
       for(;;){
        stringaRicevuta=inDalClient.readLine();
        if(stringaRicevuta==null||stringaRicevuta.equals("FINE")){
            outVersoClient.writeBytes(stringaRicevuta+" (=>server in chiusura. . ."+'n');
            System.out.println("ECho sul server in chiusura :"+stringaRicevuta);
            break;
        }
        else{
            outVersoClient.writeBytes(stringaRicevuta+" ricevuta e ristramessa"+'\n');
            System.out.println("6 echo sul server: "+stringaRicevuta);
        }
       }
       outVersoClient.close();
       inDalClient.close();
       System.out.println("9 Chiusura socket"+client);
       client.close();
        

    }
   
    public void run(){
        try{
            comunica();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
}