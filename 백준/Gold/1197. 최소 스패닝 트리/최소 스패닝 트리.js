const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n] = stdin[0].split(" ").map((num) => parseInt(num));
const edges = stdin.slice(1).map((row) => {
  const [start, end, weight] = row.split(" ").map((num) => parseInt(num));
  return {
    start,
    end,
    weight,
  };
});

const compare = (edge1, edge2) => {
  return edge1.weight - edge2.weight;
};

const parent = Array.from({ length: n + 1 }, (_, i) => i);

const find = (a) => {
  if (parent[a] == a) return a;
  const result = find(parent[a]);
  parent[a] = result;
  return result;
};

const union = (a, b) => {
  a = find(a);
  b = find(b);
  if (a == b) return;
  parent[a] = b;
};

const MST = (n, edges) => {
  let [edgeCount, mstWeight] = [0, 0];

  edges.sort(compare);

  for (let i = 0; i < edges.length; i++) {
    let { start, end, weight } = edges[i];
    if (find(start) == find(end)) continue;
    union(start, end);
    edgeCount += 1;
    mstWeight += weight;

    if (edgeCount == n - 1) {
      return mstWeight;
    }
  }

  return -1;
};

console.log(MST(n, edges));
