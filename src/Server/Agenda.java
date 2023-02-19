package Server;

import java.util.Hashtable;

import Interfaces.IAgenda;

public class Agenda implements IAgenda {

    private final Hashtable<String, Integer> hashtable = new Hashtable<>();

    @Override
    public void write(String key, int d) {
        hashtable.put(key, d);
    }

    @Override
    public int read(String key) {
        return hashtable.get(key);
    }
}
