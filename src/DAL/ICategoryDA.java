package DAL;

import BE.Category;

import java.util.List;

public interface ICategoryDA {
    public List<Category> getAllCategories() throws Exception;

    public Category createPlaylist(Category category) throws Exception;

    public void updatePlaylist(Category category) throws Exception;

    public void deletePlaylist(Category category) throws Exception;





}
