package BE;

public class Movie {

    private int Mid;

    private String name;

    private int rating;

    private int lastview;

    private String filePath;


    public Movie(int id, String name, int rating, int lastview, String fileLink) {
        this.Mid = id;
        this.name = name;
        this.rating = rating;
        this.lastview = lastview;
        this.filePath = fileLink;
    }


    public int getId() {

        return Mid;
    }

    public String getName() {

        return name;
    }


    public int getrating() {

        return rating;
    }

    public int getlastview() {

        return lastview;
    }

    public String getFilePath() {

        return filePath;
    }



    public void setName() {

        this.name = name;
    }

    public void setRating() {

        this.rating = rating;
    }

    public void setLastview() {

        this.lastview = lastview;
    }

    public void setFilePath() {
        this.filePath = filePath;
    }

    @Override
    public String toString() {

        return Mid + ": " + name + " ("+rating+")" + lastview;
    }


}
