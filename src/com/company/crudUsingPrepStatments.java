package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class crudUsingPrepStatments {
    Connection conn = null;
    Statement stm = null;
    ResultSet resultSet = null;


    public void insertData() throws SQLException, IOException {
        try {
            conn = multiDBconnection.connectionToMultiDB("db1");
            String query = "INSERT INTO `bundesländer`(`LandName`, `Kürzung`) VALUES (?,?)";
            BufferedReader readStm = new BufferedReader(new InputStreamReader(System.in));
            PreparedStatement prepared_stm = conn.prepareStatement(query);
            System.out.println("enter the name of the state :");
            String landName = readStm.readLine();
            System.out.println("enter the abbreviation of the state :");
            String abb = readStm.readLine();
            prepared_stm.setString(1,landName);
            prepared_stm.setString(2,abb);
            prepared_stm.executeUpdate();
            System.out.println("Done !");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printData() throws IOException,SQLException {
        try {
            conn = multiDBconnection.connectionToMultiDB("db1");
            String query = "SELECT * FROM `bundesländer`";
            PreparedStatement rd = conn.prepareStatement(query);
            resultSet = rd.executeQuery();

            while (resultSet.next()){

                StringBuilder stb = new StringBuilder();
                stb.append("*ID :"+resultSet.getInt(1));
                stb.append(" *state name :"+resultSet.getString(2));
                stb.append(" *state abbreviation :"+resultSet.getString(3).toUpperCase());
                System.out.println(stb.toString());
//                System.out.println("ID:"+resultSet.getString("LandID")+ "| Name :"+resultSet.getString("LandName")+"Abbreviation :"+resultSet.getString("Kürzung"));
                System.out.println("---------------------------");
            }
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateData() throws SQLException, IOException {
        try {
            conn = multiDBconnection.connectionToMultiDB("db1");
            printData();
            String query = "UPDATE `bundesländer` SET `Kürzung`=? WHERE  LandId=?";
            BufferedReader readStm = new BufferedReader(new InputStreamReader(System.in));
            PreparedStatement prepared_stm = conn.prepareStatement(query);
            System.out.println("enter the ID of the state you want to update :");
            String landId = readStm.readLine();
            System.out.println("enter the new abbreviation of the state :");
            String abb = readStm.readLine();

            prepared_stm.setString(1,abb);
            prepared_stm.setString(2,landId);
            prepared_stm.executeUpdate();
            System.out.println("Done !");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //    using Transaction statement is more quickly because its compile the commits one time
    public void usingPreparedStatemnt() throws SQLException, IOException {
        try {
            conn = multiDBconnection.connectionToMultiDB("db1");
            String query = "UPDATE `bundesländer` SET `Kürzung`=? WHERE  LandId=?";


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
