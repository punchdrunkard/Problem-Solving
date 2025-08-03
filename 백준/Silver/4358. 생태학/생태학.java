import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		int total = 0;
		String tree;

		Map<String, Integer> counter = new TreeMap<>();
		while ((tree = br.readLine()) != null) {
			total++;
			counter.put(tree, counter.getOrDefault(tree, 0) + 1);
		}

		for (String name : counter.keySet()) {
			int val = counter.get(name);

			double percentage = ((double)val / total) * 100.0;
			String formattedPercentage = String.format("%.4f", percentage);
			sb.append(name).append(' ').append(formattedPercentage).append('\n');
		}

		System.out.println(sb);
	}
}
