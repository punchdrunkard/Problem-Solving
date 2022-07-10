const inputData = () => {
  const input = require("fs");
  const N = parseInt(
    input.readFileSync("/dev/stdin")
  );
  return N;
};

const main = () => {
  const N = inputData();
  for (let i = 1; i <= 9; i++) {
    console.log(`${N} * ${i} = ${N * i}`);
  }
};

main();
