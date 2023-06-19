const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [word, index] = [stdin[0], parseInt(stdin[1])];
console.log(word[index - 1]);
