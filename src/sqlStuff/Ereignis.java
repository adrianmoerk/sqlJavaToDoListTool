package sqlStuff;

public class Ereignis {
    private int id;
    private String ereignis;
    private int status;
    private String von;
    private String bis;

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

    public String getEreignis() {
        return ereignis;
    }

    public int getStatus() {
        return status;
    }

    public String getVon() {
        return von;
    }

    public String getBis() {
        return bis;
    }

    public String toString() {
        String statString = "";
        String ergebnis = "";

        switch (status) {
            case 0:
                statString = "geplant";
                break;
            case 1:
                statString = "fertig";
                break;
            case 2:
                statString = "entfällt";
                break;
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
    
}

