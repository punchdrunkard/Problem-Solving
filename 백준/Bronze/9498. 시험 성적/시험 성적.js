const inputData = () => {
  const input = require("fs");
  const score = parseInt(
    input.readFileSync("/dev/stdin")
  );
  return score;
};

const main = () => {
  const score = inputData();
  const grade =
    score >= 90
      ? "A"
      : score >= 80
      ? "B"
      : score >= 70
      ? "C"
      : score >= 60
      ? "D"
      : "F";
  console.log(grade);
};

main();
