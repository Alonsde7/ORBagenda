package Server;

import Interfaces.ITime;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Time implements ITime {
    @Override
    public int getHour() {
        return new GregorianCalendar().get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public int getMinute() {
        return new GregorianCalendar().get(Calendar.MINUTE);
    }

    @Override
    public int getSeconds() {
        return new GregorianCalendar().get(Calendar.SECOND);
    }
}
