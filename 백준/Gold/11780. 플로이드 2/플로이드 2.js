const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);
const m = parseInt(stdin[1]);

const edges = stdin
  .slice(2)
  .map((row) => row.split(" ").map((num) => parseInt(num)));

const INF = 1e9;

const adj = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(INF));
// 최단 경로를 추적하기 위한 배열
const nxt = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(0));

// init
const init = (arr) => {
  arr.forEach((_, i) => {
    arr[i][i] = 0;
  });
};

init(adj);
init(nxt);

// 데이터를 인접 행렬에 저장
edges.forEach((edge) => {
  const [src, dest, weight] = edge;
  adj[src][dest] = Math.min(adj[src][dest], weight);
  nxt[src][dest] = dest;
});

// 플로이드-워셜 알고리즘
const floyd = () => {
  for (let k = 1; k <= n; k++) {
    for (let i = 1; i <= n; i++) {
      for (let j = 1; j <= n; j++) {
        // 최단 경로 계산
        if (adj[i][j] > adj[i][k] + adj[k][j]) {
          adj[i][j] = adj[i][k] + adj[k][j];
          nxt[i][j] = nxt[i][k];
        }
      }
    }
  }
};

floyd();

// 각 정점 쌍에 대한 거리 배열
const dist = [];
for (let i = 1; i <= n; i++) {
  const temp = [];
  for (let j = 1; j <= n; j++) {
    if (adj[i][j] === INF) temp.push(0);
    else temp.push(adj[i][j]);
  }
  dist.push(temp);
}

// i - j 로 가는 최단거리 경로 복원
const paths = [];

for (let i = 1; i <= n; i++) {
  for (let j = 1; j <= n; j++) {
    const path = [];

    if (nxt[i][j] === 0) {
      paths.push(0);
      continue;
    }

    path.push(i);

    let current = i;
    let pathLength = 1;

    while (current !== j) {
      current = nxt[current][j];
      path.push(current);
      pathLength += 1;
    }

    path.unshift(pathLength);
    paths.push(path);
  }
}

console.log(dist.map((d) => d.join(" ")).join("\n"));
console.log(
  paths
    .map((path) => {
      return typeof path === "number" ? path : path.join(" ");
    })
    .join("\n")
);
