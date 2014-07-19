package com.suhorukov.arch.lesson6;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by demo4 on 19.07.14.
 */
public class GuestBookControllerImpl implements GuestBookController{
    PreparedStatement insert;
    PreparedStatement selectAll;

    public GuestBookControllerImpl(Connection c) throws SQLException {
        insert = c.prepareStatement("insert into posts (postdate, message) values(?, ?)");
        selectAll = c.prepareStatement("select * from posts");
    }

    @Override
    public void addRecord(String message) throws SQLException {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        insert.setTimestamp(1, t);
        insert.setString(2, message);
        insert.execute();
    }

    @Override
    public List<Record> getRecords() throws SQLException {
        List<Record> list = new ArrayList<>();
        try(ResultSet rs = selectAll.executeQuery()){
            while(rs.next()){
                Record r = new Record(
                        rs.getInt("id"),
                        rs.getString("message"),
                        rs.getTimestamp("postdate")
                );
                list.add(r);
            }
        }
        return list;
    }

    @Override
    public void close() throws IOException {
        try {
            insert.close();
            selectAll.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
}
