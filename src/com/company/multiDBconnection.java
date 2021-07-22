package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class multiDBconnection {
    static private String username = "root";
    static private String password = "";
    static private String con_string_db_1 = "jdbc:mysql://localhost/ muhammedessahameed";
    static private String con_string_db_2 =  "jdbc:mysql://localhost/ muhammedessa";
    static private String db1 = "db1";
    static private String db2 = "db2";

    public static Connection connectionToMultiDB (String str) throws SQLException {
//        try {
//            if (str.equals(db1)) {
//                return DriverManager.getConnection(con_string_db_1,username,password);
//            }else if(str.equals(db2)){
//                return DriverManager.getConnection(con_string_db_2,username,password);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;

        switch (str){
            case "db1":
               return DriverManager.getConnection(con_string_db_1,username,password);
//                System.out.println("Sie sind mit DB 1 verbunden");
            case "db2":
               return DriverManager.getConnection(con_string_db_2,username,password);
//                System.out.println("Sie sind mit DB 2 verbunden");
            default:
//                System.out.println("opps ein Fehler");
                return null;
        }
    }
}
