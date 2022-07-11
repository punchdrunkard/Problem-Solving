const inputData = () => {
  const input = require("fs");
  const [A, B] = input
    .readFileSync("/dev/stdin")
    .toString()
    .split(" ")
    .map((number) => parseInt(number));

  return [A, B];
};

const main = () => {
  const [A, B] = inputData();
  console.log(A * B);
};

main();
