package Program;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;

public class PythonTree implements SearchTree {
    PythonNode root ;
    String URL;
    // function l() which gives the leftmost child
    ArrayList<Integer> l = new ArrayList<Integer>();
    // list of keyroots, i.e., nodes with a left child and the tree root
    ArrayList<Integer> keyroots = new ArrayList<Integer>();
    // list of the labels of the nodes used for node comparison
    ArrayList<String> labels = new ArrayList<String>();
    @Override
    public int getStartline() {
        return startline;
    }
    @Override
    public int getEndline() {
        return endline;
    }

    int startline;
    int endline;

    // the following constructor handles preorder notation. E.g., f(a b(c))
//    public PythonTree(String s) throws IOException {
//        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(s));
//        tokenizer.nextToken();
//        root = parseString(root, tokenizer);
//        if (tokenizer.ttype != StreamTokenizer.TT_EOF) {
//            throw new RuntimeException("Leftover token: " + tokenizer.ttype);
//        }
//    }

    public PythonTree(PythonNode r, String url){
        root = r;
        URL = url;
    }

    @Override
    public ArrayList<Integer> getLeftmost() {
        return l;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public ArrayList<Integer> getKeyroots() {
        return keyroots;
    }
    @Override
    public ArrayList<String> getLabels() {
        return labels;
    }

    @Override
    public Node getRoot(){
        return root;
    }

    @Override
    public int size() {
        int size = 1;
        size = getSize(root, size);
        return size;
    }

    private int getSize(Node node, int size){
        size += node.getChildren().size();
        for(Node n : node.getChildren()){
            size = getSize(n,size);
        }
        return size;

    }


    //    private static PythonNode parseString(PythonNode node, StreamTokenizer tokenizer) throws IOException {
//        node.setLabel(tokenizer.sval);
//        tokenizer.nextToken();
//        if (tokenizer.ttype == '(') {
//            tokenizer.nextToken();
//            do {
//                node.getChildren().add(parseString(new PythonNode(), tokenizer));
//            } while (tokenizer.ttype != ')');
//            tokenizer.nextToken();
//        }
//        return node;
//    }
@Override
    public void traverse() {
        // put together an ordered list of node labels of the tree
        traverse(root, labels);
    }

    private static ArrayList<String> traverse(Node node, ArrayList<String> labels) {
        for (int i = 0; i < node.getChildren().size(); i++) {
            labels = traverse(node.getChildren().get(i), labels);
        }
        labels.add(node.getLabel());
        return labels;
    }
    @Override
    public void index() {
        // index each node in the tree according to traversal method
        index(root, 0);
    }

    private static int index(Node node, int index) {
        for (int i = 0; i < node.getChildren().size(); i++) {
            index = index(node.getChildren().get(i), index);
        }
        index++;
        ((PythonNode) node).setIndex(index);
        return index;
    }
    @Override
    public void l() {
        // put together a function which gives l()
        leftmost();
        l = l((PythonNode) root, new ArrayList<Integer>());
    }

    private ArrayList<Integer> l(PythonNode node, ArrayList<Integer> l) {
        for (int i = 0; i < node.getChildren().size(); i++) {
            l = l((PythonNode) node.getChildren().get(i), l);
        }
        l.add(node.getLeftmost().getIndex());
        return l;
    }

    private void leftmost() {
        leftmost(root);
    }

    private static void leftmost(PythonNode node) {
        if (node == null)
            return;
        for (int i = 0; i < node.getChildren().size(); i++) {
            leftmost((PythonNode) node.getChildren().get(i));
        }
        if (node.getChildren().size() == 0) {
            node.leftmost = node;
        } else {
            PythonNode tempnode =  (PythonNode) node.children.get(0);
            node.leftmost = tempnode.getLeftmost();
        }
    }

    //LR_keyroots(T)= {klthere exists no k’> k such that/(k)= l(k’)}.

    @Override
    public void keyroots() {
        // calculate the keyroots
        for (int i = 0; i < l.size(); i++) {
            int flag = 0;
            for (int j = i + 1; j < l.size(); j++) {
                if (l.get(j) == l.get(i)) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                this.keyroots.add(i + 1);
            }
        }
    }


}