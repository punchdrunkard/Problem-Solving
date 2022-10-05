const inputData = () => {
  let fs = require("fs");
  let input = fs.readFileSync("/dev/stdin").toString().split("\n");

  const [queueSize, numSize] = input[0].split(" ").map((num) => parseInt(num));
  const targetIndexes = input[1].split(" ").map((num) => parseInt(num));

  return [queueSize, numSize, targetIndexes];
};

const compute = (queueSize, targetIndexes) => {
  let operationCount = 0;
  const queue = Array.from({ length: queueSize }, (_, i) => i + 1);

  for (const target of targetIndexes) {
    if (queue[0] === target) {
      queue.shift();
    } else {
      // 앞으로 가야하는 거리
      const startDistance = queue.indexOf(target);
      // 뒤로 가야하는 거리
      const endDistance = queue.length - startDistance;

      if (startDistance < endDistance) {
        // 2번 연산
        const queueFragment = queue.splice(0, startDistance);
        queue.push(...queueFragment);
        queue.shift();
        operationCount += startDistance;
      } else {
        // 3번 연산
        const queueFragment = queue.splice(-endDistance, endDistance);
        queue.unshift(...queueFragment);
        queue.shift();
        operationCount += endDistance;
      }
    }
  } // end of for

  return operationCount;
};

const main = () => {
  const [queueSize, numSize, targetIndexes] = inputData();
  const answer = compute(queueSize, targetIndexes);
  console.log(answer);
};

main();
