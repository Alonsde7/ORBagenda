package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Interfaces.IAgenda;

public class Agenda implements IAgenda {

    private final String HOST = "localhost";
    private final int PORT = 10000;
    private final int ID_AGENDA;
    private final int COD_SELEC_OBJECT = 1;

    /**
     * Establece conexión con el servidor, en el host y puerto especificado
     * envía el código de operación 1, espera a la recepción por parte del servidor
     * cierra los puertos.
     */
    public Agenda() throws IOException {

        // Variables

        Socket socket;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;

        // Inicialización de variables

        socket = new Socket(HOST, PORT);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());

        // Envío y recepción de mensajes

        dataOutputStream.writeInt(COD_SELEC_OBJECT); // Selección de Object
        dataOutputStream.writeInt(1); // Opción de inicialización
        ID_AGENDA = dataInputStream.readInt();
        dataInputStream.readBoolean();

        // Cierre de variables
        close(socket, dataInputStream, dataOutputStream);

    }

    /**
     * Establece la conexión en el host y puerto especificado en la inicialización
     * envía el código de operación 2 al servidor, seguido de la clave y del número
     * espera a la confirmación del servidor
     */
    @Override
    public void write(String key, int d) throws IOException {

        // Variables
        Socket socket;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;

        // Inicialización de variables
        socket = new Socket(HOST, PORT);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());

        // Envío y recepción
        dataOutputStream.writeInt(COD_SELEC_OBJECT); // Selección de objeto
        dataOutputStream.writeInt(2); // Selección escritura
        dataOutputStream.writeInt(ID_AGENDA);
        dataOutputStream.writeUTF(key);
        dataOutputStream.writeInt(d);
        dataInputStream.readBoolean();

        // Cierre de variables
        close(socket, dataInputStream, dataOutputStream);

    }

    /**
     * Establece la conexión en el host y puerto especificado en la inicialización
     * Envía el código de operación 3 al servidor y la clave de la agenda
     * Espera respuesta de lectura y respuesta del servidor
     */
    @Override
    public int read(String key) throws IOException {

        // Variables
        int result;
        Socket socket;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;

        // Inicialización de variables
        socket = new Socket(HOST, PORT);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());

        // Envío y recepción de mensajes
        dataOutputStream.writeInt(COD_SELEC_OBJECT); // Selección de objeto
        dataOutputStream.writeInt(3); // Selección de lectura
        dataOutputStream.writeInt(ID_AGENDA);
        dataOutputStream.writeUTF(key);
        result = dataInputStream.readInt();
        dataInputStream.readBoolean();

        close(socket, dataInputStream, dataOutputStream);

        return result;
    }

    /**
     * Cierre de variables que se pasen por parámetro
     */
    private static void close(Socket sc, DataInputStream di, DataOutputStream dou) throws IOException {

        if (sc != null) sc.close();
        if (di != null) di.close();
        if (dou != null) dou.close();

    }
}
