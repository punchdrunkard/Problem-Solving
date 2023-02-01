const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);

const eggs = stdin
  .slice(1)
  .map((row) => row.split(" ").map((num) => parseInt(num)));

const HP = 0;
const WEIGHT = 1;

let answer = 0;

const crash = (index, count) => {
  // 가장 오른쪽 계란이면 종료한다.
  if (index === n) {
    if (count > answer) answer = count;
    return;
  }

  // 현재 계란이 꺠져있는 경우
  if (eggs[index][HP] <= 0 || count === n - 1) {
    crash(index + 1, count);
    return;
  }

  for (let i = 0; i < n; i++) {
    if (i === index) continue; // 자기 자신
    if (eggs[i][HP] <= 0) continue; // 이미 깨져있는 경우

    // index 번 째 계란으로 i번쨰 계란을 치기
    eggs[i][HP] -= eggs[index][WEIGHT];
    eggs[index][HP] -= eggs[i][WEIGHT];

    if (eggs[i][HP] <= 0) count += 1;
    if (eggs[index][HP] <= 0) count += 1;

    // 다음으로 넘어가기
    crash(index + 1, count);

    // backtracking
    if (eggs[i][HP] <= 0) count -= 1;
    if (eggs[index][HP] <= 0) count -= 1;

    eggs[i][HP] += eggs[index][WEIGHT];
    eggs[index][HP] += eggs[i][WEIGHT];
  }
};

crash(0, 0);

console.log(answer);
