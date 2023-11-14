let fees, records;

const getMinute = (time) => {
  const [hours, minutes] = time.split(":").map((t) => parseInt(t));
  return hours * 60 + minutes;
};

const calculateFee = (time) => {
  const [basicTime, basicFee, unitTime, unitFee] = fees;
  if (time <= basicTime) {
    return basicFee;
  } else {
    return basicFee + Math.ceil((time - basicTime) / unitTime) * unitFee;
  }
};

const processCarInfo = (records) => {
  const carInfo = new Map();
  records.forEach((record) => {
    const [time, carNumber] = record.split(" ");
    if (!carInfo.has(carNumber)) {
      carInfo.set(carNumber, []);
    }
    carInfo.get(carNumber).push(time);
  });

  carInfo.forEach((times, carNumber) => {
    if (times.length % 2 !== 0) {
      times.push("23:59");
    }
  });

  return carInfo;
};

const calculateTotalTime = (times) => {
  let totalTime = 0;
  for (let i = 0; i < times.length; i += 2) {
    totalTime += Math.abs(getMinute(times[i]) - getMinute(times[i + 1]));
  }
  return totalTime;
};

const calculateFinalFees = (carInfo) => {
  const results = [];
  carInfo.forEach((times, carNumber) => {
    const totalTime = calculateTotalTime(times);
    results.push([carNumber, calculateFee(totalTime)]);
  });
  return results.sort((a, b) => a[0] - b[0]).map((result) => result[1]);
};

function solution(_fees, _records) {
  fees = _fees;
  records = _records;

  const carInfo = processCarInfo(records);
  return calculateFinalFees(carInfo);
}
