import java.io.*;
import java.net.*;

public class Server 
{
	public static void main(String...n)
	{
		ServerSocket s1=null; Socket s2=null;
		try
		{
			System.out.print("Enter the port Number: ");
			int port=new java.util.Scanner(System.in).nextInt();
			
			s1=new ServerSocket(port);
			s2=s1.accept();
			
			DataInputStream in=new DataInputStream(s2.getInputStream());
			DataOutputStream out=new DataOutputStream(s2.getOutputStream());
			
			BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
			
			String min="",mout="";
			
			while(min.equals("stop"))
			{
				min=in.readUTF();
				System.out.println(min);
				mout=b.readLine();
				out.writeUTF(mout);
				out.flush();
			}
			
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		finally
		{
			try
			{
				if(s1!=null) s1.close();
				if(s2!=null) s2.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
		}
	}
}
