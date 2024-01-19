package DAL;

import BE.Category;
import BE.Movie;

import java.util.List;

public interface IMovieDA {

    public List<Movie> getAllMovies() throws Exception;

    Movie createMovie(Movie movie) throws Exception;
    void addCategoryToMovie(Movie movie, Category category) throws Exception;


    public void updateMovie(Movie movie) throws Exception;

    public void deleteMovie(Movie movie) throws Exception;

    List<Movie> getAllCategoriesInMovies(int categoryId) throws Exception;
}
