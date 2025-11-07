package com.indocyber.ioc.dbconnection;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final DatabaseConnection databaseConnection;

    public DatabaseService(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

//    public List<Object> getAll(){
//        return databaseConnection.connect("SELECT * FROM Category");
//    }
}
