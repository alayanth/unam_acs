import java.util.*;
import java.io.*;
import java.net.*;

import javax.crypto.*;
import java.security.*;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class Cliente {

    public static void main(String a[]) throws Exception{
        SocketFactory socketFactory = SSLSocketFactory.getDefault();
        Socket socket = null;
        String peticion = "";
        String respuesta = "";
        String serverName;
        int puerto;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        ObjectInput in = new ObjectInputStream(new FileInputStream("secret.key"));
        SecretKey myDesKey = (SecretKey)in.readObject();
        in.close();
        
        Cipher desCipher;

        // Create the cipher 
        desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    
        
        // Initialize the same cipher for decryption
        desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
        desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
    

        try {

            System.out.println("A que servidor desea conectarse: ");
            serverName = reader.readLine();
            System.out.println("Ahora ingrese el puerto de conexión");
            puerto = Integer.parseInt(reader.readLine());
            System.out.println("Conectando al servidor " + serverName + " en el puerto " + puerto);

            socket = new Socket(serverName,puerto);
            //socket = socketFactory.createSocket(serverName, puerto);

            System.out.println("Acceso correcto al BankServer");
            // Como ya hay socket, obtengo los flujos asociados a este
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            // Ya que me conecte con el Servidor, debo leer del teclado para despues eso mismo enviarlo al Servidor
            System.out.println(dis.readUTF());//se muestra un mensaje de bienvenida

            System.out.println(" ****************Bienvenido a BANCORP***************");
            //haciendo la primera consulta
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (!respuesta.equals("salir")) {
                System.out.println("\n");
                System.out.println("============");
                System.out.println("Por favor introduzca alguna de las siguientes opciones: \n"
                    +"*->CONSULTAR\n"
                    +"*->DEPOSITAR <cantidad>\n"
                    +"*->RETIRAR <cantidad>\n"
                    +"*->SALIR");
                //envio de la transaccion
                System.out.print(":> ");
                peticion = br.readLine();

                desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                byte[] text = peticion.getBytes();
                byte[] textEncrypted = desCipher.doFinal(text);
                //dos.write(textEncrypted);
                dos.write(textEncrypted,0,textEncrypted.length);
                System.out.println(textEncrypted);
                //dos.writeUTF(peticion);


                // respuesta del cajero
                respuesta = dis.readUTF();
                System.out.println(respuesta);
                desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
                text = respuesta.getBytes();
                byte[] textDecrypted = desCipher.doFinal(text);
                respuesta = new String(textDecrypted);
                //System.out.println(respuesta);
            }
            System.out.println(dis.readUTF());//se muestra un mensaje de despedida
            dos.close();
            dis.close();
            socket.close();
        } catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println("mal uso de la aplcacion");
            return;
        } catch (SocketException se) {
            System.out.println("Puerto incorrecto.");
            return;
        } catch (IOException e) {
            System.out.println("IOException generada");
            e.printStackTrace();

        } catch (Exception ee) {
            System.out.println("Aplicacion interrumpida");
            return;
        }

    }
}
