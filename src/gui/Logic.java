package gui;

import sqlStuff.Ereignis;
import sqlStuff.SQL;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Logic {
    JList<Ereignis> ereignisJList = new JList<>();

    public static void addContent(String ereignis, byte status, String von, String bis) {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlIn("INSERT INTO `todo`.`todo-liste` (`EreignisName`, `Status`, `Von`, `Bis`) VALUES ('" + ereignis + "', '" + status + "', '" + von + "', '" + bis + "');");
        sql.disconnect();
    }


    // Methode die, die Liste der Ereignisse in ein JList packt
    public void anzeigeLabel() {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlOut();
        this.ereignisJList.setListData(sql.getEreignisList().toArray(new Ereignis[0]));
        sql.disconnect();

    }

    public void filterEreignis(int status) {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlOut();
        List<Ereignis> tempEreignisList;
        tempEreignisList = sql.getEreignisList();
        List<Ereignis> filteredEreignisList = new ArrayList<>();
        switch (status) {
            case 0:
                for (Ereignis ereignis : tempEreignisList) {
                    if (ereignis.getStatus() == 0) {
                        filteredEreignisList.add(ereignis);
                    }
                }
                break;
            case 1:
                for (Ereignis ereignis : tempEreignisList) {
                    if (ereignis.getStatus() == 1) {
                        filteredEreignisList.add(ereignis);
                    }
                }
                break;
            case 2:
                for (Ereignis ereignis : tempEreignisList) {
                    if (ereignis.getStatus() == 2) {
                        filteredEreignisList.add(ereignis);
                    }
                }
                break;

        }
        this.ereignisJList.setListData(filteredEreignisList.toArray(new Ereignis[0]));
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
