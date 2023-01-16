const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n] = stdin[0].split(" ").map((num) => parseInt(num));
const orders = stdin.slice(1);

const graph = Array.from({ length: n + 1 }, () => new Array());
const inDegree = Array.from({ length: n + 1 }, () => 0);

const topologySort = () => {
  const q = [];
  const result = [];

  for (let i = 1; i <= n; i++) {
    if (inDegree[i] === 0) {
      q.push(i);
    }
  }

  while (q.length) {
    const front = q.shift();
    result.push(front);

    for (const v of graph[front]) {
      inDegree[v] -= 1;

      if (inDegree[v] === 0) {
        q.push(v);
      }
    }
  }

  console.log(result.join(" "));
};

orders.forEach((order) => {
  const [a, b] = order.split(" ").map((num) => parseInt(num));
  graph[a].push(b);
  inDegree[b] += 1;
});

topologySort();
