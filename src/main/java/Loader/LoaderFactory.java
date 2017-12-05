package loader;

/**
 * Factory class for creating object for PythonLoader
 */
public class LoaderFactory {

    /**
     * Method to create object for PythonLoader when url is given
     */
    public PythonLoader makePythonLoader(String url){return new PythonLoader(url);}
}
