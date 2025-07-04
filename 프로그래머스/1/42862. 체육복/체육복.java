class Solution {

    // init 메서드는 기존 코드와 동일합니다.
    int n;
    boolean[] lost, reserve;

    public int solution(int n, int[] lost, int[] reserve) {
        init(n, lost, reserve);
        return solve();
    }

    // 수정된 solve 메서드
    int solve() {
        // 1. 체육수업을 들을 수 있는 학생 수의 초기값 설정
        // 전체 학생 수에서, 도난 당했지만 여벌이 없는 학생 수를 뺀다.
        int answer = n;
        for (int i = 0; i < n; i++) {
            if (lost[i]) {
                answer--;
            }
        }

        // 2. 체육복 빌려주기
        for (int i = 0; i < n; i++) {
            if (lost[i]) { // 체육복을 잃어버린 학생이라면
                // 앞사람에게 먼저 빌린다.
                if (i > 0 && reserve[i - 1]) {
                    answer++; // 수업 참여 가능 학생 +1
                    reserve[i - 1] = false; // 앞사람은 이제 여벌 없음
                    lost[i] = false; // 이 학생은 이제 문제 해결
                }
                // 앞사람에게 못 빌렸을 경우, 뒷사람에게 빌린다.
                else if (i < n - 1 && reserve[i + 1]) {
                    answer++; // 수업 참여 가능 학생 +1
                    reserve[i + 1] = false; // 뒷사람은 이제 여벌 없음
                    lost[i] = false; // 이 학생은 이제 문제 해결
                }
            }
        }
        return answer;
    }

    void init(int n, int[] lostArr, int[] reserveArr) {
        this.n = n;
        this.lost = new boolean[n];
        this.reserve = new boolean[n];

        for (int l : lostArr) {
            this.lost[l - 1] = true;
        }
        for (int r : reserveArr) {
            this.reserve[r - 1] = true;
        }

        // 여벌이 있지만 도난당한 학생 처리
        for (int i = 0; i < n; i++) {
            if (this.lost[i] && this.reserve[i]) {
                this.lost[i] = false;
                this.reserve[i] = false;
            }
        }
    }
}