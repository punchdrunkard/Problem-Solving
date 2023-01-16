const main = () => {
  const [n] = stdin[0].split(" ").map((num) => parseInt(num));
  const opers = stdin
    .slice(1)
    .map((row) => row.split(" ").map((num) => parseInt(num)));
  const answer = [];

  const parents = Array.from({ length: n + 1 }, (_, i) => i);

  const find = (a) => {
    if (parents[a] === a) return a;

    const result = find(parents[a]);
    parents[a] = result;
    return result;
  };

  const union = (a, b) => {
    const [parent_a, parent_b] = [find(a), find(b)];
    if (parent_a === parent_b) return;
    parents[parent_a] = parent_b;
  };

  opers.forEach((oper) => {
    const [op, a, b] = oper;

    switch (op) {
      case 0:
        union(a, b);
        break;
      case 1:
        answer.push(find(a) === find(b) ? "YES" : "NO");
        break;
    }
  });

  console.log(answer.join("\n"));
};

const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let stdin = [];

rl.on("line", function (line) {
  stdin.push(line);
}).on("close", function () {
  main();
  process.exit();
});
