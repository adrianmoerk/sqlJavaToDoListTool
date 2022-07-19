package sqlStuff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL implements DatenbankSQL {
    private Connection verbindung;
    private List<Ereignis> ereignisList = new ArrayList<>();

    public void connect() {
        try {
            this.verbindung = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sqlIn(String query) {
        try {
            this.verbindung.createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sqlOut() {
        try {
            String query = "SELECT * FROM todo.`todo-liste`;";
            ResultSet rs = this.verbindung.createStatement().executeQuery(query);
            while (rs.next()) {
                this.ereignisList.add(new Ereignis(rs.getInt("ID"), rs.getString("EreignisName"), rs.getInt("Status"), rs.getString("Von"), rs.getString("Bis")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sqlDel(int primaryKey) {
        try {
            String query = "DELETE FROM todo.`todo-liste`;";
            if (primaryKey != 0) {
                query = "DELETE FROM todo.`todo-liste` WHERE `ID`=" + primaryKey + ";";
                System.out.println("Element mit ID " + primaryKey + " wurde gelöscht.");
            }
            this.verbindung.createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        try {
            this.verbindung.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getVerbindung() {
        return verbindung;
    }

    public void setVerbindung(Connection verbindung) {
        this.verbindung = verbindung;
    }

    public List<Ereignis> getEreignisList() {
        return ereignisList;
    }

    public void setEreignisList(List<Ereignis> ereignisList) {
        this.ereignisList = ereignisList;
    }
}