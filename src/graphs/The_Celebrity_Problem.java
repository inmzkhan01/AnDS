package graphs;

public class The_Celebrity_Problem {
    //Celebrity doesn't exists.
    /*static int[][] matrix = {
            {0, 0, 1, 0, 1},
            {0, 0, 1, 0, 1},
            {0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1},
            {0, 0, 1, 0, 0}
    };*/

    //Celebrity exists.
    static int[][] matrix = {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 1, 0},
    };

    public static void main(String[] args) {
        int id = findCelebrity(matrix.length);
        System.out.println("Celebrity ID: " + id);
    }

    static int findCelebrity(int n) {

        int celeb = 0;

        for (int i = 1; i < n; i++) {
            if (knows(celeb, i)) {
                celeb = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != celeb && (knows(celeb, i) || !knows(i, celeb))) {
                return -1;
            }
        }

        return celeb;
    }

    static boolean knows(int celeb, int i) {
        return matrix[celeb][i] == 1;
    }

}