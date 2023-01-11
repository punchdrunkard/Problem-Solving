const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

stdin
  .splice(0, stdin.length - 1)
  .map((row) =>
    row
      .split(" ")
      .map((num) => parseInt(num))
      .sort((a, b) => a - b)
  )
  .forEach((nums) => {
    console.log(
      nums[0] ** 2 + nums[1] ** 2 === nums[2] ** 2 ? "right" : "wrong"
    );
  });
