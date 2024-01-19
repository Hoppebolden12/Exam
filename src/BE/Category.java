package BE;

public class Category {

    private String category;
    private int Cid;

    public Category (int Cid, String category) {

        this.category = category;
        this.Cid = Cid;

    }
    public int getCId() {

        return Cid;
    }

    public String getCategory() {

        return category;
    }
    public void setCategory() {

        this.category = category;
    }

    public void setCid() {

        this.Cid = Cid;
    }

    public String toString() {

        return Cid + ": " + category;
    }

}
