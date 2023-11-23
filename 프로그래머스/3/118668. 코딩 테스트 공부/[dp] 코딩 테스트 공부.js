// dp 풀이

const INF = Infinity;

// 1. 목표 alp, cop 찾기
const findTargetStatus = (problems, alp, cop) => {
  return problems.reduce(
    (acc, val) => {
      acc[0] = Math.max(acc[0], val[0], alp);
      acc[1] = Math.max(acc[1], val[1], cop);
      return acc;
    },
    [-INF, -INF]
  );
};

function solution(alp, cop, problems) {
  const [alp_target, cop_target] = findTargetStatus(problems, alp, cop);
  
  const dp = Array.from({ length: alp_target + 1 }, () => Array(cop_target + 1).fill(INF));
    
  dp[alp][cop] = 0;

  for (let i = alp; i <= alp_target; i++) {
    for (let j = cop; j <= cop_target; j++) {
      let al = Math.min(i + 1, alp_target);
      let co = Math.min(j + 1, cop_target);

      dp[al][j] = Math.min(dp[al][j], dp[i][j] + 1);
      dp[i][co] = Math.min(dp[i][co], dp[i][j] + 1);

      problems.forEach((p) => {
        const [alp_req, cop_req, alp_rwd, cop_rwd, cost] = p;
        
        // 문제를 풀 수 있는 경우
        if (i >= alp_req && j >= cop_req) {
          let al = Math.min(i + alp_rwd, alp_target);
          let co = Math.min(j + cop_rwd, cop_target);

          dp[al][co] = Math.min(dp[al][co], dp[i][j] + cost);
        }
      });
    }
  }

  return dp[alp_target][cop_target];
}
