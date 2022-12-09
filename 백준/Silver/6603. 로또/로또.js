const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const answerForPrint = [];

const dfs = (answer, k, numbers, visited, currentIndex) => {
  if (answer.length === 6) {
    answerForPrint.push(answer.join(" "));
    return;
  }

  for (let i = currentIndex; i < k; i++) {
    if (!visited[numbers[i]]) {
      visited[numbers[i]] = true;
      answer.push(numbers[i]);
      dfs(answer, k, numbers, visited, i);
      visited[numbers[i]] = false;
      answer.pop();
    }
  }
};

for (let i = 0; i < stdin.length - 1; i++) {
  const testCase = stdin[i].split(" ").map((num) => parseInt(num));
  const k = testCase[0];
  const numbers = testCase.slice(1);
  const visited = new Array(50).fill(false);
  dfs([], k, numbers, visited, 0);
  answerForPrint.push("");
}

console.log(answerForPrint.join("\n"));
