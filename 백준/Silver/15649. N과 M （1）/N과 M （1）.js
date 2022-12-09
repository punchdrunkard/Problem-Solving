// 백트래킹을 이용한 풀이

const inputData = () => {
  const input = require("fs");
  const data = input
    .readFileSync("/dev/stdin")
    .toString()
    .split(" ")
    .map((number) => parseInt(number));
  return data;
};

const [N, M] = inputData();
const visited = new Array(N + 1).fill(false);

const findSequence = (answer, visited) => {
  if (answer.length === M) {
    console.log(answer.join(" "));
    return;
  } else {
    for (let i = 1; i <= N; i++) {
      if (visited[i]) continue;
      answer.push(i);
      visited[i] = true;
      findSequence(answer, visited);
      answer.pop();
      visited[i] = false;
    }
  }
};

const main = () => {
  findSequence([], visited);
};

main();
