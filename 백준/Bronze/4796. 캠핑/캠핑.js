const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const testCases = stdin.slice(0, stdin.length - 1);

const solution = (testCase) => {
  const [L, P, V] = testCase.split(" ").map((num) => parseInt(num));

  let answer = 0;
  answer += parseInt(V / P) * L;

  const mod = V % P;
  if (mod > L) answer += L;
  else answer += mod;

  return answer;
};

const answers = [];

testCases.forEach((testCase, idx) => {
  answers.push(`Case ${idx + 1}: ${solution(testCase)}`);
});

console.log(answers.join("\n"));
