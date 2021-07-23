package fr.mercury.investbridge.database.sql;

import lombok.Getter;
import lombok.Setter;

/*
 @Author Anto' | SwartZ#0001
 @create 16/07/2021
 */
@Getter
@Setter
public class ConfigDatabase {

    private DatabaseCredentials websiteCredentials;

    public ConfigDatabase() {
        this.websiteCredentials = new DatabaseCredentials();
    }
}
