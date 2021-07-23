package fr.mercury.investbridge.storage.mongo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import fr.mercury.investbridge.storage.InvestUser;
import fr.mercury.investbridge.storage.InvestmentStorage;
import org.bson.Document;

import java.math.BigDecimal;
import java.util.Optional;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */
public class MongoInvestmentStorage implements InvestmentStorage {

    private MongoCollection<Document> collection;

    public MongoInvestmentStorage() {
        MongoDatabase database = MongoClients.create().getDatabase("investment");
        collection = database.getCollection("investment");
    }

    @Override
    public Optional<InvestUser> getUser(String username) {
        return Optional.empty();
    }

    @Override
    public boolean hasAccount(String name) {
        return false;
    }

    @Override
    public void save(InvestUser user) {

    }

    @Override
    public Optional<BigDecimal> getMoney(String username) {

        Document document = collection.find().filter(Filters.eq("username", username)).first();

    //    return Optional.ofNullable(document).ifPresent(userDocument -> userDocument.getLong());

        return Optional.empty();
    }

    @Override
    public void addMoney(String username) {


    }
}
