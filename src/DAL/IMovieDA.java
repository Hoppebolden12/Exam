package DAL;

import BE.Movie;

import java.util.List;

public interface IMovieDA {

    public List<Movie> getAllMovies() throws Exception;

    Movie createMovie(Movie movie) throws Exception;

    public void updateMovie(Movie movie) throws Exception;

    public void deleteMovie(Movie movie) throws Exception;

    public Movie deleteCategoryFromMovie(Movie movie) throws Exception;
}
