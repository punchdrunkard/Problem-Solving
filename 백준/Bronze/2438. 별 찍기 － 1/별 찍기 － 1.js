const inputData = () => {
  const input = require("fs");
  const N = parseInt(
    input.readFileSync("/dev/stdin")
  );

  return N;
};

const main = () => {
  const N = inputData();
  for (let i = 1; i <= N; i++) {
    console.log("*".repeat(i));
  }
};

main();