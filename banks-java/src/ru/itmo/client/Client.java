package ru.itmo.client;

import java.util.UUID;

public class Client {
    private String name;
    private String surname;
    private String address;
    private String passport;

    public Client(String clientName, String clientSurname) {
        name = clientName;
        surname = clientSurname;
        address = "Default address";
        passport = "Default passport";
    }

    public Client(String clientName, String clientSurname, String clientAddress, String clientPassport) {
        name = clientName;
        surname = clientSurname;
        address = clientAddress;
        passport = clientPassport;
    }


}
