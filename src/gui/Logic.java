package gui;

import sqlStuff.Ereignis;
import sqlStuff.SQL;

import javax.swing.*;


public class Logic {
    JList<Ereignis> ereignisJList = new JList<>();

    public static void addContent(String ereignis, byte status, String von, String bis) {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlIn("INSERT INTO `todo`.`todo-liste` (`EreignisName`, `Status`, `Von`, `Bis`) VALUES ('" + ereignis + "', '" + status + "', '" + von + "', '" + bis + "');");
        sql.disconnect();
    }


    // Methode die die Liste der Ereignisse in ein JList packt
    public void anzeigeLabel() {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlOut();
        this.ereignisJList.setListData(sql.getEreignisList().toArray(new Ereignis[0]));
        sql.disconnect();

    }


    public void allesLoeschen() {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlDel(0);
        sql.disconnect();
    }

    public void loeschen(int primaryKey) {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlDel(primaryKey);
        sql.disconnect();
    }
}
