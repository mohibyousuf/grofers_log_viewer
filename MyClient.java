import java.io.*;
import java.net.*;

class MyClient
{
	Socket s = null;
	DataInputStream din = null;
	MyClient()
	{
		try
		{
			s = new Socket("localhost", 5000);
			din = new DataInputStream(s.getInputStream());
			startChat();
		}
		catch(Exception e){}
	}
	
	void startChat()throws IOException
	{
			while(true)
			{
				String logs = din.readUTF();
				if(logs.compareTo("") != 0){
					System.out.println("SERVER : "+ logs);
				}
			}
	}
	
	public static void main(String ... s)
	{
		new MyClient();
	}
}