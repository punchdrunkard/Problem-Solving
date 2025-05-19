import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int t, n;
	static PriorityQueue<Process> pq;
	static List<Integer> answer = new ArrayList<>();

	public static void main(String[] args) {
		init();
		solve();
		printAnswer();
	}
	
	static void solve() {
		for (int i = 0; i < t; i++) {
			// 프로세스를 선택
			Process currentProcess = pq.poll();
			// 나머지 프로세스들의 우선 순위 상승 -> 현재 프로세스의 우선순위 낮추기
			currentProcess.priority--;
			// 현재 프로세스 실행 시간 감소
			currentProcess.requiredTime--;
			answer.add(currentProcess.id);

			// 실행이 완료됨
			if (currentProcess.requiredTime != 0) {
				pq.offer(currentProcess);
			}

			if (pq.isEmpty()) {
				break;
			}

		}
	}

	static void printAnswer() {
		StringBuilder sb = new StringBuilder();
		for (int a : answer) {
			sb.append(a).append('\n');
		}
		System.out.println(sb);
	}

	static void init() {
		t = scan.nextInt();
		n = scan.nextInt();
		pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			Process p = new Process(scan.nextInt(), scan.nextInt(), scan.nextInt());
			pq.offer(p);
		}
	}

	static class Process implements Comparable<Process> {
		int id, requiredTime, priority;

		Process(int id, int requiredTime, int priority) {
			this.id = id;
			this.requiredTime = requiredTime;
			this.priority = priority;
		}

		@Override
		public int compareTo(Process o) {
			// 우선 순위 값이 제일 큰 프로세스가 여러 개라면, id가 가장 작은 프로세스
			if (priority == o.priority) {
				return Integer.compare(id, o.id);
			}

			// 우선 순위 값이 제일 큰 프로세스
			return Integer.compare(o.priority, priority);
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) {
			try {
				br = new BufferedReader(new FileReader(new File(s)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
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
