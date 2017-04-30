import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Commands extends Thread{

	private int index;
	private BufferedReader input;
	private BufferedReader in_puts;
	private PrintWriter output;
	private int ID;

	public Commands(int ID, BufferedReader inputChannel, PrintWriter outputChannel, int index){
		this.index= index;
		this.input= inputChannel;
		this.output= outputChannel;
		this.ID= ID;
	}

	public void run(){
		try{
			Socket socket= DataClient.getSocket(index);
			PrintWriter output= new PrintWriter(socket.getOutputStream(), true);
			output.println("Connection established with "+ DataClient.getNickname(ID) + "\n");

			String msg= "";

			while(true){
				msg= input.readLine();				

				if(msg.equals(":OUT")){
					output.println("Closing connection...");
					output.println("Done!");
					break;}
		
				else{output.println(msg);}

				Thread.sleep(15);
			}
		}
		catch(Exception ex){System.out.println("Error with the connection");}
	}
	
	public static void getUsers(PrintWriter output){
		try{
			output.println("Users online:");
			for(int i=0; i<DataClient.nicknames.size(); i++){
				output.println(i + ") " + DataClient.getNickname(i));
			}
		}
		catch(Exception ex){System.out.println("Error to get users online");}
	}

	public static void setChat(int index, BufferedReader inputChannel, PrintWriter outputChannel, int ID){
		try{
			Socket socket= DataClient.getSocket(index);
			BufferedReader input= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output= new PrintWriter(socket.getOutputStream(), true);
		
			outputChannel.println("Waiting for an answer...");	
			output.println("Do you want to connect with "+ DataClient.getNickname(ID) + "?" +
			"  ---> press 'y' to accept or 'n' to reject");

			String answ= input.readLine();

			if(answ.equals("y")){
				Commands thread= new Commands(index, input, outputChannel, ID);
				thread.start();}

			else{
				outputChannel.println("Connection refused");
				output.println("Connection refused");
				return;}
	
			output.println("Connection established with "+ DataClient.getNickname(ID) + "\n");
			String msg= "";

			while(true){
				msg= inputChannel.readLine();						

				if(msg.equals(":OUT")){
					output.println("Closing connection...");
					output.println("Done!");
					break;}
				
				else{output.println(msg);}
			}
		}
		catch(Exception e){System.out.println("Problems with establishing connection with the user");}
	}
}
