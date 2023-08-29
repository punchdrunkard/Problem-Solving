const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const n = parseInt(stdin[0]);

// 가장 견딜 수 있는 무게가 큰 로프부터 고려한다.
const ropes = stdin
  .slice(1)
  .map(Number)
  .sort((a, b) => b - a);

let answer = 0;

// i개의 로프를 이용하여 무게를 들 경우
for (let i = 1; i <= n; i++) {
  // i개의 로프를 사용했을 때, 견딜 수 있는 최대 무게
  // 무게는 가장 약한 로프가 견뎌야하기 때문에, 무게 제한(ropes[i - 1])에 로프의 갯수 i를 곱함
  const currentWeight = ropes[i - 1] * i;
  answer = Math.max(answer, currentWeight);
}

console.log(answer);
