import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class SetSockets{

    private Socket sockets[];
    private String nickname[];
    private BufferedReader input;
    private PrintWriter output;
    private Scanner messages;

    public SetSockets(int index){
        this.sockets= new Socket[index+1];
        this.nickname= new String[index+1];
        messages= new Scanner(System.in);
    }

    public void setSockets(Socket sockets, String nickname, int index){
        this.sockets[index]= sockets;
        this.nickname[index]= nickname;
    }

    public void runing(int index){
        System.out.println(sockets[index]+" " + nickname[index]);
        Threads threads= new Threads(sockets[index], nickname[index]);
        threads.start();
    }
}
