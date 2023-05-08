/**
 * @param {integer} init
 * @return { increment: Function, decrement: Function, reset: Function }
 */
function createCounter(init) {
  this.counter = init;

  this.increment = () => {
    return ++this.counter;
  };

  this.reset = () => {
    return (this.counter = init);
  };

  this.decrement = () => {
    return --this.counter;
  };

  return {
    increment,
    decrement,
    reset,
  };
}

/**
 * const counter = createCounter(5)
 * counter.increment(); // 6
 * counter.reset(); // 5
 * counter.decrement(); // 4
 */
