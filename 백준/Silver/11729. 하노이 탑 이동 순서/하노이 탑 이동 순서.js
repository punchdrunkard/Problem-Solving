const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const answer = [];

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

// a 번 기동에서 b 번 기둥으로 n 개의 원판을 이동한다.
const move = (a, b, n) => {
  // base condition : n = 1 일 때
  if (n === 1) {
    answer.push(`${a} ${b}`);
    return;
  }
  // a 번 기동에서 a, b 도 아닌 기둥으로 n - 1개의 원반을 옮기고
  move(a, 6 - a - b, n - 1);
  // 남은 n 번째 원반을 원하는 곳으로 옮긴다.
  answer.push(`${a} ${b}`);
  // 6 - a - b 번째에 있던 남아있는 n - 1 개의 원반을 다시 원하는 곳으로 옮긴다.
  move(6 - a - b, b, n - 1);
};

const k = parseInt(stdin[0]);
move(1, 3, k);
console.log(2 ** k - 1);
console.log(answer.join("\n"));
