import java.io.*;
import java.net.*;

class MyClient
{
	private final int SERVER_SOCKET_PORT = 5000;
	private Socket s = null;
	private DataInputStream din = null;
	MyClient()
	{
		try
		{
			s = new Socket("localhost", SERVER_SOCKET_PORT);
			din = new DataInputStream(s.getInputStream());
			startChat();
		}
		catch(Exception e){
			System.out.println("Couldn't connect to server.... Exiting.");
		}
	}
	
	public void startChat()throws IOException
	{
			while(true)
			{
				String logs = din.readUTF();
				if(logs.compareTo("") != 0){
					System.out.println(logs);
				}
			}
	}
	
	public static void main(String ... s)
	{
		new MyClient();
	}
}