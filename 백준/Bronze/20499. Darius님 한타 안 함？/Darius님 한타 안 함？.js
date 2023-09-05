const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const [K, D, A] = stdin[0].split('/').map(Number);
const answer = K + A < D || D === 0 ? 'hasu' : 'gosu';
console.log(answer);
