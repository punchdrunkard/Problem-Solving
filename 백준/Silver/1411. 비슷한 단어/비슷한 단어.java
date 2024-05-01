import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n;
    static String[] words;

    public static void main(String[] args) {
        init();
        System.out.println(solve());
    }

    static int solve() {
        List<Integer>[] wordComposition = calcWordComposition();

        int len = words[0].length(); // 모든 단어의 길이는 같음
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean isSimilar = true;

                for (int k = 0; k < len; k++) {
                    if (wordComposition[i].get(k) != wordComposition[j].get(k)) {
                        isSimilar = false;
                        break;
                    }
                }

                if (isSimilar) {
                    count++;
                }
            }
        }

        return count;
    }

    static List<Integer>[] calcWordComposition() {
        List<Integer>[] wordComposition = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            wordComposition[i] = new ArrayList<>();
        }

        // 각 단어의 구성이 어떻게 되는지 구한다.
        for (int j = 0; j < n; j++) {
            String word = words[j];

            Map<Character, Integer> alphaMap = new HashMap<>();

            int count = 0;

            for (int i = 0; i < word.length(); i++) {
                char current = word.charAt(i);

                if (!alphaMap.containsKey(current)) {
                    alphaMap.put(word.charAt(i), count);
                    count++;
                }

                wordComposition[j].add(alphaMap.get(current));
            }
        }

        return wordComposition;
    }


    static void init() {
        n = scan.nextInt();
        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = scan.next();
        }
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int nextInt() {
            return Integer.parseInt(next());
        }

        String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return st.nextToken();
        }

    }
}
