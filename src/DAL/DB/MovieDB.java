package DAL.DB;

import BE.Category;
import BE.Movie;
import DAL.IMovieDA;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDB implements IMovieDA {
    private MyDatabaseConnector databaseConnector;

    public MovieDB() throws IOException {
        databaseConnector = new MyDatabaseConnector();
    }
    public List<Movie> getAllMovies() throws Exception {
        ArrayList<Movie> allMovies = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM SMovie.MovieI";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                // Map DB row to Song object
                int id = rs.getInt("id");
                String name = rs.getString("title");
                int rating = rs.getInt("rating");
                Date lastview = rs.getDate("lastview");
                String filePath = rs.getString("filePath");

                Movie movie = new Movie(id, name, rating, (java.sql.Date) lastview, filePath);
                allMovies.add(movie);
            }
            return allMovies;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }
    public Movie createMovie(Movie movie) throws Exception {

        String sql = "INSERT INTO SMovie.MovieI (name, rating, lastview, filePath) VALUES (?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            //bind our parameters
            stmt.setString(1,movie.getName());
            stmt.setInt(2, movie.getRating());
            stmt.setDate(3,movie.getLastview());
            stmt.setString(4,movie.getFilePath());

            // Run the specified SQL Statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create song object and send up the layers
            Movie createdMovie = new Movie(id, movie.getName(), movie.getRating(), movie.getLastview(), movie.getFilePath());

            return createdMovie;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not add song", ex);
        }
    }

    @Override
    public void addCategoryToMovie(Movie movie, Category category) throws Exception {
        String sql = "DELETE FROM SMovie.MovieCategory WHERE id = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            // Bind parameters
            stmt.setInt(1, movie.getId());

            stmt.executeUpdate();
            // Run the specified SQL statement
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not delete song", ex);
        }


    }

    @Override
    public void updateMovie(Movie movie) throws Exception {
        throw new UnsupportedOperationException();

    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {
        String sql = "DELETE FROM SMovie.MovieI WHERE id = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            // Bind parameters
            stmt.setInt(1, movie.getId());

            stmt.executeUpdate();
            // Run the specified SQL statement
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not delete song", ex);
        }

    }

    @Override
    public List<Movie> getAllCategoriesInMovies(int categoryId) throws Exception {
        ArrayList<Movie> allCategoriesInMovies = new ArrayList<>();

        String sql = "SELECT s.*, sip.Category_id\n" +
                "FROM YTMusic.Songs s\n" +
                "INNER JOIN SMovie.MovieCategory sip ON s.id = sip.id\n" +
                "WHERE sip.Category_id = (?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {


            stmt.setInt(1, categoryId);

            ResultSet rs = stmt.executeQuery();

            // Loop through rows from the database result set
            while (rs.next()) {

                // Map DB row to Song object
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int rating = rs.getInt("rating");
                Date lastview = rs.getDate("lastview");
                String filePath = rs.getString("filePath");

                // Create Song object
                Movie movie = new Movie(id, name, rating, (java.sql.Date) lastview, filePath);
                allCategoriesInMovies.add(movie);
            }
            return allCategoriesInMovies;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException("Could not get songs from database", ex);
        }
    }
}
