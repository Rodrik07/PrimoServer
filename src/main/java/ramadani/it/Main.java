package ramadani.it;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        ServerSocket mioServerSocket = new ServerSocket(3000);
        //il mio main quando arriva qui si ferma e quello sotto non viene eseguito fino a quando qualcuno si collega e mi restituisce un socket
        Socket mioSocket =  mioServerSocket.accept();
        System.out.println("Un CLIENT si e' connesso");

        System.out.println("ciao");
        //ciao come stai
    }
}