package Server;

import Interfaces.ISkeleton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class SkeletonAgenda implements ISkeleton {

    private int keyusadas = 0;
    private final Hashtable<Integer, Agenda> LISTA_DE_AGENDAS = new Hashtable<>();

    @Override
    public void process(DataInputStream di, DataOutputStream dOut) throws IOException {

        int opcion = di.readInt();

        switch (opcion) {
            case 1 -> inicializacion(dOut);
            case 2 -> escritura(di, dOut);
            case 3 -> lectura(di, dOut);
            default -> {
                System.out.println("Código de operación desconocido");
                dOut.writeBoolean(false);
            }
        }
    }

    /**
     * Crea una agenda, la añade a la lista y envía al usuario el ID de la Agenda.
     */
    private synchronized void inicializacion(DataOutputStream dOut) throws IOException {

        dOut.writeInt(keyusadas);
        dOut.writeBoolean(true);

        LISTA_DE_AGENDAS.put(keyusadas, new Agenda());
        keyusadas++;
    }

    /**
     * Escribe en la agendaID que recibe por el socket, en caso de que no reciba agenda devuelve falso
     */
    private synchronized void escritura(DataInputStream di, DataOutputStream dOut) throws IOException {

        int agendaId = di.readInt();
        String nombre = di.readUTF();
        int numTelefono = di.readInt();

        if (LISTA_DE_AGENDAS.containsKey(agendaId)) {
            dOut.writeBoolean(true);
            LISTA_DE_AGENDAS.get(agendaId).write(nombre, numTelefono);

        } else dOut.writeBoolean(false);
    }

    /**
     * Escribe por el socket el número correspondiente a la agenda y a la clave que recibe,
     * en caso de que no exista alguna de las dos, el usuario recibe un número no válido y un mensaje de falso
     */
    private void lectura(DataInputStream di, DataOutputStream dOut) throws IOException {

        //Leemos tanto el IDAgenda como la clave del nombre a buscar
        int agendaId = di.readInt();
        String nombre = di.readUTF();

        try {
            dOut.writeInt(LISTA_DE_AGENDAS.get(agendaId).read(nombre));
            dOut.writeBoolean(true);

        } catch (NullPointerException e) { //En caso de que no exista
            dOut.writeInt(Integer.MIN_VALUE);
            dOut.writeBoolean(false);
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getIid() {
        return 1;
    }
}
