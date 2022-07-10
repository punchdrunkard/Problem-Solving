const inputData = () => {
  const input = require("fs");
  const data = input
    .readFileSync("/dev/stdin")
    .toString()
    .split("\n");

  const N = parseInt(data[0]);
  const tests = [];
  for (let i = 1; i <= N; i++) {
    tests.push(data[i].split(" ").map((number) => parseInt(number)));
  }
  return [N, tests];
};

const main = () => {
  const [N, tests] = inputData();
  tests.map((test) => {
    console.log(test[0] + test[1]);
  });
};

main();