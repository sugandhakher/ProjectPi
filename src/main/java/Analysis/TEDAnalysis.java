package analysis;

import report.*;
import program.*;

import java.util.ArrayList;

/**
 * TED analysis
 * use tree edit distance to check if two program has plagiarism
 * http://www.grantjenks.com/wiki/_media/ideas:simple_fast_algorithms_for_the_editing_distance_between_tree_and_related_problems.pdf
 */
public class TEDAnalysis implements Analysis {
    /**
     * variables
     */
    private Program programOrigin;
    private Program programCompare;
    int confidence;
    int treeEditDistance;
    static int[][] TD;
    private Report report;

    /**
     * Constructor taking Origin program and Compare program as arguments
     */
    public TEDAnalysis(Program o, Program c) {
        programOrigin = o;
        programCompare = c;
        confidence = 0;
        treeEditDistance = 0;
        report = new StandardReport();
        report.addStatistics("originFunNum", o.getAllTrees().size());
        report.addStatistics("compareFunNum", c.getAllTrees().size());
    }

    /**
     * return confidence of compare result
     */
    public int getConfidence() {
        return confidence;
    }

    /**
     * Algorithm that returns the tree edit distance between two AST trees
     */
    private int zhangShasha(SearchTree tree1, SearchTree tree2) {
        tree1.index();
        tree1.l();
        tree1.keyroots();
        tree1.traverse();
        tree2.index();
        tree2.l();
        tree2.keyroots();
        tree2.traverse();

        ArrayList<Integer> l1 = tree1.getLeftmost();
        ArrayList<Integer> keyroots1 = tree1.getKeyroots();
        ArrayList<Integer> l2 = tree2.getLeftmost();
        ArrayList<Integer> keyroots2 = tree2.getKeyroots();

        // space complexity of the algorithm
        TD = new int[l1.size() + 1][l2.size() + 1];

        // solve subproblems
        for (int i1 = 1; i1 < keyroots1.size() + 1; i1++) {
            for (int j1 = 1; j1 < keyroots2.size() + 1; j1++) {
                int i = keyroots1.get(i1 - 1);
                int j = keyroots2.get(j1 - 1);
                TD[i][j] = treedist(l1, l2, i, j, tree1, tree2);
            }
        }

        return TD[l1.size()][l2.size()];
    }

    /**
     * Return distance between each nodes in the two comparison trees
     */
    private int treedist(ArrayList<Integer> l1, ArrayList<Integer> l2, int i, int j, SearchTree tree1, SearchTree tree2) {
        int[][] forestdist = new int[i + 1][j + 1];

        // costs of the three atomic operations
        int Delete = 1;
        int Insert = 1;
        int Relabel = 1;

        forestdist[0][0] = 0;
        for (int i1 = l1.get(i - 1); i1 <= i; i1++) {
            forestdist[i1][0] = forestdist[i1 - 1][0] + Delete;
        }
        for (int j1 = l2.get(j - 1); j1 <= j; j1++) {
            forestdist[0][j1] = forestdist[0][j1 - 1] + Insert;
        }
        for (int i1 = l1.get(i - 1); i1 <= i; i1++) {
            for (int j1 = l2.get(j - 1); j1 <= j; j1++) {
                int i_temp = (l1.get(i - 1) > i1 - 1) ? 0 : i1 - 1;
                int j_temp = (l2.get(j - 1) > j1 - 1) ? 0 : j1 - 1;
                if ((l1.get(i1 - 1) == l1.get(i - 1)) && (l2.get(j1 - 1) == l2.get(j - 1))) {

                    int Cost = (tree1.getLabels().get(i1 - 1).equals(tree2.getLabels().get(j1 - 1))) ? 0 : Relabel;
                    forestdist[i1][j1] = Math.min(
                            Math.min(forestdist[i_temp][j1] + Delete, forestdist[i1][j_temp] + Insert),
                            forestdist[i_temp][j_temp] + Cost);
                    TD[i1][j1] = forestdist[i1][j1];
                } else {
                    int i1_temp = l1.get(i1 - 1) - 1;
                    int j1_temp = l2.get(j1 - 1) - 1;

                    int i_temp2 = (l1.get(i - 1) > i1_temp) ? 0 : i1_temp;
                    int j_temp2 = (l2.get(j - 1) > j1_temp) ? 0 : j1_temp;

                    forestdist[i1][j1] = Math.min(
                            Math.min(forestdist[i_temp][j1] + Delete, forestdist[i1][j_temp] + Insert),
                            forestdist[i_temp2][j_temp2] + TD[i1][j1]);
                }
            }
        }
        return forestdist[i][j];
    }


    /**
     * Return the report of similarity between two trees
     */
    @Override
    public Report getReport() {
        return report;
    }

    /**
     * Running analysis for comparison between trees and generating report
     */
    @Override
    public void runAnalysis() {
        for (SearchTree tree1 : programOrigin.getAllTrees()) {
            for (SearchTree tree2 : programCompare.getAllTrees()) {
                int size1 = tree1.size();
                int size2 = tree2.size();
                int sizeDiff = Math.abs(size1 - size2);
                boolean compare = true;
                if (tree1.getRoot().getLabel().equals("main") && size1 > 400)
                    compare = false;
                if (tree2.getRoot().getLabel().equals("main") && size2 > 400)
                    compare = false;

                if (compare) {
                    int distance = zhangShasha(tree1, tree2);
                    int aveSize = (size1 + size2) / 2;
                    double valve = 2.5;
                    if (distance == 0 || (double) aveSize / (double) distance > valve) {
                        Plagiarism p = new StandardPlagiarism();
                        p.addToOrigin(tree1.getURL(), tree1.getRoot().getStartline(), tree1.getRoot().getEndline());
                        p.addToCompare(tree2.getURL(), tree1.getRoot().getStartline(), tree2.getRoot().getEndline());
                        p.setConfident(100 - (distance * aveSize / 10));
                        report.addPlagiarism(p);
                    }

                }
            }
        }

    }

}
