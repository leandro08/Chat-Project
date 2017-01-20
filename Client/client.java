import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class client extends Thread{

    private BufferedReader messages;

    public client(BufferedReader input){
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

        try{
            System.out.println("------Established connection------\n");
            Scanner msg= new Scanner(System.in);
            Socket client= new Socket("127.0.0.1", 4545);

            BufferedReader input= new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter output= new PrintWriter(client.getOutputStream(), true);

            client thread= new client(input);
            thread.start();

            while(true){
                output.println("client: "+msg.nextLine());
            }
        }

        catch (Exception e){
                System.out.println("Problems with the connection!");
            }

    }
}
