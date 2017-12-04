package Loader;

import Program.Program;

import java.io.IOException;


/** Interface Loader
 * Role: Component Interface
 *  This component takes charge of read URL, no matter to a dictionary/file/remote repository,
 *  then parse that python project to Node
 */
public interface Loader {

    /** Return the url of this load is using
     */
    public String getURL();
    /** build a program data structure defined in package program for a program
     * @param url: the url for a python project
     */
    public Program Parse() throws IOException;
}