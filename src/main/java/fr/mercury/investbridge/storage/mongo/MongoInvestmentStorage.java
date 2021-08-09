package fr.mercury.investbridge.storage.mongo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import fr.mercury.investbridge.database.MongoDBDatabase;
import fr.mercury.investbridge.storage.InvestmentStorage;
import fr.mercury.investbridge.storage.UserInvestment;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Optional;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */
public class MongoInvestmentStorage extends InvestmentStorage<MongoDBDatabase> {

    private MongoCollection<Document> collection;

    public MongoInvestmentStorage(MongoDBDatabase database) {
        super(database);
//        database = MongoClients.create().getDatabase("investment");
//        collection = database.getCollection("investment");
    }

    @Override
    public Optional<UserInvestment> getUser(String username) {
        return Optional.empty();
    }

    @Override
    public void save(UserInvestment user) {

    }

    @Override
    public boolean hasAccount(String name) {
        return false;
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
