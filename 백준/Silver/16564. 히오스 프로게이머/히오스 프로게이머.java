import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int n, k;
    public static long[] levels;
    public static final long MAX_LEVEL = 2_000_000_000;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    public static long solve(){
        long lo = -1;
        long hi = MAX_LEVEL + 1;

        while (lo + 1 < hi){
            long mid = lo + (hi - lo) / 2;

            if (isTargetLevelPossible(mid)){
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    // 주어진 k에서 최소레벨 target가 가능한가?
    // 각 레벨 별로 최소 레벨이 되려면 얼만큼 더 레벨업 시켜줘야하는지 구한다.
    public static boolean isTargetLevelPossible(long target){
        long requiredLevelUpSum = 0; // target 레벨을 위해 올려야 하는 레벨의 합

        for (var level: levels){
            if (level < target){
                requiredLevelUpSum += (target - level);
            }
        }

        return requiredLevelUpSum <= k;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        k = Integer.parseInt(inputs[1]);
        levels = new long[n];

        for (int i = 0; i < n; i++){
            levels[i] = Long.parseLong(br.readLine());
        }
    }
}
