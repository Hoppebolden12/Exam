package GUI.Model;
import BE.Category;
import BE.Movie;
import Bll.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class CategoryModel {
    private ObservableList<Category> categoriesToBeViewed;

    private CategoryManager categoryManager;


    public CategoryModel() throws Exception {
        categoryManager = new CategoryManager();
        categoriesToBeViewed= FXCollections.observableArrayList();
        categoriesToBeViewed.addAll(categoryManager.getAllCategories());
    }

    public void createNewPlaylist(Category newCategory) throws Exception {
        Category c = categoryManager.createNewCategory(newCategory);
        categoriesToBeViewed.add(c);
    }


    public ObservableList<Category> getObservablePlaylists() {
        return categoriesToBeViewed;
    }


    public void deletePlaylist(Playlist playlist) throws Exception {
        playlistManager.deletePlaylist(playlist);
        playlistsToBeViewed.remove(playlist);
    }

}
