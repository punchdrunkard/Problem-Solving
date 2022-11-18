// ref : https://degurii.tistory.com/108 (백준에서 Node.js 입력을 Python처럼 쉽게 받는 법)

const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();
