package sqlStuff;

public interface DatenbankSQL {
    public static final String dbTreiber = "jdbc:mariadb";
    public static final String host = "localhost";
    public static final String port = "3306";
    public static final String dbName = "todo";
    public static final String user = "root";
    public static final String password = "password";
    public static final String url = dbTreiber + "://" + host + ":" + port + "/" + dbName;
    public static final String table = "liste";
}
