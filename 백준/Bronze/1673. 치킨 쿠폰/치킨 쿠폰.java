import java.util.*;
import java.io.*;

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}

			StringTokenizer st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			solve(n, k);
		}

		System.out.println(sb);
	}

	static void solve(int n, int k) {
		long couponCount = n;
		long stampCount = 0; // 도장의 갯수
		long answer = 0;

		while (couponCount >= 1) {
			// 가지고 있는 쿠폰으로 치킨을 먹는다.
			answer += couponCount;

			// 스탬프를 적립한다.
			stampCount += couponCount;

			// 스탬프를 쿠폰으로 바꾼다.
			couponCount = (stampCount / k);
			stampCount = stampCount % k;
		}

		sb.append(answer).append('\n');
	}

}
