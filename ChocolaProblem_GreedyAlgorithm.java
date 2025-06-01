// We are given a bar of chocolate composed of m x n square pieces. One should break the chocolate into single squares. Each break of a part of the chocolate is charged a cost expressed by a positive integer. This cost does not depend on the size of the part that is being broken but only depends on the line the break goes along. Let us denote the costs of breaking along consecutive vertical lines with x1, x2, ...., xm-1 and along horizontal lines with y1, y2, ..., yn-1.
// Compute the minimal cost of breaking the whole chocolate into single squares.

// Check out the screen shot on Oct 13

// Observations :
/*
1. Every line of the grid must go through the cut
2. After each cut, the complement line has to suffer multiple cuts leading to increased cost
 */
// Conclusion : Expensive cuts first !!!!!!!
// Also :
// Cost of vertical cut = horizontal pieces * cost of that vertical line cut
// Cost of horizontal cut = vertical pieces * cost of that horizontal line cut
import java.util.Arrays;
import java.util.Collections;

public class ChocolaProblem_GreedyAlgorithm {

    public static void main(String[] args) {
        // 4 x 6 (n x m) Grid
        Integer[] costVer = { 2, 1, 3, 1, 4 }; // m - 1
        Integer[] costHor = { 4, 1, 2 }; // n - 1

        Arrays.sort(costVer, Collections.reverseOrder());
        Arrays.sort(costHor, Collections.reverseOrder());

        int h = 0, v = 0; // Pointers to traverse the arrays
        int hp = 1, vp = 1; // Horizontal and Vertical pieces

        int cost = 0;
        while (v < costVer.length && h < costHor.length) {
            if (costVer[v] <= costHor[h]) { // Perform Horizontal Cut
                cost += (vp * costHor[h]); // Horizontal cut goes through vertical pieces
                hp++;
                h++;
            } else { // Perform Horizontal Cut
                cost += (hp * costVer[v]); // Horizontal cut goes through vertical pieces
                vp++;
                v++;
            }
        }

        // Remaining Horizontal cuts
        while (h < costHor.length) {
            cost += (vp * costHor[h]);
            hp++;
            h++;
        }

        // Remaining Vertical cuts
        while (v < costVer.length) {
            cost += (hp * costVer[v]);
            vp++;
            v++;
        }

        System.out.println(cost);
    }
}