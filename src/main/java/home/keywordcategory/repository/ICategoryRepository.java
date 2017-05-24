package home.keywordcategory.repository;


import home.keywordcategory.model.ICategory;

import java.util.List;

/**
 * Category repository interface
 */
public interface ICategoryRepository {
    /**
     * Load list of categories
     */
    List<ICategory> getCategories();

    /**
     * Save categories
     */
    void saveCategories(List<ICategory> categories);

}
