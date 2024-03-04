import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n;
    static int[][] members;

    // 우선 순위 -> id 작은 순서
    static Queue<Computer> availableQ = new PriorityQueue<>(Comparator.comparingInt(a -> a.id));

    // 우선 순위 -> 끝나는 시간이 작은 순서
    static Queue<Computer> waitQ = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));

    // TODO 시간을 기점으로 waitQ에 있는 걸 availableQ로 업데이트해야 한다. -> 이분탐색 사용하기

    static class Computer {
        int id, end; // 시작, 끝 시간

        Computer(int id, int end) {
            this.id = id;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        input();
        solve();
    }

    static void solve() {
        List<Integer> counter = new ArrayList<>(); // counter[i] := i번째 컴퓨터 이용한 사람 수
        int computerId = 0;

        // 시작 시간 기준 정렬
        Arrays.sort(members, Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < n; i++) {
            int start = members[i][0];
            int end = members[i][1];

            updateAvailableQueue(start);

            if (availableQ.isEmpty()) {
                // 컴퓨터를 하나 더 배정해야 한다.
                Computer newComputer = new Computer(computerId, end);
                waitQ.offer(newComputer);
                counter.add(1);
                computerId++;
            } else {
                // 기존에 있던 컴퓨터 사용 가능
                Computer existedComputer = availableQ.poll();
                existedComputer.end = end;
                waitQ.offer(existedComputer);
                counter.set(existedComputer.id, counter.get(existedComputer.id) + 1);
            }
        }

        printAnswer(counter);
    }

    static void updateAvailableQueue(int time) {
        while (!waitQ.isEmpty() && waitQ.peek().end <= time) {
            availableQ.offer(waitQ.poll());
        }
    }

    static void printAnswer(List<Integer> counter) {
        StringBuilder answer = new StringBuilder();
        answer.append(counter.size()).append('\n');

        for (int count : counter) {
            answer.append(count).append(' ');
        }

        System.out.println(answer);
    }

    static void input() {
        n = scan.nextInt();
        members = new int[n][2];

        for (int i = 0; i < n; i++) {
            members[i][0] = scan.nextInt();
            members[i][1] = scan.nextInt();
        }
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
