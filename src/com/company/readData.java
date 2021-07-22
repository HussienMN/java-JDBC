package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class readData {

    Connection conn = null;
    Statement stm = null;
    ResultSet resultSet = null;

    public void conn(){
        try {
            conn = multiDBconnection.connectionToMultiDB("db1");
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void printDataOfProvinz(){
        try {
            conn();
            String query = "SELECT * FROM `provinces`";
//            this LIMIT with the query to determine how much record i want to get
//            String query = "SELECT * FROM `provinces`"+"LIMIT 4";
//            i can use also this formate LIMIT 4,7 to get the recordes betwween 4 and 7
//            String query = "SELECT * FROM `provinces`"+"LIMIT 4,7";

            resultSet = stm.executeQuery(query);
            while (resultSet.next()){
                StringBuilder stb = new StringBuilder();

//                System.out.println("provinz Name :"+resultSet.getString("provincesName")+ "| PLZ :"+resultSet.getString("provincesNumber"));
                stb.append("provinz Name :"+resultSet.getString("provincesName").toUpperCase()+ "| PLZ :"+resultSet.getString("provincesNumber").toUpperCase());
                System.out.println(stb.toString());
                System.out.println("------------------------------");

            }

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void printDataOfEmployee(){
        try {
            conn();
            String query = "SELECT * FROM `employees`";
            resultSet = stm.executeQuery(query);

            while (resultSet.next()){
                //note its wrong to print the data using this way with + as concatenation, insted of that we
                //should use stringBuffer and stringBuilder

//                System.out.println("Mitarbeiter Name :"+resultSet.getString("firstName")+" "+resultSet.getString("lastName"));
//                System.out.println("Mitarbeiter Adresse :"+resultSet.getString("address"));
//                System.out.println("Mitarbeiter email :"+resultSet.getString("email"));


                //the correct way:

                StringBuilder stb = new StringBuilder();
                stb.append("Mitarbeiter Name :"+resultSet.getString("firstName").toUpperCase()+" "+resultSet.getString("lastName").toUpperCase());
                stb.append(" /Mitarbeiter Adresse :"+resultSet.getString("address").toUpperCase());
                stb.append(" /Mitarbeiter email :"+resultSet.getString("email").toUpperCase());
                System.out.println(stb.toString());
                System.out.println("---------------------------");

            }

            // example if i want to get the first or last record in retrieved data
//            resultSet.first();
//            System.out.println("the first one "+resultSet.getString("firstName"));
//            resultSet.last();
//            System.out.println("the last one "+resultSet.getString("firstName"));
//            resultSet.absolute(2);
//            System.out.println("the data in row 2 of firstName "+resultSet.getString("firstName"));
//            resultSet.getRow();
//            System.out.println("nummber of rows of firstName "+resultSet.getString("firstName"));



        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void CreateTable(){
        try {
            conn();
            String query = "CREATE TABLE `Bundesländer` (\n" +
                    "  `LandID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  `LandName` varchar(50) NOT NULL,\n" +
                    "  `Kürzung` varchar(50) NOT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n";
            stm.executeUpdate(query);
            System.out.println("table has been created successfully!");
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertDataInTable() throws IOException {
        try {
            conn();
            BufferedReader readStatment = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the name of the State :");
            String name_of_State = readStatment.readLine();
            System.out.print("Enter the abbreviation of the state :");
            String state_abbreviation = readStatment.readLine();

            String query = "INSERT INTO `bundesländer`(`LandName`, `Kürzung`) VALUES ('"+name_of_State+"','"+state_abbreviation+"')";
            stm.executeUpdate(query);
            System.out.println("data has been added successfully!");
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void UpdateDataInTable() throws IOException {
        try {
            conn();

            BufferedReader readStatment = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the the ID of the admin :");
            String adminID = readStatment.readLine();
            System.out.print("Enter the name want to set :");
            String newName = readStatment.readLine();

//            String query = "UPDATE `admin` SET `userName`='Hussien' WHERE `adminId` = 1";
            String query2 = "UPDATE `admin` SET `userName`='"+newName+"' WHERE `adminId` ="+adminID+"  ";
            stm.executeUpdate(query2);
            System.out.println("data has been updated successfully!");
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
