package DAL.DB;

import BE.Movie;
import DAL.IMovieDA;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDB implements IMovieDA {
    private MyDatabaseConnector databaseConnector;

    public MovieDB() throws IOException {
        databaseConnector = new MyDatabaseConnector();
    }


    @Override
    public List<Movie> getAllSongs() throws Exception {
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
                int lastview = rs.getInt("lastview");
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
    public Movie createSong(Movie movie) throws Exception {

        String sql = "INSERT INTO SMovie.MovieI (name, rating, lastview, filePath) VALUES (?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //bind our parameters
            stmt.setString(1, movie.getName());
            stmt.setInt(2, movie.getrating());
            stmt.setInt(3, movie.getlastview());
            stmt.setString(4, movie.getFilePath());

            // Run the specified SQL Statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create song object and send up the layers
            Movie createdMovie = new Movie(id, movie.getName(), movie.getrating(), movie.getlastview(), movie.getFilePath());

            return createdMovie;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not add Movie", ex);
        }
    }

    @Override
    public void updateSong(Movie movie) throws Exception {

    }

    @Override
        public Movie deleteSong (Movie movie) throws Exception {
            String sql = "DELETE FROM SMovie.MovieI WHERE song_id = ?;";

            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Bind parameters
                stmt.setInt(1, movie.getId());

                stmt.executeUpdate();
                // Run the specified SQL statement
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Could not delete Movie", ex);
            }
            return movie;
        }

        @Override
        public Movie deleteSongFromPlaylist (Movie movie) throws Exception {
            String sql = "DELETE FROM YTMusic.SongsInPlaylist WHERE song_id = ?;";

            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Bind parameters
                stmt.setInt(1, movie.getId());

                stmt.executeUpdate();
                // Run the specified SQL statement
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Could not delete Movie", ex);
            }
            return movie;
        }

    }
