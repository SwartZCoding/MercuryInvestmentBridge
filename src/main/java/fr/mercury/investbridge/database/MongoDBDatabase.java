package fr.mercury.investbridge.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import fr.mercury.investbridge.database.sql.DatabaseCredentials;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;

/*
 @Author Anto' | SwartZ#0001
 @create 30/07/2021
 */
public class MongoDBDatabase extends AbstractDatabase {

    private MongoClient client;
    private MongoDatabase database;

    public MongoDBDatabase(DatabaseCredentials credentials) {
        super(credentials);
    }

    @Override
    public boolean isConnected() {
        return this.database != null;
    }

    @Override
    protected void connect() {
        DatabaseCredentials credentials = this.getCredentials();

        MongoCredential credential = MongoCredential.createCredential(credentials.getUsername(), credentials.getDatabase(), credentials.getPassword().toCharArray());
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClientSettings.builder().codecRegistry(pojoCodecRegistry).build();

    //    this.client = new MongoClient(new ServerAddress(credentials.getHost(), credentials.getPort()), Arrays.asList(credential));
        this.database = this.client.getDatabase(credentials.getDatabase());
    }

    @Override
    protected void disconnect() throws Exception {
        this.client.close();
    }

    @Override
    public DatabaseType getDatabaseType() {

        return DatabaseType.MONGODB;
    }

    public MongoDatabase getMongo() {
        return this.database;
    }
}
