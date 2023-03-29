#include <bits/stdc++.h>

using namespace std;

const int MAX = 1001;
const int MOD = 10007;

// dp[n] : 2 * n 크기의 사각형을 채우는 방법의 수
int dp[MAX];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin >> n;

    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 3;

    for (int i = 4; i <= n; i++){
        // 1) 맨 위의 칸이 2 * 1 로 덮히는 경우 : dp[i - 1]
        // 2) 맨 위의 칸이 1 * 2 로 덮히는 경우 : dp[i - 2]
        dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
    }

    cout << dp[n];

    return 0;
}
