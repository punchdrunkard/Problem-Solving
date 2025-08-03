import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	// static FastReader scan = new FastReader("src/input/2.inp");
	static FastReader scan = new FastReader();

	static String s, e, q;
	static int sTime, eTime, qTime; // 분단위로 변경

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		Set<String> s1 = new HashSet<>();
		Set<String> s2 = new HashSet<>();

		while (scan.hasNext()) {
			int time = convertToMin(scan.next());
			String name = scan.next();

			// 현재 시간이 개강총회가 시작하기 전이라면
			if (time <= sTime) {
				s1.add(name);
			} else if (eTime <= time && time <= qTime) { // 현재 시간이 개강총회를 끝내고 나서, 스트리밍이 끝날때까지라면
				s2.add(name);
			}
		}

		s1.retainAll(s2);
		return s1.size();
	}

	static int convertToMin(String time) {
		int[] timeArr = Arrays.stream(time.split(":"))
			.mapToInt(Integer::parseInt)
			.toArray();

		return timeArr[0] * 60 + timeArr[1];
	}

	static void init() {
		s = scan.next();
		e = scan.next();
		q = scan.next();

		sTime = convertToMin(s);
		eTime = convertToMin(e);
		qTime = convertToMin(q);
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String fileName) {
			try {
				br = new BufferedReader(new FileReader(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		boolean hasNext() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					String line = br.readLine();
					if (line == null) { // EOF에 도달하면 false 반환
						return false;
					}
					st = new StringTokenizer(line);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
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
	}
}
