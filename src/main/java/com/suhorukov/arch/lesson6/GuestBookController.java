package com.suhorukov.arch.lesson6;

import com.sun.prism.impl.Disposer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by demo4 on 19.07.14.
 */
public interface GuestBookController extends AutoCloseable {
    void addRecord(String message) throws SQLException;
    List<Record> getRecords() throws SQLException; //Record {id, postDate, message}
}
