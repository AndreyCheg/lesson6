package com.suhorukov.arch.lesson6;

import java.util.Date;
import java.sql.*;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try(Connection con = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1");){
            System.out.println(con);
            Statement stmt = con.createStatement();
            stmt.execute("create table posts(id INT IDENTITY, postdate TIMESTAMP, message VARCHAR)");
            stmt.close();
            try(GuestBookController ctrl = new GuestBookControllerImpl(con)){
                ctrl.addRecord("hello!");
                ctrl.addRecord("World!");
                ctrl.addRecord("this lesson was interested!");
                ctrl.addRecord("let's do some thing!");
                ctrl.addRecord("hello!");
                ctrl.addRecord("World!");

                List<Record> ls = ctrl.getRecords();
                for(Record r: ls){
                    System.out.println("# " + r.getId() + " : \"" + r.getMsg() + "\" at " + r.getTimestamp());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
