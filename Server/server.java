import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class server extends Thread{

    private BufferedReader messages;

    public server(BufferedReader input){
        messages= input;
    }

    public void run(){
    try{
        while(true){
            System.out.println(messages.readLine());
        }
    }

    catch (Exception e){
        System.out.println("Problems...");
        }
    }

    public static void main(String [] args){

        System.out.println("Waiting for the connection...\n");

        try{
            Scanner msg= new Scanner(System.in);
            ServerSocket Server= new ServerSocket(4545);
            Socket server= Server.accept();
            System.out.println("------Established connection------\n");

            BufferedReader input= new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter output= new PrintWriter(server.getOutputStream(), true);

            server thread= new server(input);
            thread.start();

            while(true){
                output.println("server: "+msg.nextLine());
                }
            }

        catch (Exception e){
             System.out.println("Problems with the connection!");
             }


    }
}

