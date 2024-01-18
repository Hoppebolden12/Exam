package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectCategoryWindow {
    public NewMovieController mainController;
    public TextField txtFNewcategory;
    public TableColumn colCategoryChossen;
    public TableColumn colCategoryAvailable;


    public void setMainController(NewMovieController newMovieController) {
        this.mainController = newMovieController;
    }

    public void CreateNewCategory(ActionEvent actionEvent) {
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
