package DAL;

import BE.Movie;

import java.util.List;

public interface IMovieDA {

    public List<Movie> getAllSongs() throws Exception;

    public Movie createSong(Movie movie) throws Exception;

    public void updateSong(Movie movie) throws Exception;

    public Movie deleteSong(Movie movie) throws Exception;

    public Movie deleteSongFromPlaylist(Movie movie) throws Exception;
}
