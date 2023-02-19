package Client;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {

        Agenda agenda = new Agenda();
        Agenda agendaTelefono = new Agenda();
        Time time = new Time();

        agenda.write("pepe", 888888889);
        agenda.write("antonia", 888888888);

        agendaTelefono.write("pepe", 877777779);
        agendaTelefono.write("antonia", 877777778);

        System.out.println("El numero de pepe en agenda es: " + agenda.read("pepe"));
        System.out.println("El numero de antonia en agenda es: " + agenda.read("antonia"));

        System.out.println("El número de pepe en agendatelefono es: " + agendaTelefono.read("pepe"));
        System.out.println("El número de antonia en agendaTelefono es: " + agendaTelefono.read("antonia"));
        System.out.println("El número de noExiste en agendaTelefono es: " + agendaTelefono.read("noExiste"));

        System.out.println(" La hora del servidor es: "
                + time.getHour() + ":"
                + time.getMinute() + ":"
                + time.getSeconds());
    }
}