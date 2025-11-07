package com.indocyber.ioc.dbconnection;

import org.springframework.stereotype.Component;

public class SqlServerConnection implements DatabaseConnection {

    @Override
    public void connect() {
        System.out.println("SQL SERVER CONNECTION");
    }
}
