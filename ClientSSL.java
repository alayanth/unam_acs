
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class ClientSSL {
  public static void main(String[] argv) throws Exception {
    int port = 8000;
    String hostname = "localhost";
    SocketFactory socketFactory = SSLSocketFactory.getDefault();
    Socket socket = socketFactory.createSocket(hostname, port);


    InputStream in = socket.getInputStream();
    OutputStream out = socket.getOutputStream();

    // Read from in and write to out...

    in.close();
    out.close();
  }
}