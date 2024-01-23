import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int f; // 친구 관계의 수
	static Map<String, String> parents;
	static Map<String, Integer> count; // count[a] := a 를 루트로 하는 집합의 크기
	static List<List<String>> edges;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++){
			init();

			for (var edge: edges){
				union(edge.get(0), edge.get(1));
				int answer = count.get(find(edge.get(0)));
				sb.append(answer).append('\n');
			}
		}

		System.out.print(sb);
	}


	public static void union(String a, String b){
		a = find(a);
		b = find(b);

		if (a.equals(b)){
			return;
		}

		parents.put(a, b);
		count.put(b, count.get(b) + count.get(a));
	}

	public static String find(String a){
		if (parents.get(a).equals(a)) {
			return a;
		}

		// 경로 압축
		parents.put(a, find(parents.get(a)));
		return parents.get(a);
	}

	public static void initDisjointSet(Set<String> names){
		parents = new HashMap<>();
		count = new HashMap<>();

		for (String name: names) {
			parents.put(name, name);
			count.put(name, 1);
		}
	}

	public static void init() throws IOException {
		f = Integer.parseInt(br.readLine());

		// 일단 들어온 이름들 저장해야 할듯...
		Set<String> names = new HashSet<>();
		edges = new ArrayList<>();

		for (int i = 0; i < f; i++){
			OneLineParser line = new OneLineParser(br.readLine());

			String a = line.nextToken();
			String b = line.nextToken();

			names.add(a);
			names.add(b);

			List<String> edge = Arrays.asList(a, b);
			edges.add(edge);
		}

		initDisjointSet(names);


	}

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}
	
		public String nextToken() {
			return st.nextToken();
		}
	}
}
