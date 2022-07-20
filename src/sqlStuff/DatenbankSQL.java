package sqlStuff;

public interface DatenbankSQL {
    String dbTreiber = "jdbc:mariadb";
    String host = "localhost";
    String port = "3306";
    String dbName = "todo";
    String user = "root";
    String password = "password";
    String url = dbTreiber + "://" + host + ":" + port + "/" + dbName;
}
