import java.io.*;
import java.net.*;

public class CGI {
	public CGI()
	{
	}

	public String Post(String host, int port, String cmd) 
	{
		Socket           sock;
		OutputStream     outp;
		DataOutputStream dataout;
		InputStream      inp;
		DataInputStream  datain;

		try{
			sock = new Socket(host,port);
			outp = sock.getOutputStream();
			dataout = new DataOutputStream(outp);
			inp  = sock.getInputStream();
			datain  = new DataInputStream(inp);

			String script = "/cgi-bin/ducer.cgi";
			String ctype  = "application/octet-stream";

			// HTTP header
			dataout.writeBytes("POST " + script + " HTTP/1.0\r\n");
			dataout.writeBytes("Content-type: " + ctype + "\r\n");
			dataout.writeBytes("Content-length: " + cmd.length() + "\r\n");
			dataout.writeBytes("\r\n");         // end of header

			dataout.writeBytes(cmd);
			dataout.writeBytes("\r\n");

			String line;
			boolean body = false;
			String rdata = "";

			while ((line = datain.readLine()) != null)
			{
				if (body)
					rdata += "\n" + line;
				else if (line.equals(""))       // end of header
					body = true;
			}
				
			dataout.close();
			datain.close();
			sock.close();

			return rdata.trim();
		}
		catch (Exception e){
		      //  System.out.println(e);
			return "error:" + e;
		}
	}
	public String Post(String host, int port,String oscript, String cmd) 
	{
		String script =oscript;
		Socket           sock;
		OutputStream     outp;
		DataOutputStream dataout;
		InputStream      inp;
		DataInputStream  datain;

		try{
			sock = new Socket(host,port);
			outp = sock.getOutputStream();
			dataout = new DataOutputStream(outp);
			inp  = sock.getInputStream();
			datain  = new DataInputStream(inp);

			script = "/cgi-bin/" + script;
			String ctype  = "application/octet-stream";

			// HTTP header
			dataout.writeBytes("POST " + script + " HTTP/1.0\r\n");
			dataout.writeBytes("Content-type: " + ctype + "\r\n");
			dataout.writeBytes("Content-length: " + cmd.length() + "\r\n");
			dataout.writeBytes("\r\n");         // end of header

			dataout.writeBytes(cmd);
			dataout.writeBytes("\r\n");

			String line;
			boolean body = false;
			String rdata = "";

			while ((line = datain.readLine()) != null)
			{
				if (body)
					rdata += "\n" + line;
				else if (line.equals(""))       // end of header
					body = true;
			}
				
			dataout.close();
			datain.close();
			sock.close();

			return rdata.trim();
		}
		catch (Exception e){
		      //  System.out.println(e);
			return "error:" + e;
		}
	}

}
