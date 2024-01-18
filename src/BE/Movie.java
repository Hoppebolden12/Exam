package BE;
import java.time.LocalDate;

public class Movie {

    private int Mid;

    private String name;

    private int rating;

    private LocalDate lastview;

    private String filePath;


    public Movie(int id, String name, int rating, LocalDate lastview, String fileLink) {
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


    public int getRating() {

        return rating;
    }

    public LocalDate getLastview() {

        return lastview;
    }

    public String getFilePath() {

        return filePath;
    }



    public void setName(String name) {

        this.name = name;
    }

    public void setRating(int rating) {

        this.rating = rating;

    }

    public void setLastview(LocalDate lastview) {

        this.lastview = lastview;
    }

    public void setFilePath(String filepath) {

        this.filePath = filepath;
    }

    @Override
    public String toString() {

        return Mid + ": " + name + " ("+rating+")" + lastview;
    }


}
