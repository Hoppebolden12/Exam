package DAL;

import BE.Category;

import java.util.List;

public interface ICategoryDA {
    public List<Category> getAllCategories() throws Exception;

    public Category createCategory(Category category) throws Exception;

    public void updateCategory(Category category) throws Exception;

    public void deleteCategory(Category category) throws Exception;





}
