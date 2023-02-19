package Interfaces;

import java.io.IOException;

public interface IAgenda {

    /**
     * Asings a value d to a string key
     *
     * @param key Clave de hashtable
     * @param d   Parámetro de hashtable
     */
    void write(String key, int d) throws IOException;

    /**
     * @param key Clave de hash table
     * @return Valor de la clave en el hashtable
     */
    int read(String key) throws IOException;

}
