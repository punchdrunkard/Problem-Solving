const input = require("fs");
const data = input
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const numbers = [...data[1].toString()];
const answer = numbers.reduce((acc, number) => acc + parseInt(number), 0);
console.log(answer);
