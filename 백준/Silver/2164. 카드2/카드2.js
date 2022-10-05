const fs = require("fs");
const stdin = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => parseInt(x));


const N = stdin[0];

function Node(value) {
  this.data = value;
  this.next = null;
}

function Queue() {
  this.front = null;
  this.rear = null;
  this.length = 0;

  this.push = (value) => {
    const newNode = new Node(value);

    if (this.length === 0) {
      this.front = newNode;
      this.rear = newNode;
      this.length += 1;
      return;
    }

    this.rear.next = newNode;
    this.rear = newNode;
    this.length += 1;
  };
 
  this.pop = () => {
    const frontNode = this.front.data;
    this.front = this.front.next;
    this.length -= 1;
    return frontNode;
  };

  this.size = () => {
    return this.length;
  };

  this.getFront = () => {
    return this.front.data;
  };
}

const queue = new Queue();

Array.from({ length: N }, (v, i) => i + 1).map((number) => {
  queue.push(number);
});


const operation = () => {
  queue.pop();
  const topCard = queue.pop();
  queue.push(topCard);
};

while (queue.size() > 1) {
  operation();
}

console.log(queue.getFront());
