const inputData = () => {
  const input = require("fs");
  const data = input
    .readFileSync("/dev/stdin")
    .toString()
    .split("\n");
  const N = parseInt(data[0]);
  const scores = data[1].split(" ").map((num) => parseInt(num));
  return [N, scores];
};

const computeNewAverage = (N, scores) => {
  const M = Math.max(...scores);
  return (
    scores.map((score) => (score / M) * 100).reduce((a, b) => a + b, 0) / N
  );
};

const main = () => {
  const [N, scores] = inputData();
  console.log(computeNewAverage(N, scores));
};

main();
