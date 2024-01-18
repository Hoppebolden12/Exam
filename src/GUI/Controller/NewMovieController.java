package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class NewMovieController {
public MainWindowMController mainController;
    public TextField txtFName;
    public TextField txtFMovieName;

    public void setMainController(MainWindowMController mainWindowMController) {
        this.mainController = mainWindowMController;
    }

    /*Creates a list in the db with the name filepath sets the raiting to 1*/
    public void CreateNewMovie(ActionEvent actionEvent) {


    }
/* instead of closing the window after choosing the movie file and giving it a name.
It will direct you to the next window where you can give it categories*/

    public void SelectCategory(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/SelectCategoryWindow.fxml"));
            Parent root = loader.load();

            SelectCategoryWindow selectCategoryWindow = loader.getController();
            selectCategoryWindow.setMainController(this);

            Stage editStage = new Stage();
            editStage.setTitle("Edit category");
            editStage.setScene(new Scene(root));

            editStage.show();
        } catch (IOException e) {
            displayError(e);
            e.printStackTrace();
        }

    }

    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }

    public void ChooseFile(ActionEvent actionEvent) {
    }
}
