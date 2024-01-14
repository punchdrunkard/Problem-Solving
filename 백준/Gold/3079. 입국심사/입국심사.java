import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;
    static long[] times;

    // 나올 수 있는 최댓값 : 가장 오래 걸리는 심사대에 모두 들어갈 경우
    static long MAX_TIME_LIMIT = 1000000000000000000L; // 10**18


    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    // 심사대가 m명의 친구들을 심사하는데 걸리는 시간의 최솟값
    // -> 주어진 시간 x에 심사대가 m명의 친구들을 심사할 수 있는가?
    public static long solve() {
        long lo = 0;
        long hi = MAX_TIME_LIMIT;

        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;

            // m명 이상 심사할 수 있는 경우 ->  시간을 줄인다.
            if (canProcessAllFriends(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return hi;
    }

    // timeLimit 안에 m명 이상을 검사할 수 있는가?
    public static boolean canProcessAllFriends(long timeLimit) {
        // 각 심사대에서 검사할 수 있는 사람의 수
        long availablePeopleCount = 0;

        for (long time: times){
            availablePeopleCount += (timeLimit / time);
            // 오버플로우 조심!!!!!
            if (availablePeopleCount >= m) return true;
        }

        return false;
    }

    static void input() throws IOException {
        int[] values = parseToIntArray(br.readLine());
        n = values[0];
        m = values[1];
        times = new long[n];

        for (int i = 0; i < n; i++){
            times[i] = Integer.parseInt(br.readLine());
        }
    }

    static int[] parseToIntArray(String line) {
        return Pattern.compile(" ").splitAsStream(line).mapToInt(Integer::parseInt).toArray();
    }
}
