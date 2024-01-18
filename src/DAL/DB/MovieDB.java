package DAL.DB;

import BE.Movie;
import DAL.IMovieDA;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieDB implements IMovieDA {
    private MyDatabaseConnector databaseConnector;

    public MovieDB() throws IOException {
        databaseConnector = new MyDatabaseConnector();
    }


    @Override
    public List<Movie> getAllMovies() throws Exception {
        ArrayList<Movie> allMovies = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM SMovie.MovieI";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                // Map DB row to Song object
                int id = rs.getInt("Movie_id");
                String name = rs.getString("title");
                int rating = rs.getInt("rating");
                LocalDate lastview = rs.getDate("lastview").toLocalDate();
                String filePath = rs.getString("filePath");

                Movie movie = new Movie(id, name, rating, lastview, filePath);
                allMovies.add(movie);
            }
            return allMovies;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }

    @Override
    public Movie createMovie(Movie movie) throws Exception {
        String sql = "INSERT INTO SMovie.MovieI (title, rating, lastview, filePath) VALUES (?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Bind our parameters
            stmt.setString(1, movie.getName());
            stmt.setInt(2, movie.getRating());
            stmt.setDate(3, java.sql.Date.valueOf(movie.getLastview()));
            stmt.setString(4, movie.getFilePath());

            // Run the specified SQL Statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Set the lastview of the created movie to the current date
            LocalDate currentDate = LocalDate.now();
            movie = new Movie(id, movie.getName(), movie.getRating(), currentDate, movie.getFilePath());

            return movie;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not add Movie", ex);
        }
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {
        String sql = "UPDATE SMovie.MovieI SET rating = ? WHERE Movie_id = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Bind parameters
            stmt.setInt(1, movie.getRating());
            stmt.setInt(2, movie.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update Movie rating", ex);
        }

    }

    @Override
        public void deleteMovie (Movie movie) throws Exception {
        String sql = "DELETE FROM SMovie.MovieI WHERE Movie_id = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Bind parameter
            stmt.setInt(1, movie.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete Movie", ex);
        }
        }

    @Override
    public Movie deleteCategoryFromMovie(Movie movie) throws Exception {
        return null;
    }

}
