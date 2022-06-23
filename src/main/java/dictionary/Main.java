package dictionary;

import dictionary.entity.Entity;
import dictionary.entity.EntityProducer;
import dictionary.jdbc.DBConnector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        List<String> list = getWords();

        System.out.println(list.size());
        Counter.total = list.size();
        Entity entity;
        Connection connection = DBConnector.getConnection();
        Thread thread = new Thread(new Counter());
        thread.start();

        for (Counter.i = 0; Counter.i < Counter.total; Counter.i++) {
            try {
                entity = EntityProducer.getEntity(list.get(Counter.i));

                entity.saveToDB(connection);
            } catch (RuntimeException e){
                Counter.continued=false;
            }
        }
        connection.close();
        Counter.continued=false;
        System.out.println(Counter.i + " sec");



//        entity.saveToDB(connection);

    }

    private static List<String> getWords(){
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Paths.get("words.txt"));
        } catch (IOException e){
            System.out.println("file was not found");
            e.printStackTrace();
        }
        list.remove(0);
        return list;
    }
}
