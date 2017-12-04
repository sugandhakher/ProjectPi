package Program;

import java.util.ArrayList;

public interface SearchTree extends Tree {

    ArrayList<Integer> getKeyroots();

    ArrayList<String> getLabels();

    void traverse();
    void l();
    void keyroots();
    void index();
    ArrayList<Integer> getLeftmost();

    String getURL();

    int getStartline();
    int getEndline();


}
