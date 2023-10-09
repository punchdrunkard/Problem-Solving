const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n, m] = stdin[0].split(' ').map(Number);
const board = stdin.slice(1);

const canMakeCBoard = (i, j) => {
  return i + 8 - 1 < n && j + 8 - 1 < m;
};

const ROW1 = 'BWBWBWBW';
const ROW2 = 'WBWBWBWB';

const CHESS_TYPE_1 = [ROW1, ROW2, ROW1, ROW2, ROW1, ROW2, ROW1, ROW2];
const CHESS_TYPE_2 = [ROW2, ROW1, ROW2, ROW1, ROW2, ROW1, ROW2, ROW1];

// (x, y) 부터 8 * 8 크기의 체스판을 만들 때,
// 다시 칠해야 하는 정사각형의 갯수
const countAnswer = (x, y) => {
  let [count1, count2] = [0, 0];

  for (let i = x; i < x + 8; i++) {
    for (let j = y; j < y + 8; j++) {
      if (board[i][j] !== CHESS_TYPE_1[i - x][j - y]) count1++;
      if (board[i][j] !== CHESS_TYPE_2[i - x][j - y]) count2++;
    }
  }

  return Math.min(count1, count2);
};

const solve = () => {
  let answer = Infinity;

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      // 현재 점에서 가로, 세로로 체스판을 만들 수 있는가?
      if (!canMakeCBoard(i, j)) continue;
      answer = Math.min(answer, countAnswer(i, j));
    }
  }

  return answer;
};

console.log(solve());
