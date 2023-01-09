// ref : https://degurii.tistory.com/108 (백준에서 Node.js 입력을 Python처럼 쉽게 받는 법)
// ref : https://lamarr.dev/en/javascript/node.js/2020/04/06/01.html (https://lamarr.dev/en/javascript/node.js/2020/04/06/01.html)
// Mac, Linux의 경우 control + D를 통해 결과를 확인 할 수 있습니다.

const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();
