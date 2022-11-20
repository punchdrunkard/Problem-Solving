// 0 -> 바다
// 그 외의 숫자 -> 빙산

// 배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는
// 일년마다 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다.

// 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간

// 만일 전부 다 녹을 때 까지 두 덩어리 이상으로 분리되지 않으면 0을 출력

// 덩어리 => 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다.

// 덩어리 찾는 법 => connected component 찾기 (BFS)

const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// 입력
const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

// 매 년 빙산을 녹이면서 connected component 의 개수를 찾는다.

const [N, M] = stdin[0].split(" ").map((num) => parseInt(num));
let graph = [];

stdin.slice(1).map((line) => {
  graph.push(line.split(" ").map((num) => parseInt(num)));
});

// 상하좌우를 탐색한다 => BFS 가 필요함
// 해당 좌표의 상하좌우를 보고 상하좌우가 0이면 해당 좌표의 값을 그만큼 감소시키는 방식의 bfs

const queue = [];

// .. 큐가 굳이 필요할까?
// 일단 빙산을 기준으로 상하좌우를 봐야하는 테크닉이 필요한건 사실임

// 그래프에서 빙산의 위치 정도는 알고 있어야 할 것 같아
let year = 0;
let allMelted = false;

const isAllMelted = (graph) => {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (graph[i][j] !== 0) return false;
    }
  }
  allMelted = true;
  return true;
};

const dx = [0, 0, -1, 1];
const dy = [-1, 1, 0, 0];

let visited = Array.from(Array(N), () => new Array(M).fill(false));

// let connected = 0;

const bfs = (x, y) => {
  // console.log("bfs", x, y);
  const q = [];
  visited[x][y] = true;
  q.push([x, y]);

  while (q.length !== 0) {
    // const temp = q.shift();
    // console.log(temp);
    // console.log("temp", temp);
    const [currentX, currentY] = q.shift();

    for (let dir = 0; dir < 4; dir++) {
      const nextX = currentX + dx[dir];
      const nextY = currentY + dy[dir];

      // 범위
      if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
      // 방문, 빙산
      if (visited[nextX][nextY] || graph[nextX][nextY] === 0) continue;

      // console.log("next", nextX, nextY);
      visited[nextX][nextY] = true;
      q.push([nextX, nextY]);
    }
  }

  // console.log("-----------");
  // for (let i = 0; i < N; i++) {
  //   const row = [];
  //   for (let j = 0; j < M; j++) {
  //     row.push(visited[i][j]);
  //   }
  //   console.log(row.join(" "));
  // }
};

const findConnected = () => {
  let connected = 0;

  // 연결 요소 갯수 찾기
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (!visited[i][j] && graph[i][j] != 0) {
        // console.log("i", i, "j", j, "graph[i][j]", graph[i][j]);
        bfs(i, j);
        connected += 1;
      }
    }
  }

  // console.log("connected", connected);
  return connected;
};

while (!isAllMelted(graph)) {
  // console.log("while");
  // 녹인 후

  // console.log("-----------");
  // for (let i = 0; i < N; i++) {
  //   const row = [];
  //   for (let j = 0; j < M; j++) {
  //     row.push(graph[i][j]);
  //   }
  //   console.log(row.join(" "));
  // }

  const number = findConnected(graph);
  // console.log("connected Number", number);

  if (number >= 2) {
    // console.log("END!");
    break;
  }

  // 녹이기

  // 녹인 그래프를 저장할 배열
  const meltedGraph = [];

  for (let i = 0; i < N; i++) {
    const row = [];
    for (let j = 0; j < M; j++) {
      row.push(graph[i][j]);
    }
    meltedGraph.push(row);
  }

  // console.log("melted", meltedGraph);
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      // 빙산에 대해서...
      if (graph[i][j] > 0) {
        // console.log("현재 좌표", i, j, graph[i][j]);
        // 동, 서 남, 북
        for (let dir = 0; dir < 4; dir++) {
          const nextX = i + dx[dir];
          const nextY = j + dy[dir];

          // 범위
          if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;

          // 해당 칸이 빙산인 경우에는
          if (graph[nextX][nextY] === 0) {
            // console.log("nextX", nextX, "nextY", nextY, graph[nextX][nextY]);

            if (meltedGraph[i][j] > 0) {
              meltedGraph[i][j] -= 1;
            }
          }
        } // end of for
      } // end of if
    }
  }

  graph = [];
  for (let i = 0; i < N; i++) {
    const row = [];
    for (let j = 0; j < M; j++) {
      row.push(meltedGraph[i][j]);
    }
    graph.push(row);
  }

  // console.log("connected", connected);
  // visited 초기화
  visited = Array.from(Array(N), () => new Array(M).fill(false));
  year += 1;

  // connected = 0;
}

// console.log(allMelted);
if (allMelted) console.log(0);
else console.log(year);
