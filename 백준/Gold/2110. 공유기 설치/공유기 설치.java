import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int n, c;
    public static int[] locations; // 집의 좌표
    public final static int MAX_DIST = 2 * 1_000_000_000;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    // 최대 거리가 x일 때, c개의 공유기를 설치할 수 있나?
    public static int solve(){
        Arrays.sort(locations);

        int lo = -1;
        int hi = MAX_DIST + 1;

        while (lo + 1 < hi){
            int mid = lo + (hi - lo) / 2;

            if (check(mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    // C개의 공유기를 설치할 때, 가장 인접한 공유기의 거리가 target 이상인가?
    public static boolean check(int target){
        // 앞에서부터 공유기를 설치한다.
        int count = 1; // 첫번째는 무조건 설치
        int prev = 0;

        // 두 번째 집 부터 체크
        for (int i = 1; i < n; i++){
            // 설치할 수 있다면 설치한다. (이전에 설치한 인덱스와 비교)
            if (locations[i] - locations[prev] >= target) {
                count++;
                prev = i;
            }
        }

        return count >= c;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        c = Integer.parseInt(inputs[1]);
        locations = new int[n];

        for (int i = 0; i < n; i++) {
            locations[i] = Integer.parseInt(br.readLine());
        }
    }
}
