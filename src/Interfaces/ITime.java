package Interfaces;

import java.io.IOException;

public interface ITime {
    int getHour() throws IOException;
    int getMinute() throws IOException;
    int getSeconds() throws IOException;
}
