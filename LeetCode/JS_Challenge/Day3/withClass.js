/**
 * @param {integer} init
 * @return { increment: Function, decrement: Function, reset: Function }
 */

class Counter {
  constructor(init) {
    this.init = init;
    this.counter = init;
  }

  increment() {
    return ++this.counter;
  }

  decrement() {
    return --this.counter;
  }

  reset() {
    this.counter = this.init;
    return this.counter;
  }
}

let createCounter = function (init) {
  return new Counter(init);
};

/**
 * const counter = createCounter(5)
 * counter.increment(); // 6
 * counter.reset(); // 5
 * counter.decrement(); // 4
 */
