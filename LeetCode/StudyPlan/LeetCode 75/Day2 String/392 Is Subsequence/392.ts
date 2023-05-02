// Runtime 87ms Beats 5.88% 시간 복잡도 : O(n**2)
// Memory 45.1 MB Beats 6.40 %

// 주의! DP는 문제를 푸는 방법론 중 하나일 뿐 항상 Optimal을 보장하지 않는다.

export const isSubsequence = (s: string, t: string): boolean => {
  const dp = Array.from({ length: s.length }, () =>
    Array(t.length).fill(false)
  );

  // 예외 처리
  if (s.length === 0 && t.length === 0) {
    return true;
  }

  if (s.length === 0) {
    return true;
  }

  if (t.length === 0) {
    return false;
  }

  // initialize (for first row)
  for (let i = 0; i < t.length; i++) {
    if (s[0] == t[i]) {
      dp[0][i] = true;
      for (let j = i + 1; j < t.length; j++) {
        dp[0][j] = true;
      }
      continue;
    }
  }

  // fill dp table
  for (let i = 1; i < s.length; i++) {
    for (let j = 1; j < t.length; j++) {
      dp[i][j] = dp[i][j - 1] || (dp[i - 1][j - 1] && s[i] === t[j]);
    }
  }

  return dp[s.length - 1][t.length - 1];
};
