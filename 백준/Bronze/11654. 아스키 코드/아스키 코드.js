const input = require("fs");
const data = input
  .readFileSync("/dev/stdin")
  .toString();

console.log(data.charCodeAt(0));
