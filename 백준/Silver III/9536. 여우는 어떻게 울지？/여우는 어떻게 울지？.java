import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static final String finalLine = "what does the fox say?";

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int t = scan.nextInt();

        for (int i = 0; i < t; i++){
            String[] sounds = scan.nextLine().split(" ");
            Map<String, Boolean> isFox = new HashMap<>();
            for (String sound: sounds){
                isFox.put(sound, true);
            }

            String line;
            while (!(line = scan.nextLine()).equals(finalLine)) {
                String[] info = line.split(" ");
                String sound = info[2];
                isFox.put(sound, false);
            }

            for (String sound : sounds){
                if (isFox.get(sound)) {
                    sb.append(sound).append(" ");
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
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
