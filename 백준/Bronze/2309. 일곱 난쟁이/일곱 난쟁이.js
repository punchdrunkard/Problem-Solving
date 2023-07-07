const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const dwarfs = stdin.map((dwarf) => parseInt(dwarf));

answer = [];
idxs = [];

const findIdx = () => {
  let sum;

  for (let i = 0; i < 9; i++) {
    for (let j = 0; j < 9; j++) {
      // 난쟁이 인덱스 두 개 선택
      if (i === j) continue;

      sum = 0;

      for (let k = 0; k < 9; k++) {
        if (k !== i && k !== j) {
          sum += dwarfs[k];
        }
      }

      if (sum === 100) {
        idxs.push(i);
        idxs.push(j);
        return;
      }
    }
  }
};

findIdx();

dwarfs.map((dwarf, idx) => {
  if (!idxs.includes(idx)) {
    answer.push(dwarf);
  }
});

console.log(answer.sort((a, b) => a - b).join('\n'));
