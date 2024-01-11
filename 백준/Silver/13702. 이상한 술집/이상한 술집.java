import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, k; // 주전자 개수, 친구들의 수
    static long[] cups;
    static final long MAX = 1L << 31;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    public static long solve(){
        long lo = -1;
        long hi = MAX + 1;

        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;

            if (check(mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    // 막걸리 x 양만큼을 k명에게 분배할 수 있는가?
    public static boolean check(long target){
        long count = 0;
        long[] distrubuted = Arrays.copyOf(cups, cups.length);

        int idx = 0; // 주전자 idx

        while (idx < n){
            if (distrubuted[idx] > target) {
                distrubuted[idx] -= target;
                count++;
            } else if (distrubuted[idx] == target){
                distrubuted[idx] -= target;
                count++;
                idx++;
            } else {
                idx++;
            }

            if (count >= k) return true;
        }

        return false;
    }


    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        k = Integer.parseInt(inputs[1]);
        cups = new long[n];

        for (int i = 0; i < n; i++) {
            cups[i] = Long.parseLong(br.readLine());
        }
    }
}
