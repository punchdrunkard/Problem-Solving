const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [k, l] = stdin[0].split(" ").map((num) => parseInt(num));
const map = new Map();

stdin.slice(1).forEach((studentNum, idx) => {
  if (map.has(studentNum)) {
    map.delete(studentNum);
  }

  map.set(studentNum, idx);
});

const studentList = [...map].sort((a, b) => a[1] - b[1]);
const studentCount = Math.min(k, studentList.length);

console.log(
  studentList
    .slice(0, studentCount)
    .map((student) => student[0])
    .join("\n")
);
