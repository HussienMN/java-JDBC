package com.company;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
	// write your code here
        Connection conn = null;
        Statement stm = null;
        ResultSet resultSet = null;

        try {
            conn = multiDBconnection.connectionToMultiDB("db1");
            System.out.println("erfolgreich verbunden!");
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//            String query = "SELECT * FROM `provinces`";
//            resultSet = stm.executeQuery(query);
//            resultSet.last();
//            System.out.println(resultSet.getRow());

            readData rd = new readData();
//            rd.printDataOfEmployee();
//            rd.printDataOfProvinz();
//            rd.CreateTable();
//            rd.insertDataInTable();
//            rd.UpdateDataInTable();
            crudUsingPrepStatments crud = new crudUsingPrepStatments();
//            crud.insertData();
//            crud.updateData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
