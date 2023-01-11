class Node {
  constructor(value) {
    this.data = value;
    this.next = null;
  }
}

class Queue {
  constructor() {
    this.front = null;
    this.rear = null;

    this.length = 0;

    this.push = (data) => {
      const newNode = new Node(data);

      if (this.length === 0) {
        this.length += 1;
        this.front = newNode;
        this.rear = newNode;
        return;
      }

      this.length += 1;
      this.rear.next = newNode;
      this.rear = newNode;
    };

    this.pop = () => {
      if (this.length === 0) {
        return -1;
      }
      const frontElement = this.front;
      const frontNextElement = this.front.next;
      this.front = frontNextElement;
      this.length -= 1;
      return frontElement.data;
    };

    this.size = () => {
      return this.length;
    };

    this.empty = () => {
      return this.length === 0 ? 1 : 0;
    };

    this.frontOperation = () => {
      if (this.empty()) return -1;
      return this.front.data;
    };

    this.back = () => {
      if (this.empty()) return -1;
      return this.rear.data;
    };
  }
}
