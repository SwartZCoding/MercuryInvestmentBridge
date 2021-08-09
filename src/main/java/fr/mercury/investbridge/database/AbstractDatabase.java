package fr.mercury.investbridge.database;

import fr.mercury.investbridge.database.sql.DatabaseCredentials;

/*
 @Author Anto' | SwartZ#0001
 @create 30/07/2021
 */
public abstract class AbstractDatabase {

    private DatabaseCredentials credentials;

    public AbstractDatabase(DatabaseCredentials credentials) {
        this.credentials = credentials;

        this.connectDatabase();
    }

    protected DatabaseCredentials getCredentials() {

        return this.credentials;
    }

    public void connectDatabase() {
        if (this.isConnected())
            return;

        try {

            this.connect();
            System.out.println("[Base de donnée] Base de donnée ciblant " + credentials.getDatabase() + " connectée avec succès!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnectDatabase() {
        if (!this.isConnected())
            return;

        try {
            this.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract boolean isConnected();

    protected abstract void connect() throws Exception;

    protected abstract void disconnect() throws Exception;

    public abstract DatabaseType getDatabaseType();
}
