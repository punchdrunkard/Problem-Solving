import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int n;
    public static long liquids[];
    public static final long LIQUID_MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void solve() {
        long[] answer = new long[3]; // 정답 배열
        long diff = 3 * LIQUID_MAX;

        Arrays.sort(liquids);

        for (int i = 0; i < n - 1; i++){
            for (int j = i + 1; j < n; j++){
                long target = -(liquids[i] + liquids[j]);

                // 이분탐색을 하면 target 과 가까운 두 원소를 찾을 수 있음
                // 가까운 두 원소를 통해 구할 수 있는 diff로 답을 찾아보자.
                int lo = lowerBound(j + 1, n, target, liquids);
                int hi = lo + 1;

                // 범위 체크하기
                if (!isValidRange(lo) && !isValidRange(hi)) continue;

                // 특성 값이 0에 가까워야 한다.
                if (!isValidRange(hi)){  // 하나만 valid range인 경우
                    long currentSum = Math.abs(-target + liquids[lo]);
                    if (diff < currentSum) continue;

                    diff = currentSum;
                    answer = new long[]{liquids[i], liquids[j], liquids[lo]};

                } else {  // 둘 다 valid range 인 경우
                    long loSum = Math.abs(-target + liquids[lo]);
                    long hiSum = Math.abs(-target + liquids[hi]);

                    if (loSum < hiSum){
                        if (diff < loSum) continue;
                        diff = loSum;
                        answer = new long[]{liquids[i], liquids[j], liquids[lo]};

                        if (diff == 0){
                            printAnswer(answer);
                            return;
                        }
                    } else {
                        if (diff < hiSum) continue;
                        diff = hiSum;
                        answer = new long[]{liquids[i], liquids[j], liquids[hi]};

                        if (diff == 0){
                            printAnswer(answer);
                            return;
                        }
                    }
                }
            }
        }

        printAnswer(answer);
    }

    public static int lowerBound(int start, int end, long target, long[] arr){
        int lo = start;
        int hi = end;

        while (lo + 1 < hi){
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] <= target){
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    public static boolean isValidRange(int index){
        return 0 <= index && index < n;
    }

    public static void printAnswer(long[] answer) {
        String result = Arrays.stream(answer).mapToObj(Long::toString).reduce((s1, s2) -> s1 + " " + s2).orElse("");
        System.out.println(result);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        liquids = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    }
}
