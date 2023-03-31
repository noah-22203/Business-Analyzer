public class Obj {
    private String zip;
    private String NAICS;
    private String hood;
    private String type;
    private boolean open;
    private String sDate;

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }
    public String getsDate() {
        return sDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    public void setNAICS(String NAICS) {
        this.NAICS = NAICS;
    }

    public String getNAICS() {
        return NAICS;
    }

    public void setHood(String hood) {
        this.hood = hood;
    }

    public String getHood() {
        return hood;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public String toString() {
        return "(" + this.zip + "," + this.NAICS + "," + this.hood + "," + this.open + "," + this.sDate + ")";
    }
}

