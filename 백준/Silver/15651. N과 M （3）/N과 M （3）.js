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
const answerList = [];

const findSequence = (answer) => {
  if (answer.length === M) {
    answerList.push(answer.join(" "));
    return;
  } else {
    for (let i = 1; i <= N; i++) {
      answer.push(i);
      findSequence(answer);
      answer.pop();
    }
  }
};

const main = () => {
  findSequence([]);
  console.log(answerList.join("\n"));
};

main();
