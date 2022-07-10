const inputData = () => {
  const input = require("fs");
  const numbers = input
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .split("\n")
    .map((number) => parseInt(number));
  return numbers;
};

const main = () => {
  const numbers = inputData();
  const multiplied = [
    ...numbers.reduce((acc, number) => acc * number, 1).toString(),
  ];
  for (let i = 0; i <= 9; i++) {
    console.log(multiplied.filter((num) => num === i.toString()).length);
  }
};

main();