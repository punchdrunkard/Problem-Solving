const inputData = () => {
  const input = require("fs");
  const data = input
    .readFileSync("/dev/stdin")
    .toString()
    .split("\n");
  const [N, M] = data[0].split(" ").map((num) => parseInt(num));
  const numbers = data[1]
    .split(" ")
    .map((num) => parseInt(num))
    .sort((a, b) => {
      if (a > b) return 1;
      if (a < b) return -1;
      return 0;
    });
  return [N, M, numbers];
};

const [N, M, numbers] = inputData();
const visited = new Array(N).fill(false);
const answerList = new Set();

const findSequence = (answer) => {
  if (answer.length === M) {
    answerList.add(answer.join(" "));
    return;
  } else {
    for (let i = 0; i < N; i++) {
      if (visited[i]) continue;
      answer.push(numbers[i]);
      visited[i] = true;
      findSequence(answer);
      answer.pop();
      visited[i] = false;
    }
  }
};

const main = () => {
  findSequence([]);
  console.log([...answerList].join("\n"));
};

main();
