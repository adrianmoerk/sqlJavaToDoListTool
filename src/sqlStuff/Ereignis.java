package sqlStuff;

public class Ereignis {
    private final int id;
    private final String ereignis;
    private final int status;
    private final String von;
    private final String bis;

    public Ereignis(int id, String ereignis, int status, String von, String bis) {
        this.id = id;
        this.ereignis = ereignis;
        this.status = status;
        this.von = von;
        this.bis = bis;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        String statString = "";
        String ergebnis;

        switch (status) {
            case 0 -> statString = "geplant";
            case 1 -> statString = "fertig";
            case 2 -> statString = "entfällt";
        }
        if (von.equals("") && bis.equals(""))
            ergebnis = "ID: " + id + " Ereignis: " + ereignis + " Status: " + statString;
        else if (von.equals(""))
            ergebnis = "ID: " + id + " Ereignis: " + ereignis + " Status: " + statString + " Bis: " + bis;
        else if (bis.equals(""))
            ergebnis = "ID: " + id + " Ereignis: " + ereignis + " Status: " + statString + " Von: " + von;
        else
            ergebnis = "ID: " + id + " Ereignis: " + ereignis + " Status: " + statString + " Von: " + von + " Bis: " + bis;
        return ergebnis;
    }

    public int getStatus() {
        return status;
    }
}

