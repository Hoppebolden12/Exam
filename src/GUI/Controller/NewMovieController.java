package GUI.Controller;

import BE.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;


public class NewMovieController {
public MainWindowMController mainController;
    public TextField txtFName;
    public TextField txtFMovieName;

    public void setMainController(MainWindowMController mainWindowMController) {
        this.mainController = mainWindowMController;
    }

    public void CreateNewMovie(ActionEvent actionEvent) {
        String name = txtFMovieName.getText();
        String filepath = txtFName.getText();
        LocalDate lastview = LocalDate.now();
        int rating = 0;

        Movie newMovie = new Movie(0, name, rating, lastview, filepath);

        // Add the new movie to the main controller's list
        mainController.addMoviesToTable(newMovie);


    }


    public void ChooseFile(ActionEvent actionEvent) throws SQLException,IOException {
        FileChooser chooser = new FileChooser();
        // this filters the files
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP4 Files", "*.mp4", "*.mpeg4"));

        // Set the initial directory before showing the dialog
        File initialDirectory = new File("/Data");
        chooser.setInitialDirectory(initialDirectory);

        File selectedFile = chooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            Path destinationPath = copyFile(selectedFile);

        }

        if (selectedFile != null) {
            txtFMovieName.setText(selectedFile.getName()); //add Movie Name
            txtFName.setText(selectedFile.getName()); // FilePath added
        }
    }

    private Path copyFile(File sourceFile) throws IOException {
        // Get the destination directory within your project
        File destinationDirectory = new File("data/selected");

        // Create the destination directory if it doesn't exist
        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdirs();
        }

        // Define the destination path for the copied file
        Path destinationPath = new File(destinationDirectory, sourceFile.getName()).toPath();

        // Copy the selected file to the destination directory
        try {
            Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);


            System.out.println("File copied successfully. Destination Path: " + destinationPath);
        } catch (IOException e){
            throw e;
        }
        return destinationPath;
    }

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
            e.printStackTrace();
        }


}
}
