package Server;

import Interfaces.ISkeleton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SkeletonTime implements ISkeleton {

    private final Time time = new Time();

    @Override
    public void process(DataInputStream di, DataOutputStream dOut) throws IOException {

        int opcion = di.readInt();

        switch (opcion) {
            case 1 -> dOut.writeInt(time.getHour());
            case 2 -> dOut.writeInt(time.getMinute());
            case 3 -> dOut.writeInt(time.getSeconds());
        }
        dOut.writeBoolean(true);
    }

    @Override
    public int getIid() {
        return 2;
    }
}
