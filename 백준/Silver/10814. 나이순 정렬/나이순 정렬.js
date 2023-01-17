const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const compare = (a, b) => {
  const [age_a, age_b] = [parseInt(a[0]), parseInt(b[0])];

  if (age_a === age_b) {
    return a[2] - b[2];
  }

  return age_a - age_b;
};

const data = stdin
  .slice(1)
  .map((row) => row.split(" "))
  .map((line, i) => {
    const [age, name] = line;
    return [parseInt(age), name, i];
  })
  .sort(compare);

console.log(data.map((row) => row.splice(0, 2).join(" ")).join("\n"));
