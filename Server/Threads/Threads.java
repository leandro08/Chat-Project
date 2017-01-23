import java.net.Socket;
import java.io.*;

public class Threads extends Thread{

    private Socket socket;
    private String nickname;
    private BufferedReader input;
    private PrintWriter output;

    public Threads(Socket socket, String nickname){
        this.socket= socket;
        this.nickname= nickname;

        try{
            output= new PrintWriter(socket.getOutputStream(), true);
            input= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (Exception e){
            System.out.println("Problems with the IO channels!");
        }
    }

    public void run(){
        output.println("HELLO!");
    }
}
