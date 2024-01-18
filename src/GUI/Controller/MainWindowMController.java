package GUI.Controller;
import BE.Movie;
import GUI.Model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainWindowMController {


    public TableColumn clmMid;
    public TableColumn clmName;
    public TableColumn clmRating;
    public TableColumn clmLastV;
    public TableColumn clmCategoryLast;
    public TableView tblMovie;
    private MovieModel movieModel;


    @FXML
    private TableView<Movie> tblMovies;

    @FXML
    private TableColumn<Movie, String> nameColumn;

    @FXML
    private TableColumn<Movie, Integer> ratingColumn;

    @FXML
    private TableColumn<Movie, LocalDate> lastViewedColumn;
/*
    @FXML
    private TableColumn<Movie, List<Category>> categoryColumn;*/

    private ObservableList<Movie> movieList = FXCollections.observableArrayList();

    public void initialize() {
        // Initialize TableView columns
        clmMid.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        clmRating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        clmLastV.setCellValueFactory(new PropertyValueFactory<>("LastViewed"));
        clmCategoryLast.setCellValueFactory(new PropertyValueFactory<>("Categories"));

        if (movieModel != null) {
            tblMovies.setItems(movieModel.getObservableSongs());
        }
    }
    public void addMoviesToTable(Movie movie) {
        tblMovies.getItems().add(movie);
    }

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

