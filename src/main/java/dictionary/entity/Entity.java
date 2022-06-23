package dictionary.entity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Сущность состоящая из англ слова, перевода и доп. комментария
 * может сохранять себя в бд
 */
public class Entity {
    private final String eng;
    private final String translation;
    private final String comment;

    public Entity(String eng, String translation, String comment) {
        this.eng = eng;
        this.translation = translation;
        this.comment = comment;
    }

    public String getEng() {
        return eng;
    }

    public String getTranslation() {
        return translation;
    }

    public String getComment() {
        return comment;
    }

    public void saveToDB(Connection connection){
        try {
            insert(connection);
        } catch (SQLException ignore) {

        }
    }

    private void insert(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO adv_words(eng,trans,comment) VALUES (?,?,?)");
        preparedStatement.setString(1, eng);
        preparedStatement.setString(2, translation);
        preparedStatement.setString(3, comment);
        preparedStatement.execute();

    }

}
