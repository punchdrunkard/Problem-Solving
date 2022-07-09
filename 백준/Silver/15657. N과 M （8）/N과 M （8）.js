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
const answerList = [];

const findSequence = (answer, k) => {
  if (answer.length === M) {
    answerList.push(answer.join(" "));
    return;
  } else {
    for (let i = 0; i < N; i++) {
      if (i < k) continue;
      answer.push(numbers[i]);
      findSequence(answer, i);
      answer.pop();
    }
  }
};

const main = () => {
  findSequence([], 0);
  console.log(answerList.join("\n"));
};

main();
