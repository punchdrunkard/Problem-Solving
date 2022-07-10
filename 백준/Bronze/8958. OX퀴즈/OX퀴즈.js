const inputData = () => {
  const input = require("fs");
  const data = input
    .readFileSync("/dev/stdin")
    .toString()
    .split("\n");

  const N = parseInt(data[0]);
  const tests = [];

  for (let i = 1; i <= N; i++) {
    tests.push(data[i].trim());
  }
  return [N, tests];
};

const main = () => {
  const [N, tests] = inputData();
  tests.map((test) => {
    let score = 0;
    let combo = 1;
    const testResults = [...test];
    for (let i = 0; i < testResults.length; i++) {
      if (testResults[i] === "O") {
        if (i !== 0 && testResults[i - 1] === "O") {
          combo += 1;
        }
        score += combo;
      } else {
        combo = 1;
      }
    }
    console.log(score);
  });
};

main();