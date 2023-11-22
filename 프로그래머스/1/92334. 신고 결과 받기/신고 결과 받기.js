function solution(id_list, report, k) {
  let answer = Array.from({ length: id_list.length }, () => 0);

  // 각 유저 이름에 해당하는 아이디를 저장
  let id_map = new Map();
  id_list.forEach((id, index) => id_map.set(id, index));

  //  key: 유저 ID, value: [신고당한 횟수, 신고한 사람들 Set]
  let reportMap = new Map();
  id_list.forEach((id) => reportMap.set(id, [0, new Set()]));

  report.forEach((r) => {
    let [reporter, reported] = r.split(" ");
    //  동일한 유저에 대한 신고 횟수는 1회로 처리되므로
    if (!reportMap.get(reported)[1].has(reporter)) {
      reportMap.get(reported)[1].add(reporter);
      reportMap.get(reported)[0]++;
    }
  });

  reportMap.forEach(([count, reporters]) => {
    if (count >= k) {
      reporters.forEach((reporter) => {
        answer[id_map.get(reporter)]++;
      });
    }
  });

  return answer;
}
