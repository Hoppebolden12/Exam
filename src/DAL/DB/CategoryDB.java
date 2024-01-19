package DAL.DB;

import BE.Category;
import BE.Movie;
import DAL.ICategoryDA;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDB implements ICategoryDA {
    private MyDatabaseConnector databaseConnector;
    public CategoryDB() throws IOException {
        databaseConnector = new MyDatabaseConnector();
    }

    @Override
    public List<Category> getAllCategories() throws Exception {
        ArrayList<Category> allCategories = new ArrayList<>();
        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM SMovie.Category";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                // Map DB row to Song object
                int id = rs.getInt("Category_id");
                String name = rs.getString("Category");


                Category category = new Category(id, name);
                allCategories.add(category);
            }
            return allCategories;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Could not get Categories from database", ex);
        }
    }

    @Override
    public Category createCategory(Category category) throws Exception {
        String sql = "INSERT INTO SMovie.Category (name) VALUES (?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //bind our parameters

            stmt.setString(1, category.getCategory());


            // Run the specified SQL Statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create playlist object and send up the layers
            Category createdPLaylist = new Category(id, category.getCategory());

            return createdPLaylist;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not add playlist", ex);
        }
    }

    @Override
    public void updateCategory(Category category) throws Exception {

    }

    @Override
    public void deleteCategory(Category category) throws Exception {
        String sql = "DELETE FROM SMovie.Category WHERE Category_id = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Bind parameters
            stmt.setInt(1, category.getCId());

            stmt.executeUpdate();
            // Run the specified SQL statement
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
            //ex.printStackTrace();
            //throw new Exception("Could not delete playlist", ex);
        }
    }

    @Override
    public Movie deleteCategoryFromMovie(Movie movie) throws Exception {
        return null;
    }
}