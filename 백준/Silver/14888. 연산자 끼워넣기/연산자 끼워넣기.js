const inputData = () => {
  const input = require("fs");
  const data = input.readFileSync("/dev/stdin").toString().trim().split("\n");
  const N = parseInt(data[0]);
  const numbers = data[1].split(" ").map((number) => parseInt(number));
  const operators = data[2].split(" ").map((number) => parseInt(number));
  return [N, numbers, operators];
};

const [N, numbers, operators] = inputData();
const results = [];

const operate = (level, result, currentOperator) => {
  if (level === N) {
    results.push(result);
    return;
  }

  for (let i = 0; i < 4; i++) {
    if (currentOperator[i] > 0) {
      const newOperator = [...currentOperator];
      newOperator[i] -= 1;
      if (i === 0) {
        operate(level + 1, result + numbers[level], newOperator);
      }
      if (i === 1) {
        operate(level + 1, result - numbers[level], newOperator);
      }
      if (i === 2) {
        operate(level + 1, result * numbers[level], newOperator);
      }
      if (i === 3) {
        operate(
          level + 1,
          result < 0
            ? -parseInt(Math.abs(result) / numbers[level])
            : parseInt(result / numbers[level]),
          newOperator
        );
      }
    }
  }
};

operate(1, numbers[0], operators);
const max = Math.max(...results);
const min = Math.min(...results);
console.log(max ? max : 0);
console.log(min ? min : 0);
