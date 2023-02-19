package Server;

import Interfaces.ISkeleton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MiniORB {

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    private final Hashtable<Integer, ISkeleton> SK_TABLE = new Hashtable<>();
    private final int PORT;

    public MiniORB(int port) {
        this.PORT = port;
    }

    public synchronized void addInterface(ISkeleton sk) {
        SK_TABLE.put(sk.getIid(), sk);
    }

    public synchronized ISkeleton getInterface(int iid) {
        return SK_TABLE.get(iid);
    }

    public void server() throws IOException {

        ServerSocket ss = new ServerSocket(PORT);
        final int NUMTHREADS = 5;
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(NUMTHREADS);

        while (true) {
            socket = ss.accept();
            // Por cada nueva conexión creo un thread
            threadPoolExecutor.execute(
                    new Thread(() -> {
                        try {
                            dis = new DataInputStream(socket.getInputStream());
                            dos = new DataOutputStream(socket.getOutputStream());

                            getInterface(dis.readInt()).process(dis, dos);

                            //cierre de entrada/salida y de socket
                            dis.close();
                            dos.close();
                            socket.close();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
            );
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 10000;
        MiniORB orb;

        orb = new MiniORB(port);

        orb.addInterface(new SkeletonAgenda());
        orb.addInterface(new SkeletonTime());

        orb.server();
    }
}
