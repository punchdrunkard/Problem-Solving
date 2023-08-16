## 문제

https://www.codetree.ai/missions/2/problems/number-of-unique-bst?utm_source=clipboard&utm_medium=text

## 풀이

점화식을 세우는게 중요하다.
경우의 수를 세는 문제에서 점화식을 세울 때, **합의 법칙**과 **곱의 법칙**을 잘 생각해보자.

문제에서 BST를 만들 때, BST의 **루트가 무엇이냐**에 따라서 다른 BST가 만들어진다. 따라서 BST를 루트 별로 나누고 이를 더한다.

**한 루트 안에서는** 트리의 왼쪽, 오른쪽을 생각하는데 이 트리가 **BST**이기 때문에 트리의 왼쪽의 경우 **항상 해당 루트보다 작은 수들에 의해서 트리가 만들어지고**, 오른쪽의 경우 **항상 해당 루트보다 큰 수들에 의해서 트리가 만들어진다.**

또한, 문제의 조건에 따라서 들어올 수 있는 수가 제한적이므로 (입력으로 `n`이 들어왔을 때 `1 ~ n` 까지의 숫자들만 트리의 노드로 사용되므로) 다음과 같이 점화식을 세울 수 있다.

```
dp[n] = (n보다 작은 노드들로 만들 수 있는 트리의 갯수) * (n보다 큰 노드들로 만들 수 있는 트리의 갯수)
```

## 코드

```c++
#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

int main() {
    FASTIO;

    int n;
    cin >> n;

    // dp[n] = 1부터 n까지의 숫자들을 단 한번씩만 써서 만들 수 있는
    // 노드의 개수가 n인 서로 다른 이진 탐색 트리의 개수
    vector<int> dp(n + 1, 0);
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= n; i++){
        // 트리의 루트 j에 대하여
        int count = 0;

        for (int j = 1; j <= i; j++){
            count += dp[j - 1] * dp[i - j];
        }

        dp[i] += count;
    }

    cout << dp[n];

    return 0;
}
```
