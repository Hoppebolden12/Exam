package GUI.Controller;

import BE.Category;
import DAL.DB.CategoryDB;
import GUI.Model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SelectCategoryWindow {
    public NewMovieController mainController;
    public TextField txtFNewcategory;
    public TableColumn colCategoryChossen;
    public TableColumn colCategoryAvailable;
    public TableView tblVAvailable;

    private CategoryDB categoryDB;
    private NewMovieController newMovieController;

    public void setMainController(NewMovieController newMovieController) {

        this.mainController = newMovieController;
    }

    public void CreateNewCategory(ActionEvent actionEvent) {
        String name = txtFNewcategory.getText();


        Category newCategory = new Category(-1, name);
        addCategoryToTable(newCategory);

        CategoryModel categorymodel = new CategoryModel();

        try{
            CategoryModel.createNewPlaylist(newCategory);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void addCategoryToTable(Category category) {

        tblVAvailable.getItems().add(category);
    }



    public void AddCategoryToMovie(ActionEvent actionEvent) {
    }

    public void DeleteCategoryToMovie(ActionEvent actionEvent) {
    }

    public void FinishNewMovie(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
