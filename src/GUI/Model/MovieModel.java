package GUI.Model;
import BE.Category;
import BE.Movie;
import Bll.CategoryManager;
import Bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class MovieModel {
    private ObservableList<Movie> moviesToBeViewed;
    private CategoryManager categoryManager;
    private MovieManager movieManager;

    public MovieModel() throws Exception {
        this.movieManager = new MovieManager();
        this.categoryManager = new CategoryManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
    }

    public void createMovie(Movie newMovie) throws Exception {
        Movie m = movieManager.createMovie(newMovie);
        moviesToBeViewed.add(m);
    }


    public ObservableList<Movie> getObservableSongs() {
        return moviesToBeViewed;
    }
/*
    public void searchMovie(String query) throws Exception {
        List<Movie> searchResults = movieManager.searchMovies(query);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(searchResults);
    }*/


    public void deleteSong(Movie movie) throws Exception {
        movieManager.deleteMovie(movie);
        moviesToBeViewed.remove(movie);
    }


}
