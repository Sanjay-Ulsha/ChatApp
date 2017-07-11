import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
public class Client implements ActionListener 
{
	static Frame f;
	static TextArea ta1; static TextField t; Button b;
	static Socket s2;
	static DataInputStream in;
	static DataOutputStream out;
	static String min="",mout="";
	Client()
	{
		f=new Frame("Client");
		f.setVisible(true);
		f.setSize(390,290);
		f.setBackground(Color.cyan);
		f.setLayout(new FlowLayout());
		ta1=new TextArea("",10,40,TextArea.SCROLLBARS_BOTH);
		t=new TextField(30);
		b=new Button("Send"); b.addActionListener(this);
		f.add(ta1); f.add(t); f.add(b);
		
		f.addWindowListener(new WindowAdapter(){ 
												public void windowClosing(WindowEvent w)
												{
													System.exit(1);
													try
													{
														if(s2!=null) s2.close();
													}
													catch(Exception l)
													{
														l.printStackTrace();
													}
												} });
		
	}
	public static void main(String...n)
	{
		new Client();
		
		try
		{
			int port=Integer.parseInt(JOptionPane.showInputDialog(f,"Enter the port Number?"));
			String ip=JOptionPane.showInputDialog(f,"Enter the IP address?");
			s2=new Socket(ip,port);
			
			in=new DataInputStream(s2.getInputStream());
			out=new DataOutputStream(s2.getOutputStream());
			
			while(true)
			{
				min=in.readUTF();
				//ta1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				ta1.append("\n Server : "+min);
			}
			
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
	}
	 public void actionPerformed(ActionEvent o)
	{ 
		       try
			   { 
			    mout=t.getText();
				out.writeUTF(mout);
				//ta1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				ta1.append("\n Client(Me) : "+mout);
				t.setText("");
			   }
			   catch(Exception h)
			   { h.printStackTrace(); }
	}
}
