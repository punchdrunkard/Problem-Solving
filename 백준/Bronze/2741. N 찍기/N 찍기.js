const inputData = () => {
  const input = require("fs");
  const N = parseInt(
    input.readFileSync("/dev/stdin")
  );

  return N;
};

const main = () => {
  const N = inputData();
  const answer = [];
  for (let i = 1; i <= N; i++) {
    answer.push(i);
  }
  console.log(answer.join("\n"));
};

main();
