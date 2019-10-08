import java.io.*;
import java.net.*;

class MyServer
{
	private final int SERVER_SOCKET_PORT = 5000;
	private ServerSocket ss;
	private Socket s;
	private DataOutputStream dout;
	MyServer(String filePath)
	{
		System.out.println("SERVER STARTED..WAITING FOR CLIENT..");
		try
		{
			ss = new ServerSocket(SERVER_SOCKET_PORT);	//specific port is the argument
			s = ss.accept();				//to establish a connection
			System.out.println("SOCKET CONNECTED : " + s);
			dout=new DataOutputStream(s.getOutputStream());
			startChat(filePath);
		}
		catch(Exception e)
		{}
	}//constructor
	
	public void startChat(String filePath)throws IOException
	{
		String str = null;
		File file = new File(filePath);
		FileReader fr = new FileReader(file);
		BufferedReader readFile = new BufferedReader(fr);
		String [] lines = new String[10];

		int count = 0;
		String line = null;
		while ((line = readFile.readLine()) != null) {
			lines[count % lines.length] = line;
		    count++;
		}
		dout.writeUTF(getOutputLines(lines, count));
		while(true)
		{	
			count = 0;
			while (count != 10 && (line = readFile.readLine()) != null) {
				lines[count % lines.length] = line;
		    	count++;
			}
			dout.writeUTF(getOutputLines(lines, count));
			dout.flush();
		}
	}

	public String getOutputLines(String [] lines, int count){
		String dumpString = "";
		// handling first case
		if(count > 10){
			count = count % 10;
			for(int i=count;i<10;i++){
				dumpString += lines[i];
				dumpString += "\n";
			}
			for(int i = 0; i<count;i++){
				dumpString += lines[i];
				dumpString += "\n";
			}
		}
		else{
			for(int i=0;i<count;i++){
				dumpString += lines[i];
				dumpString += "\n";
			}
		}
		return dumpString;
	}
	
	public static void main(String... s)
	{
		if(s.length == 0)
			System.out.println("No file path provided...please provide a file path");
		else
			new MyServer(s[0]);
	}
}