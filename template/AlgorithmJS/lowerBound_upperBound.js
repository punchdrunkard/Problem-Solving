// array 에서 target 보다 크거나 같은 값이 첫 번째로 나타나는 위치
const lowerBound = (target, array, size) => {
  let [st, en] = [0, size];

  while (st < en) {
    let mid = parseInt((st + en) / 2);

    if (array[mid] >= target) {
      en = mid;
    } else {
      st = mid + 1;
    }
  }

  return st;
};

// array 에서 target 보다 큰 값이 첫 번째로 나타나는 위치
const upperBound = (target, array, size) => {
  let [st, en] = [0, size];

  while (st < en) {
    let mid = parseInt((st + en) / 2);

    if (array[mid] > target) {
      en = mid;
    } else {
      st = mid + 1;
    }
  }

  return st;
};
