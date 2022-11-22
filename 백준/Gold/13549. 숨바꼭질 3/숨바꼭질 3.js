const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n, k] = stdin[0].split(" ").map((num) => parseInt(num));

const MAX = 100001;
const dist = new Array(2 * MAX).fill(-1);

const isValidRange = (x) => {
  return x >= 0 && x < 2 * MAX;
};

const canVisit = (x) => {
  return isValidRange(x) && !(dist[x] > 0);
};

const q = [];
q.push([n, false]);
dist[n] = 0;

while (dist[k] < 0) {
  const [x, isTeleport] = q.shift();

  if (!isTeleport && x !== 0) {
    let nx = 2 * x;
    while (nx <= MAX) {
      if (canVisit(nx)) {
        if (dist[nx] !== -1) {
          dist[nx] = Math.min(dist[nx], dist[x]);
        } else dist[nx] = dist[x];

        dist[nx] = dist[x];
        q.push([nx, false]);
      }

      nx *= 2;
    }
  }

  const nextX = [x - 1, x + 1];

  for (let nx of nextX) {
    if (canVisit(nx)) {
      if (dist[nx] !== -1) {
        dist[nx] = Math.min(dist[nx], dist[x] + 1);
      } else dist[nx] = dist[x] + 1;
      q.push([nx, false]);
    }
  }
}

console.log(dist[k]);
