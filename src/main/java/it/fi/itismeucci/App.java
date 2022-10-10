package it.fi.itismeucci;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Multiserver tcpServer=new Multiserver();
        tcpServer.avvio();
    }
}
