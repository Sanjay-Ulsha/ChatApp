import java.io.*;
import java.net.*;

public class Client 
{
	public static void main(String...n)
	{
		Socket s2=null;
		try
		{
			System.out.print("Enter the same port Number: ");
			int port=new java.util.Scanner(System.in).nextInt();
			
			System.out.print("Enter the IP: ");
			String ip=new java.util.Scanner(System.in).next();
			
			s2=new Socket(ip,port);
			
			DataInputStream in=new DataInputStream(s2.getInputStream());
			DataOutputStream out=new DataOutputStream(s2.getOutputStream());
			
			BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
			
			String min="",mout="";
			
			while(!min.equals("stop"))
			{
				mout=b.readLine();
				out.writeUTF(mout);
				min=in.readUTF();
				System.out.println(min);
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
				if(s2!=null) s2.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
		}
	}
}
