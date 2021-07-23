package fr.mercury.investbridge.database.sql;

import lombok.Getter;

@Getter
public class DatabaseCredentials {

    private String host, database, username, password;
    private int port;

    public DatabaseCredentials() {
        this.host = "localhost";
        this.database = "investment";
        this.username = "root";
        this.password = "password";
        this.port = 3306;
    }
}
