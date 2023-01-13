const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const answer = [];

const [n, m] = stdin[0].split(" ").map((num) => parseInt(num));
const names = stdin.slice(1, n + 1);
const queries = stdin.slice(n + 1);

const stringToIndex = new Map();
// unordered_map 을 활용할 수 있겠지만, 당연히 배열에서 인덱스로 가져오는 것이 빠르다.
// 따라서, 배열의 인덱스로 둬도 아무 문제가 없는 상황이면 배열을 사용한다.
const IndexToString = Array.from({ length: n + 1 });

names.forEach((name, index) => {
  IndexToString[index + 1] = name;
  stringToIndex.set(name, index + 1);
});

queries.forEach((query) => {
  Number.isInteger(parseInt(query[0]))
    ? answer.push(IndexToString[parseInt(query)])
    : answer.push(stringToIndex.get(query));
});

console.log(answer.join("\n"));
