import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n;
    static Map<String, Integer> pos = new HashMap<>();
    static Queue<String>[] q;
    static String target;
    static StringTokenizer st;

    public static void main(String[] args) {
        init();
        System.out.println(isPossible() ? "Possible" : "Impossible");
    }

    static boolean isPossible() {
        st = new StringTokenizer(target);

        while (st.hasMoreTokens()) {
            String word = st.nextToken();

            if (!pos.containsKey(word)) {
                return false;
            }

            int idx = pos.get(word);
            String front = q[idx].poll();

            if (!word.equals(front)) {
                return false;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!q[i].isEmpty()) {
                return false;
            }
        }
        
        return true;
    }

    static void init() {
        n = scan.nextInt();
        q = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            q[i] = new LinkedList<>();

            String s = scan.nextLine();
            st = new StringTokenizer(s);

            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                q[i].offer(word);
                pos.put(word, i);
            }
        }

        target = scan.nextLine();
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return st.nextToken();
        }

        public String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
