package BE;

public class Category {

    private String category;
    private int Cid;

    public void category (int Cid, String category) {

        this.category = category;
        this.Cid = Cid;

    }
    public int getCId() {

        return Cid;
    }

    public String getCategory() {

        return category;
    }

    public String toString() {

        return Cid + ": " + category;
    }

}
