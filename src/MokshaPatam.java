import java.util.LinkedList;
import java.util.Queue;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: [YOUR NAME HERE]
 *
 */

public class MokshaPatam {
    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        // Inits queue that represents all the intergers you will visit
        Queue<Integer> toVisit = new LinkedList<Integer>();
        int currentPos = 1;
        toVisit.add(currentPos);

        int[] jumps = new int[boardsize + 1];
        boolean[] visited = new boolean[boardsize + 1];
        int[] numSteps = new int[boardsize + 1];

        // Sets up jumps by mapping the ladders and snakes to the array
        for(int i = 1; i < ladders.length + 1; i++){
            jumps[ladders[i-1][0]] = ladders[i-1][1];
        }
        for(int i = 1; i < snakes.length+1; i++){
            jumps[snakes[i-1][0]] = snakes[i-1][1];
        }

        while(!toVisit.isEmpty()){
            // Resets the current location based on the queue
            currentPos = toVisit.remove();
            visited[currentPos] = true;

            // If the current node is the last role then return # of rolls
            if(currentPos == boardsize){
                return numSteps[currentPos];
            }

            int node = 0;
            for(int i=1; i<7; i++){
                node = currentPos + i;

                // Prevents out of bounds error
                if(node <= boardsize) {
                    // If it is a beginning of a snake/ladder, move to the end of the jump
                    if (jumps[node] != 0) {
                        node = jumps[node];
                    }
                    // If node hasn't been visited, add to queue and update node's info
                    if (!visited[node]) {
                        // If you can get to this square with fewer steps, update nunSteps
                        if(numSteps[node] == 0 || numSteps[node] > numSteps[currentPos] + 1) {
                            numSteps[node] = numSteps[currentPos] + 1;
                        }
                        toVisit.add(node);
                    }
                    if(toVisit.isEmpty()){
                        return -1;
                    }
                }
            }
        }




        return 0;
    }
}
