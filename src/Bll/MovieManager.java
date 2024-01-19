package Bll;

import java.io.IOException;
import java.util.List;

import BE.Category;
import BE.Movie;

import Bll.util.MovieSearcher;
import DAL.IMovieDA;
import DAL.DB.MovieDB;

import java.io.IOException;
import java.util.List;


    public class MovieManager {

        private MovieSearcher movieSearcher = new MovieSearcher();

        private IMovieDA MovieDAO;

        public MovieManager() throws IOException {

            MovieDAO = new MovieDB();
        }

        public List<Movie> getAllMovies() throws Exception {
            return MovieDAO.getAllMovies();
        }
        /*
        public List<Movie> searchMovies(String query) throws Exception {
            List<Movie> allMovies = getAllMovies();
            List<Movie> searchResult = MovieSearcher.search (allMovies, query);
            return searchResult;
        }*/

        public Movie createMovie(Movie newMovie) throws Exception {
            return MovieDAO.createMovie(newMovie);
        }

        public void deleteMovie(Movie movie) throws Exception {
            MovieDAO.deleteMovie(movie);
        }

    }




