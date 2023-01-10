// input
const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const answer = [];

const makePIV = (n) => {
  let PIV = 1;
  while (PIV <= n) PIV *= 2;
  return PIV;
};

class IndexTree {
  constructor(PIV) {
    this.PIV = PIV;
    this.tree = Array.from({ length: PIV * 2 }, () => BigInt(0));
  }

  update(i, v) {
    let index = i + this.PIV;
    this.tree[index] = v;

    // 부모로 타고 올라가면서 값을 갱신한다.
    index = parseInt(index / 2);

    // 루트(1번 원소)까지
    while (index >= 1) {
      this.tree[index] = this.tree[2 * index] + this.tree[2 * index + 1];
      index = parseInt(index / 2);
    }
  }

  init(array) {
    for (let i = 0; i < array.length; i++) {
      this.update(i + 1, array[i]);
    }
  }

  // l에서 r까지의 구간 합
  query(l, r) {
    let sum = BigInt(0);
    let [left, right] = [l + this.PIV, r + this.PIV];

    while (left <= right) {
      if (left % 2 == 1) sum += this.tree[left++];
      if (right % 2 == 0) sum += this.tree[right--];

      [left, right] = [parseInt(left / 2), parseInt(right / 2)];
    }

    answer.push(BigInt(sum));
  }
}

const [n, m, k] = stdin[0].split(" ").map((num) => parseInt(num));
const numbers = stdin.slice(1, n + 1).map((num) => BigInt(num));
const ops = stdin
  .slice(n + 1)
  .map((row) => row.split(" ").map((num) => parseInt(num)));

const PIV = makePIV(n);
const tree = new IndexTree(PIV);
tree.init(numbers);

ops.forEach((op) => {
  op[0] === 1 ? tree.update(op[1], BigInt(op[2])) : tree.query(op[1], op[2]);
});

console.log(answer.join("\n"));
