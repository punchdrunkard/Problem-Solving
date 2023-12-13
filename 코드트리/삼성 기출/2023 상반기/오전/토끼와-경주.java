// 문제 링크
// https://www.codetree.ai/training-field/frequent-problems/problems/rabit-and-race/
// 수행 시간 : 2294 ms, 메모리 : 99MB

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Main {
    static long totalSum;
    static int n, m, p;


    public static class Point {
        int x, y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public static Comparator<Point> pointComparator = (p1, p2) -> {
            // (행 번호 + 열 번호가 큰 칸, 행 번호가 큰 칸, 열 번호가 큰 칸) 순으로 우선순위
            if (p1.x + p1.y != p2.x + p2.y) {
                return Integer.compare(p2.x + p2.y, p1.x + p1.y);
            } else if (p1.x != p2.x) {
                return Integer.compare(p2.x, p1.x);
            } else {
                return Integer.compare(p2.y, p1.y);
            }
        };
    }

    public static class Rabbit {
        int id, x, y, jumpCount;

        Rabbit(int id, int x, int y, int jumpCount){
            this.id = id;
            this.x = x;
            this.y = y;
            this.jumpCount = jumpCount;
        }

        public static Comparator<Rabbit> rabbitComparator = (r1, r2) -> {
            if (r1.jumpCount != r2.jumpCount) {
                return Integer.compare(r1.jumpCount, r2.jumpCount);
            } else if ((r1.x + r1.y) != (r2.x + r2.y)) {
                return Integer.compare(r1.x + r1.y, r2.x + r2.y);
            } else if (r1.x != r2.x) {
                return Integer.compare(r1.x, r2.x);
            } else if (r1.y != r2.y) {
                return Integer.compare(r1.y, r2.y);
            } else {
                return Integer.compare(r1.id, r2.id);
            }
        };

        public static Comparator<Rabbit> scoreComparator = (r1, r2) -> {
            if (r1.x + r1.y != r2.x + r2.y) {
                return Integer.compare(r2.x + r2.y, r1.x + r1.y);
            } else if (r1.x != r2.x) {
                return Integer.compare(r2.x, r1.x);
            } else if (r1.y != r2.y) {
                return Integer.compare(r2.y, r1.y);
            } else {
                return Integer.compare(r2.id, r1.id);
            }
        };
    }

    static long[] score = new long[RABBIT_MAX]; // score[index] := index번 토끼의 점수
    static int[] rabbitDistance = new int[RABBIT_MAX]; // rabbitDistance[index] := index번 토끼의 고유 거리
    static int[] jumpCount = new int[RABBIT_MAX]; // jumpCount[index] := index 토끼의 점프 카운트
    static Point[] location = new Point[RABBIT_MAX]; // location[index] := index 번 토끼의 위치 좌표

    static int[] id = new int[RABBIT_MAX]; // 토끼의 아이디들 기록

    static HashMap<Integer, Integer> idToIndex = new HashMap<>(); // 토끼의 id를 index로 변환한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(br.readLine()); // 명령의 수

        for (int test_case = 1; test_case <= q; test_case++) {
            int[] command = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (command[0] == 100){
                initRace(command);
            } else if (command[0] == 200){
                startRace(command[1], command[2]);
            } else if (command[0] == 300){
                changeDistance(command[1], command[2]);
            } else {
                System.out.println(pickBestRabbit());
            }
        }
    }

    // 가장 높은 점수를 출력 한다.
    public static long pickBestRabbit(){
        long bestScore = Long.MIN_VALUE;

        for (int i = 0; i < p; i++){
            bestScore = Math.max(bestScore, score[i]);
        }

        return bestScore + totalSum;
    }

    // 고유 번호가 pid인 토끼의 이동거리를 l배 해준다.
    public static void changeDistance(int pid, int l){
        int index = idToIndex.get(pid);
        rabbitDistance[index] *= l;
    }

    public static void startRace(int k, int s){
        boolean[] picked = new boolean[RABBIT_MAX]; // picked[id] := id번 토끼까 뽑힌적 있나?

        // 현재 존재하는 토끼를 우선 순위 큐에 집어 넣는다.
        PriorityQueue<Rabbit> pq = new PriorityQueue<>(Rabbit.rabbitComparator);

        for (int i = 0; i < p; i++){
            int pid = id[i];
            int x = location[i].x;
            int y = location[i].y;
            int j = jumpCount[i];

            pq.add(new Rabbit(pid, x, y, j));
        }

        for (int turn = 0; turn < k; turn++){
            // 1. 기징 우선순위가 높은 토끼를 뽑는다.
            int pid = pq.poll().id;

            int index = idToIndex.get(pid);
            picked[index] = true;

            // 2. 그 토끼를 이동시킨다.
            moveRabbit(pid);

            // 3. 이동 시킨 토끼를 다시 우선순위 큐에 넣는다.
            pq.offer(new Rabbit(pid, location[index].x, location[index].y, jumpCount[index]));
        }

        // 3. k번의 턴이 모두 진행되면 가장 우선순위가 높은 토끼를 골라 s를 더한다.
        int sid = pickScoreRabbit(picked);
        int index = idToIndex.get(sid);
        score[index] += s;
    }

    // 토끼를 이동시킴
    public static void moveRabbit(int pid){
        Point next = findNextLocation(pid);

        int index = idToIndex.get(pid);
        location[index] = next;
        jumpCount[index]++;

        // 이동 후, 해당 토끼를 제외한 나머지 토끼들은 점수를 얻는다.
        int s = next.x + next.y + 2; // 문제의 조건은 1-indexed 이므로
        score[index] -= s;
        totalSum += s;
    }

    // 다음으로 이동할 위치를 구한다.
    public static Point findNextLocation(int pid){
        int index = idToIndex.get(pid);
        Point current = location[index];
        int dist = rabbitDistance[index];

        PriorityQueue<Point> pq = new PriorityQueue<>(Point.pointComparator);
        pq.offer(moveUp(current, dist));
        pq.offer(moveDown(current, dist));
        pq.offer(moveLeft(current, dist));
        pq.offer(moveRight(current, dist));

        return pq.poll();
    }

    public static Point calculateVerticalMove(Point current, int dist, boolean isUpward) {
        int mod = 2 * (n - 1);
        int actualMove = dist % mod; // 실제 이동 거리
        actualMove = (actualMove < 0) ? actualMove + mod : actualMove; // 음수 처리

        int nx = current.x;
        int remain = isUpward ? current.x : (n - 1) - current.x; // 위로 또는 아래로 이동

        // 위 또는 아래로 이동
        if (isUpward) {
            nx = Math.max(nx - actualMove, 0);
        } else {
            nx = Math.min(nx + actualMove, n - 1);
        }

        return new Point(nx, current.y);
    }

    public static Point calculateHorizontalMove(Point current, int dist, boolean isRightward) {
        int mod = 2 * (m - 1);
        int actualMove = dist % mod; // 실제 이동 거리
        actualMove = (actualMove < 0) ? actualMove + mod : actualMove; // 음수 처리

        int ny = current.y;
        int remain = isRightward ? (m - 1) - current.y : current.y; // 오른쪽 또는 왼쪽으로 이동

        // 오른쪽 또는 왼쪽으로 이동
        if (isRightward) {
            ny = Math.min(ny + actualMove, m - 1);
        } else {
            ny = Math.max(ny - actualMove, 0);
        }

        return new Point(current.x, ny);
    }

    public static Point moveUp(Point current, int dist) {
        return calculateVerticalMove(current, dist, true);
    }

    public static Point moveDown(Point current, int dist) {
        return calculateVerticalMove(current, dist, false);
    }

    public static Point moveLeft(Point current, int dist) {
        return calculateHorizontalMove(current, dist, false);
    }

    public static Point moveRight(Point current, int dist) {
        return calculateHorizontalMove(current, dist, true);
    }

    public static int pickScoreRabbit(boolean[] picked){
        PriorityQueue<Rabbit> pq = new PriorityQueue<>(Rabbit.scoreComparator);

        for (int i = 0; i < p; i++){
            if (!picked[i]) continue;

            int pid = id[i];
            int x = location[i].x;
            int y = location[i].y;

            pq.add(new Rabbit(pid, x, y, -1));
        }

        return pq.poll().id;
    }

    public static void initRace(int[] command){
        n = command[1];
        m = command[2];
        p = command[3];

        for (int i = 0; i < 2 * p; i += 2){
            int pid = command[i + 4];
            int dist = command[i + 5];

            id[i / 2] = pid;
            idToIndex.put(pid, i / 2);
            rabbitDistance[i / 2] = dist;
            location[i / 2] = new Point(0, 0);
        }
    }
}

// 주의 사항
// - 우선 순위 큐는 만드는 거 자체가 비싼 연산이므로, 재활용 할 수 있으면 재활용 한다. (메모리 조심!)
// - 모듈러 연산하면, 나올 수 있는 값이 한정된다는 특징을 사용할 수 있다.
// - 하나를 제외한 전체에게 무언가를 더한다는 것은, 하나에 무언가를 빼는 것을 고려할 수 있다.