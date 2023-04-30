const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

let answer = 0;

const n = parseInt(stdin[0]); // 회의의 수
// [회의의 시작시간, 끝나는 시간]
const meetings = stdin
  .slice(1)
  .map((value) => value.split(' ').map((num) => parseInt(num)));

let time = 0; // 현재 시점

// 끝나는 시점으로 회의를 정렬
meetings.sort((a, b) => {
  if (a[1] === b[1]) {
    return a[0] - b[0];
  }
  return a[1] - b[1];
});

// 지금 시점에서 가장 "빨리 끝나는" 회의를 배정
meetings.forEach((meeting) => {
  let [startTime, endTime] = meeting;
  if (startTime >= time) {
    time = endTime;
    answer += 1;
  }
});

console.log(answer);
