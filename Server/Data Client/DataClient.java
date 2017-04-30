import java.net.Socket;
import java.util.ArrayList;

public class DataClient{

	public static ArrayList<Socket> sockets= new ArrayList<Socket>();
	public static ArrayList<String> nicknames= new ArrayList<String>();

	public static void set(Socket socket, String nickname){
		sockets.add(socket);
		nicknames.add(nickname);
	}
	
	public static Socket getSocket(int index){
		return sockets.get(index);
	}
	
	public static String getNickname(int index){
		return nicknames.get(index);
	}
}
