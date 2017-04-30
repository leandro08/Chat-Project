import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
	
	private BufferedReader input;

	public Client(BufferedReader inputChannel){
		this.input= inputChannel;
	}

	public void run(){
		try{
			while(true){
				if(input.ready()){System.out.println(">>> " + input.readLine());}
				Thread.sleep(15);
			}
		}
		catch(Exception ex){System.out.println("Error with the input channel!");}
	}
	
	public static void main(String [] args){
		
		try{
			Socket socket= new Socket("127.0.0.1", 4545);
			System.out.println("---Established connection---\n");

			PrintWriter output= new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner read= new Scanner(System.in);
			
			int ID= Integer.parseInt(input.readLine());
			System.out.println(input.readLine());
			String nickname= read.nextLine();

			output.println(nickname);  
			Client thread= new Client(input);
			thread.start();

			while(true){
				output.println(read.nextLine());
				
			}
		}
		catch(Exception ex){System.out.println("Problems with the connection!");}
	}
}
