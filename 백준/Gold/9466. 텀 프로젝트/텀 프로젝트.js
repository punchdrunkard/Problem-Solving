const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const T = stdin[0];

for (let i = 0; i < T; i++) {
  const size = parseInt(stdin[2 * i + 1]);
  const choose = stdin[2 * i + 2].split(" ").map((num) => parseInt(num) - 1);

  let count = 0;

  const visited = new Array(size).fill(false);
  const finished = new Array(size).fill(false);

  const dfs = (v) => {
    visited[v] = true;
    const next = choose[v];

    if (visited[next]) {
      if (!finished[next]) {
        // cycle 찾아가기
        let temp = choose[next];
        while (temp !== next) {
          count += 1;
          temp = choose[temp];
        }
        count += 1; // 자기 자신
      }
    } else {
      dfs(next);
    }

    finished[v] = true;
  };

  for (let v = 0; v < size; v++) {
    if (!visited[v]) dfs(v, 0);
  }

  console.log(size - count);
}
