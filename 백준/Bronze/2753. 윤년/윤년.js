const inputData = () => {
  const input = require("fs");
  const year = parseInt(
    input.readFileSync("/dev/stdin")
  );

  return year;
};

const isLeapYear = (year) => {
  return (year % 4 === 0 && year % 100 !== 0) || year % 400 === 0 ? 1 : 0;
};

const main = () => {
  const year = inputData();
  console.log(isLeapYear(year));
};

main();
