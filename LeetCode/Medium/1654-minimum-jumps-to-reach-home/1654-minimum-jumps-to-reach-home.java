class Solution {

    static final int X_LIMIT = 6002;

    static final int NOT_BACKWARD = 0;
    static final int BACKWARD = 1;
    
    boolean[] isForbidden;
    boolean[][] visited;
    
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        init(forbidden);
        return solve(a, b, x);
    }

    int solve(int a, int b, int target) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, NOT_BACKWARD));
        visited[0][NOT_BACKWARD] = true;
        int dist = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Pair current = q.poll();
                
                if (current.pos == target) {
                    return dist;
                }

                List<Pair> candidates = new ArrayList<>();
                // Forward jump
                if (!isOutOfRange(current.pos + a) && !visited[current.pos + a][NOT_BACKWARD] && !isForbidden[current.pos + a]) {
                    candidates.add(new Pair(current.pos + a, NOT_BACKWARD));
                    visited[current.pos + a][NOT_BACKWARD] = true;
                }
                
                // Backward jump (only if the last jump wasn't backward)
                if (current.state != BACKWARD && !isOutOfRange(current.pos - b) && !visited[current.pos - b][BACKWARD] && !isForbidden[current.pos - b]) {
                    candidates.add(new Pair(current.pos - b, BACKWARD));
                    visited[current.pos - b][BACKWARD] = true;
                }

                q.addAll(candidates);
            }

            dist++;
        }

        return -1;
    }

    boolean isOutOfRange(int x) {
        return x < 0 || x >= X_LIMIT;
    }

    void init(int[] forbidden) {
        isForbidden = new boolean[X_LIMIT];
        for (int pos: forbidden) {
            isForbidden[pos] = true;
        }

        visited = new boolean[X_LIMIT][2];  // Initialize visited for both states
    }

    class Pair {
        int pos, state;

        Pair(int pos, int state) {
            this.pos = pos;
            this.state = state;
        }
    }
}
