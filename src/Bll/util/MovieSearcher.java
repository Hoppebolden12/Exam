package Bll.util;
import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher {


    public class SongSearcher {
        public List<Movie> search(List<Movie> searchBase, String query) {
            List<Movie> searchResult = new ArrayList<>();

            for (Movie movie : searchBase) {
                if (compareToMovieName(query, movie)) {
                    searchResult.add(movie);
                }
            }
            return searchResult;
        }

        private boolean compareToMovieName(String query, Movie movie) {
            return movie.getName().toLowerCase().contains(query.toLowerCase());
        }

    }
}

