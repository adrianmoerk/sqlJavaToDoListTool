package gui;

import sqlStuff.Ereignis;
import sqlStuff.SQL;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Logic {
    JList<Ereignis> ereignisJList = new JList<>();

    /**
     * @param ereignis Ereignis-Objekt
     * @param status   Status des Ereignisses (0 = geplant, 1 = fertig, 2 = entfällt)
     * @param von      Startdatum des Ereignisses
     * @param bis      Enddatum des Ereignisses
     */
    public static void addContent(String ereignis, byte status, String von, String bis) {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlIn("INSERT INTO `todo`.`todo-liste` (`EreignisName`, `Status`, `Von`, `Bis`) VALUES ('" + ereignis + "', '" + status + "', '" + von + "', '" + bis + "');");
        sql.disconnect();
    }


    /**
     * erstellt JList mit Ereignissen
     */
    public void anzeigeLabel() {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlOut();
        this.ereignisJList.setListData(sql.getEreignisList().toArray(new Ereignis[0]));
        sql.disconnect();

    }

    /**
     * @param status Status des Ereignisses, 0 = geplant, 1 = fertig, 2 = entfällt
     */
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

    /**
     * Methode die sqlDel aufruft mit dem Spezial Key 0, um alle Ereignisse zu löschen
     *
     * @see sqlStuff.SQL#sqlDel(int)
     */
    public void allesLoeschen() {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlDel(0);
        sql.disconnect();
    }

    /**
     * @param primaryKey Die ID des zu löschenden Elements
     */
    public void loeschen(int primaryKey) {
        SQL sql = new SQL();
        sql.connect();
        sql.sqlDel(primaryKey);
        sql.disconnect();
    }
}
