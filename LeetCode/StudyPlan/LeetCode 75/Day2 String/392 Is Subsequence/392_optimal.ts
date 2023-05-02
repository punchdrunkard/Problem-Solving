// Runtime 55ms, Beats 92.74 %
// Memory 43.3 MB, Beats 41.49 %

export const isSubsequence = (s: string, t: string): boolean => {
  if (s.length > t.length) return false; // s의 길이가 t보다 길 경우, subsequence가 될 수 없다.

  let [sIndex, tIndex] = [0, 0];

  while (tIndex < t.length) {
    if (s[sIndex] === t[tIndex]) {
      sIndex++;
    }
    tIndex++;

    if (sIndex >= s.length) break; // 더 이상 볼 필요 없는 경우
  }

  return sIndex === s.length;
};
