// 이 문제의 경우, 클래스를 이용하는 경우 메모리 초과가 발생
// 또한 fs 모듈을 사용할 수 없다.

const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n] = stdin[0].split(" ").map((num) => parseInt(num));
const opers = stdin
  .slice(1)
  .map((row) => row.split(" ").map((num) => parseInt(num)));
const answer = [];

class UnionFind {
  constructor(n) {
    this.parents = Array.from({ length: n + 1 }, (_, i) => i);
  }

  find(a) {
    if (this.parents[a] === a) return a;
    result = this.find(parent[a]);
    this.parents[a] = result;
    return result;
  }

  union(a, b) {
    const [parent_a, parent_b] = [this.find(a), this.find(b)];
    if (parent_a === parent_b) return;
    this.parents[parent_a] = parent_b;
  }
}

opers.forEach((oper) => {
  const [op, a, b] = oper;
  const sets = new UnionFind(n);

  switch (op) {
    case 0:
      sets.union(a, b);
      break;
    case 1:
      answer.push(sets.find(a) === sets.find(b) ? "YES" : "NO");
      break;
  }
});

console.log(answer.join("\n"));
