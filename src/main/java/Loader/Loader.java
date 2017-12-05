package loader;

import program.Program;

import java.io.IOException;


/** Interface loader
 * Role: Component Interface
 *  This component takes charge of read URL, no matter to a dictionary/file/remote repository,
 *  then parse that python project to Node
 */
public interface Loader {

    /** Return the url of this load
     */
    public String getURL();

    /** build a program data structure type1 defined in package program for a program
     */
    public Program parse() throws IOException;
    /** build a program data structure type 2 defined in package program for a program
     */
    public Program parse2() throws IOException;
}
