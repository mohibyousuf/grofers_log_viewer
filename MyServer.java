import java.io.*;
import java.net.*;

class MyServer
{
	ServerSocket ss;
	Socket s;
	// DataInputStream din;
	DataOutputStream dout;
	MyServer()
	{
		System.out.println("SERVER STARTED..WAITING FOR CLIENT..");
		try
		{
			ss = new ServerSocket(5000);	//specific port is the argument
			s=ss.accept();				//to establish a connection
			System.out.println("SOCKET CONNECTED : "+s);
			dout=new DataOutputStream(s.getOutputStream());
			startChat();
		}
		catch(Exception e)
		{}
	}//constructor
	
	void startChat()throws IOException
	{
		String str = null;
		File file = new File("/Users/mohib/dev/housing.projects/log/development.log");
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

	String getOutputLines(String [] lines, int count){
		String dumpString = "";
		if(count > 0){
			System.out.println("COUNT after reading:" + count);
			System.out.println(count);
			System.out.println(lines[0]);
		}
		if(count >=10){
			for(int i=0;i<10;i++){
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
		new MyServer();
	}
}