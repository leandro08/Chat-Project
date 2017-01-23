import java.net.Socket;
import java.util.Scanner;

public class Server{

    public static void main(String [] args){

        System.out.println(" Waiting for the connection...\n");

        try{
            ServerSocket server= new ServerSocket(4545);
            Socket sockets= null;
            Scanner messages= new Scanner(System.in);
            BufferedReader nickname;
            SetSockets thread= new SetSockets(10);
            int index= 0;

            while(index < 11){
                sockets= server.accept();
                nickname= new BufferedReader(new InputStreamReader(sockets.getInputStream()));
                System.out.println("New connection from "+sockets.getInetAddress());
                thread.setSockets(sockets, nickname.readLine(), index);
                thread.runing(index);
                index++;
            }
        }
        catch (Exception e){
            System.out.println("Problems with the connection!");
        }
    }
}
