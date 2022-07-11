const inputData = () => {
  const input = require("fs");
  const word = input.readFileSync("/dev/stdin").toString().trim();
  return word;
};

const main = () => {
  const word = inputData();
  const answer = [];
  for (let i = "a".charCodeAt(0); i <= "z".charCodeAt(0); i++) {
    answer.push(word.indexOf(String.fromCharCode(i)));
  }
  console.log(answer.join(" "));
};

main();
