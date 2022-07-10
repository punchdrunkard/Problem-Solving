const inputData = () => {
  const input = require("fs");
  const sequence = input
    .readFileSync("/dev/stdin")
    .toString()
    .trim()
    .replaceAll(" ", "");

  return sequence;
};

const findScale = (sequence) => {
  const ASCENDING = "12345678";
  const DESCENDING = "87654321";

  return sequence === ASCENDING
    ? "ascending"
    : sequence === DESCENDING
    ? "descending"
    : "mixed";
};

const main = () => {
  const sequence = inputData();
  console.log(findScale(sequence));
};

main();
