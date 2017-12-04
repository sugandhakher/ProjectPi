package Loader;

public class LoaderFactory {
    public PythonLoader makePythonLoader(String url){return new PythonLoader(url);}
}
