package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowMController {

    public void CreateNewMovie(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/NewMovie.fxml"));
            Parent root = loader.load();

            NewMovieController newMovieController = loader.getController();
            newMovieController.setMainController(this);

            Stage editStage = new Stage();
            editStage.setTitle("New Movie");
            editStage.setScene(new Scene(root));

            editStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

