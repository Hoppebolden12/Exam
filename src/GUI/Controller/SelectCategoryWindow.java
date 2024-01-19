package GUI.Controller;

import BE.Category;

import GUI.Model.CategoryModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SelectCategoryWindow {


    public TextField txtFNewcategory;
    public TableView tblVAvailable;

    public void CreateNewCategory(ActionEvent actionEvent) throws Exception {
        String name = txtFNewcategory.getText();


        Category newCategory = new Category(-1, name);
        addCategoryToTable(newCategory);

        CategoryModel categorymodel = new CategoryModel();

        try{
            categorymodel.createNewCategory(newCategory);
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }

    }
    public void addCategoryToTable(Category category){
        tblVAvailable.getItems().add(category);
    }

    public void FinishNewMovie(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }


    public void AddCategoryToMovie(ActionEvent actionEvent) {
    }

    public void DeleteCategoryToMovie(ActionEvent actionEvent) {
    }
}
