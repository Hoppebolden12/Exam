package Bll;
import BE.Category;
import DAL.ICategoryDA;
import DAL.DB.CategoryDB;
import java.io.IOException;
import java.util.List;
public class CategoryManager {
    private ICategoryDA categoryDAO;

    public CategoryManager() throws IOException {

        categoryDAO = new CategoryDB();
    }

    public List<Category> getAllCategories() throws Exception {
        return categoryDAO.getAllCategories();
    }
    public Category createNewCategory(Category newcategory) throws Exception {
        return categoryDAO.createCategory(newcategory);
    }

    public void deleteCategory(Category category) throws Exception {
        categoryDAO.deleteCategory(category);
    }
}
