package fr.mercury.investbridge.database.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;

/*
 @Author Anto' | SwartZ#0001
 @create 23/07/2021
 */
public class MongoConnection {

    private String name;
    private MongoClient mongoClient;

    public MongoConnection(MongoCredentials mongoCredentials) {

        this.name = mongoCredentials.getName();

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(new CodecRegistry[]{MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(new CodecProvider[]{PojoCodecProvider.builder().automatic(true).build()})});
        MongoClientSettings.builder().codecRegistry(pojoCodecRegistry).build();

        this.mongoClient = new MongoClient(new ServerAddress(mongoCredentials.getHost(), mongoCredentials.getPort()));
    }

    public String getName() {
        return this.name;
    }

    public MongoClient getMongoClient() {
        return this.mongoClient;
    }
}
