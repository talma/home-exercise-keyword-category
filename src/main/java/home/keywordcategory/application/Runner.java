package home.keywordcategory.application;


/**
 * Main application class
 */
public class Runner {

    /**
     * Classify category based on arguments
     * @param args list of two items: categories and URL
     *             Given Category list is subset of repository categories
     *             URL can be any valid URL.
     */
    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            printUsage();
        }
    }

    public static void printUsage() {
        System.out.println("Usage Runner <Categories> <URLs>");
        System.out.println("Categories: list of categories comma separated.");
        System.out.println("URLs: list of URLs comma separated.");
    }

    /**
     * initialize categories model
     */
    public static void initializeModel() {
       // TODO: implement initialize Category repository and get categories
    }


}
