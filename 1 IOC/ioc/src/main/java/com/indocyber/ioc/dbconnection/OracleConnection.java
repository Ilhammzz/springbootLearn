package com.indocyber.ioc.dbconnection;

import org.springframework.stereotype.Component;

public class OracleConnection implements DatabaseConnection{
    @Override
    public void connect() {
        System.out.println("ORACLE CONNECTION");
    }
}
