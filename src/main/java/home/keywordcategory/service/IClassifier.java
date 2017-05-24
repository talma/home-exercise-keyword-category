package home.keywordcategory.service;

import home.keywordcategory.model.ICategory;

import java.util.List;

/**
 * Classify URL
 */
public interface IClassifier {
    List<ICategory> classifyURL(String pageData);
}
