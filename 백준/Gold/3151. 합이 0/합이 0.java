import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BiPredicate;

public class Main {
    static int n;
    static int[] a;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    public static long solve() {
        long answer = 0;
        Arrays.sort(a);

        // a[i] + a[j] + a[k] = 0 이 되는 (i, j, k)의 갯수는?
        // => a[k] = -(a[i] + a[j]) 가 성립하는 k 찾기
        // 따라서, 모든 i, j 쌍에 대하여 (n^2) 조건을 만족하는 k를 찾는다. (logN)
        // => O(n^2 logN)
        for (int i = 0; i < n - 1; i++){
            for (int j = i + 1; j < n; j++){
                int target = -(a[i] + a[j]);
                answer += (upperBound(j, n, target, a) - lowerBound(j, n, target, a));
            }
        }

        return answer;
    }

    // target 보다 큰 최초의 index
    public static int upperBound(int start, int end, int target, int[] arr) {
        return binarySearch(start, end, target, arr, (midVal, targ) -> midVal <= targ);
    }

    // target 보다 크거나 같은 최초의 index
    public static int lowerBound(int start, int end, int target, int[] arr) {
        return binarySearch(start, end, target, arr, (midVal, targ) -> midVal < targ);
    }

    public static int binarySearch(int start, int end, int target, int[] arr, BiPredicate<Integer, Integer> condition){
        int lo = start;
        int hi = end;

        while (lo + 1 < hi){
            int mid = lo + (hi - lo) / 2;

            if (condition.test(arr[mid], target)){
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
