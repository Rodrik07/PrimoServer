package ramadani.it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        System.out.println("Server avviato. In attesa di connessioni...");
        
        while (true) {
            //accetta la connessione di un client
            Socket socket = serverSocket.accept();
            System.out.println("Client connesso.");
            
            new Thread(() -> {
                try {
                    //input e output per la comunicazione con il client
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    try {
                        //legge l'operazione
                        int operazione = Integer.parseInt(in.readLine());

                        //legge i due numeri
                        double num1 = Double.parseDouble(in.readLine());
                        double num2 = Double.parseDouble(in.readLine());

                        double risultato = 0;
                        switch (operazione) {
                            case 1: 
                                risultato = num1 + num2;
                                break;
                            case 2: 
                                risultato = num1 - num2;
                                break;
                            case 3: 
                                risultato = num1 * num2;
                                break;
                            case 4:
                                if (num2 != 0) {
                                    risultato = num1 / num2;
                                } else {
                                    out.println("Errore: divisione per zero");
                                    socket.close();
                                    return;
                                }
                                break;
                            default:
                                out.println("Operazione non valida.");
                                socket.close();
                                return;
                        }

                        //invia il risultato al client
                        out.println("Risultato: " + risultato);
                    } catch (NumberFormatException e) {
                        out.println("Errore: input non valido.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //chiude la connessione
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start(); 
            
        }
    }
}
