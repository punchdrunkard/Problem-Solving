// https://leetcode.com/problems/create-hello-world-function/?utm_campaign=PostD1&utm_medium=Post&utm_source=Post&gio_link_id=QPDw0kJR

/**
 * @return {Function}
 */
let createHelloWorld = function () {
  return function (...args) {
    return "Hello World";
  };
};

const f = createHelloWorld();
f(); // "Hello World"

// 클로저 사용 예시 : https://leetcode.com/problems/create-hello-world-function/solutions/3486638/first-understand-what-is-closure-easily-master-the-concept-with-examples/
