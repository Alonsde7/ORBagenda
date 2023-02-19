package Interfaces;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface ISkeleton {
    void process(DataInputStream di, DataOutputStream dOut) throws IOException;

    int getIid();
}
