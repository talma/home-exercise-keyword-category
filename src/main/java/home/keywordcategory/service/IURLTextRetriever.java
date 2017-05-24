package home.keywordcategory.service;


import java.net.URI;

public interface IURLTextRetriever {
    /**
     * Get text out of given url
     * @param url valid url
     * @return text
     */
    String getText(URI url);
}
