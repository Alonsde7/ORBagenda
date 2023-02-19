package Client;

import Interfaces.ITime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Time implements ITime {

    final private String PROXY = "localhost";
    final private int PORT = 10000;
    private final int COD_SELEC_OBJECT = 2;


    @Override
    public int getHour() throws IOException {
        Socket socket = new Socket(PROXY, PORT);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeInt(COD_SELEC_OBJECT); // Selección de objeto
        dos.writeInt(1); // Opción de hora

        int hora = dis.readInt();
        dis.readBoolean(); //recibo booleano de confirmación

        dis.close();
        dos.close();
        socket.close();

        return hora;
    }

    @Override
    public int getMinute() throws IOException {
        Socket socket = new Socket(PROXY, PORT);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeInt(COD_SELEC_OBJECT); // Selección de objeto
        dos.writeInt(2); // Opción de minuto

        int minuto = dis.readInt();
        dis.readBoolean(); //recibo booleano de confirmación

        dis.close();
        dos.close();
        socket.close();

        return minuto;
    }

    @Override
    public int getSeconds() throws IOException {
        Socket socket = new Socket(PROXY, PORT);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeInt(COD_SELEC_OBJECT); // Selección de objeto
        dos.writeInt(3); // Opción de hora

        int segundo = dis.readInt();
        dis.readBoolean(); //recibo booleano de confirmación

        dis.close();
        dos.close();
        socket.close();

        return segundo;
    }
}
