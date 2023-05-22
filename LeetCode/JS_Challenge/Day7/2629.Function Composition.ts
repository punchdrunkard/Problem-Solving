// https://leetcode.com/problems/function-composition/?utm_campaign=PostD7&utm_medium=Post&utm_source=Post&gio_link_id=4PY7wZM9

type F = (x: number) => number;

function compose(functions: F[]): F {
  return function (x): number {
    return functions.reduceRight((acc, fn) => fn(acc), x);
  };
}
