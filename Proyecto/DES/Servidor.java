import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.*;

import javax.crypto.*;
import java.security.*;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;


public class Servidor {

    public static void main(String a[]) throws Exception{
        ServerSocketFactory socketFactory = SSLServerSocketFactory.getDefault();
        ServerSocket serverSocket = null;
        Socket socket = null;
        int port = 1212;
        //recepcion del dato del cliente
        String peticion = "";
        // Respuesta es lo que le respondo al Cliente
        String respuesta = "";
        //comando del cliente codificada
        String accion = "";
        String cantidad = "";
        double saldo;

        
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        SecretKey myDesKey = keygenerator.generateKey();
        
        // Create the cipher 
        Cipher desCipher;
        desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        //guarda la clave
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("secret.key"));
        out.writeObject( myDesKey );
        out.close(); 
        

        

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor inicializado");
            System.out.println("Usando el puerto: "+port);
            //serverSocket = socketFactory.createServerSocket(port);
        } catch (IOException e) {
            System.out.println("java.io.IOException generada");
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("Escuchando");
                socket = serverSocket.accept();
                System.out.println("Cliente: " + socket.getInetAddress()+ " "+socket.getInetAddress().getHostName()+ " conectado");
                //flujos de entrada y salida del servidor
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                // manejo de los comandos del cajero
                dos.writeUTF("  ");
                do {
                    // Acciones que realiza el cajero
                    dos.writeUTF("");
                    respuesta = accion = cantidad = "";
                    peticion = dis.readUTF();
                    System.out.println(peticion);
                    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
                    byte[] text = peticion.getBytes();
                    byte[] textDecrypted = desCipher.doFinal(text);
                    peticion = new String(textDecrypted);
                                
                    System.out.println("Operación recibida: " + peticion);
                    StringTokenizer st = new StringTokenizer(peticion, " ");
                    int n = 3;
                    switch (st.countTokens()) {//permite determinar la cantidad de datos ingresados por el cliente
                        case 1://si solo se ingresa un dato
                            accion = st.nextToken();
                            switch (accion) {
                                case "consultar" : case "CONSULTAR":
                                    //lee del archivo
                                    BufferedReader br = new BufferedReader(new FileReader("cuenta.txt"));
                                    StringTokenizer tmp = new StringTokenizer(br.readLine());
                                    br.close();
                                    String[] cuentadatos = new String[n];
                                    for (int i = 0; i < n; i++) {
                                        cuentadatos[i] = tmp.nextToken();
                                    }
                                    respuesta = "\n/-------->:" 
                                                + "\nNombre del cliente: " + cuentadatos[0] 
                                                + "\nNúmero de cuenta: " + cuentadatos[1] 
                                                + "\nBalance: " + cuentadatos[2];
                                    break;
                                case "salir": case "SALIR":
                                    respuesta = "salir";
                                    break;
                                default:
                                    respuesta = "Operación no valida";
                            }
                            break;
                        case 2://si se ingresa dos datos
                            accion = st.nextToken();
                            cantidad = st.nextToken();
                            switch (accion) {
                                case "depositar": case "DEPOSITAR":
                                    try {
                                        saldo = Double.parseDouble(cantidad);
                                    } catch (NumberFormatException nfe) {
                                        System.out.println("No ingresaste un número o cantidad correcta.");
                                        saldo = 0.0;
                                        respuesta = "Introduzca una cantidad a depositar correcta\n";
                                    }
                                    if (saldo > 0.0) {
                                        BufferedReader br = new BufferedReader(new FileReader("cuenta.txt"));
                                        StringTokenizer tmp = new StringTokenizer(br.readLine());
                                        br.close();
                                        String[] datos = new String[n];
                                        for (int i = 0; i < n; i++) {
                                            datos[i] = tmp.nextToken();
                                        }
                                        saldo += Double.parseDouble(datos[2]);
                                        datos[2] = String.valueOf(saldo);
                                        FileWriter fw = new FileWriter("cuenta.txt");
                                        fw.write(datos[0] + " " + datos[1] + " " + datos[2]);
                                        fw.close();

                                        br = new BufferedReader(new FileReader("cuenta.txt"));
                                        tmp = new StringTokenizer(br.readLine());
                                        br.close();
                                        String[] cuentadatos = new String[n];
                                        for (int i = 0; i < n; i++) {
                                            cuentadatos[i] = tmp.nextToken();
                                        }
                                        respuesta = "\n/-------->:" 
                                                + "\nNombre del cliente: " + cuentadatos[0] 
                                                + "\nNúmero de cuenta: " + cuentadatos[1] 
                                                + "\nBalance: " + cuentadatos[2];

                                    } else {
                                        respuesta += "Deposito no válido";
                                    }
                                    break;
                                case "retirar": case "RETIRAR":
                                    try {
                                        saldo = Double.parseDouble(cantidad);
                                    } catch (NumberFormatException nfe) {
                                        System.out.println("Cantidad no numerica.");
                                        saldo = 0.0;
                                        respuesta = "Introduzca una cantidad a retirar correcta\n";;
                                    }
                                    if (saldo > 0.0) {
                                        BufferedReader br2 = new BufferedReader(new FileReader("cuenta.txt"));
                                        StringTokenizer tmp2 = new StringTokenizer(br2.readLine());
                                        br2.close();
                                        String[] cuentadatos2 = new String[n];
                                            for (int i = 0; i < n; i++) {
                                                cuentadatos2[i] = tmp2.nextToken();
                                            }
                                        double saldotemp = Double.parseDouble(cuentadatos2[2]);
                                            if (saldotemp >= saldo) {
                                                saldotemp -= saldo;
                                                cuentadatos2[2] = String.valueOf(saldotemp);
                                                FileWriter fw2 = new FileWriter("cuenta.txt");
                                                fw2.write(cuentadatos2[0] + " " + cuentadatos2[1] + " " + cuentadatos2[2]);
                                                fw2.close();
                                          
                                                BufferedReader  brr = new BufferedReader(new FileReader("cuenta.txt"));
                                                StringTokenizer tmpp = new StringTokenizer(brr.readLine());
                                                brr.close();
                                                String[] cuentadatos = new String[n];
                                                for (int i = 0; i < n; i++) {
                                                    cuentadatos[i] = tmpp.nextToken();
                                                }
                                                respuesta = "\n/-------->:" 
                                                        + "\nNombre del cliente: " + cuentadatos[0] 
                                                        + "\nNúmero de cuenta: " + cuentadatos[1] 
                                                        + "\nBalance: " + cuentadatos[2];        
                                            } else {
                                                respuesta = "Tu cuenta no tiene saldo suficiente";
                                            }
                                    } else {
                                        respuesta = "Operacion invalida";
                                    }
                                    break;
                                default:
                                    respuesta = "Falta un argumento a tu petición";
                            }
                            break;
                        default://si se ingresa una cantidad diferente
                            respuesta = "Operacion invalida";
                    }
                    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                    text = respuesta.getBytes();
                    byte[] textEncrypted = desCipher.doFinal(text);
                    //dos.writeUTF(respuesta);
                    dos.write(textEncrypted);
                } while (!respuesta.equals("salir"));
                dos.writeUTF("\nAltamirano Peralta David"
                            +"\nTorres Sánchez Ángel Antonio"
                            +"\n"
                            +"\nFacultad de Ingeniería"
                            +"\nArquitectura Cliente-Servidor");
                dos.close();
                dis.close();
                socket.close();
            } catch (SocketException se) {
                System.out.println("Desconexion");
                se.printStackTrace();
                return;
            } catch (IOException e) {
                System.out.println("java.io.IOException generada");
                e.printStackTrace();
            } catch (Exception ee) {
                System.out.println("Bad termination of program");
                ee.printStackTrace();
                return;
            }
        }
    }
}
