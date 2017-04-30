import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	
	private BufferedReader in_cmd;
	private PrintWriter out;
	private int ID;

	public Server(BufferedReader inputChannel, PrintWriter outputChannel, int ID){
		this.in_cmd= inputChannel;
		this.out= outputChannel;
		this.ID= ID;
	}

	public void run(){
		try{
			int index= 0;	
			String read= "";

			while(true){
				if(in_cmd.ready()){
					read= in_cmd.readLine();

					if(read.length()>5){
						index= Integer.parseInt(read.split("-")[1]);
						read= read.split("-")[0];}

					if(read.equals(":LIST")){
						Commands.getUsers(out);}

					else if(read.equals(":JOIN")){
						Commands.setChat(index, in_cmd, out, ID);}
		     	}
			Thread.sleep(15);
		   }
		}
		catch(Exception ex){System.out.println("Problems with the IO channels...");}
	}

	public static void main(String [] args){
		
		System.out.println("Waiting for the connection...\n");

		try{
			BufferedReader input= null;
			PrintWriter output= null;
			ServerSocket server= new ServerSocket(4545);
		
			Socket socket= null;
			String nickname= "";
			Server thread= null;

			int index= 0;

			while(true){
				socket= server.accept();
				output= new PrintWriter(socket.getOutputStream(), true);
				output.println(index);
				output.println("Enter a nickname to login:");

				input= new BufferedReader(new InputStreamReader(socket.getInputStream()));
				nickname= input.readLine();
				DataClient.set(socket, nickname);

				System.out.println("New connection from "+socket.getInetAddress());
				thread= new Server(input, output, index);
				thread.start();
				index++;
	
			}
		}
		catch(Exception ex){System.out.println("Problems with the connection");}
	}
}
