package Program;

import java.util.List;

public class JoinCallVisitor implements Visitor {
    private List<SearchTree> allFunctions;
    public JoinCallVisitor(Program p){
        allFunctions = p.getAllTrees();
    }
    @Override
    public void visit(Node node) {
        if(node.getLabel() == "atom"){
            for(SearchTree tree : allFunctions){
                if(node.getContext().equals(tree.getRoot().getContext())){
                    Node firstStatement = tree.getRoot().getChildren().get(1)
                            .getChildren().get(0).getChildren().get(0);
                    if(firstStatement.getLabel().equals("return_stmt"))
                        node.addChild(firstStatement.getChildren().get(0));
                    else
                        for(Node n : tree.getRoot().getChildren().get(1).getChildren()){
                            node.addChild(n);
                        }

                }
            }

        }
    }
}
