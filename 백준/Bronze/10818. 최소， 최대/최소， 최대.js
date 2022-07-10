const inputData = () => {
  const input = require("fs");
  const data = input
    .readFileSync("/dev/stdin")
    .toString()
    .split("\n");

  N = parseInt(data[0]);
  numbers = data[1].split(" ").map((number) => parseInt(number));
  return [N, numbers];
};

const main = () => {
  const [N, numbers] = inputData();
  console.log(Math.min(...numbers), Math.max(...numbers));
};

main();
