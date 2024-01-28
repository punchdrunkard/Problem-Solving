import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
  
public class Main {  
    public static void main(String[] args) throws IOException {  
       StringBuilder sb = new StringBuilder();  
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
       int t = Integer.parseInt(br.readLine());  
  
       int[][] apartment = makeApartment();  
  
       for (int tc = 0; tc < t; tc++) {  
          int k = Integer.parseInt(br.readLine());  
          int n = Integer.parseInt(br.readLine());  
          sb.append(apartment[k][n]).append('\n');  
       }  
  
       System.out.println(sb);  
    }  
  
    public static int[][] makeApartment() {  
       int[][] apartment = new int[15][15];  
  
       // 0층  
       for (int i = 1; i <= 14; i++) {  
          apartment[0][i] = i;  
       }  
  
       for (int i = 1; i <= 14; i++) {  
          for (int j = 1; j <= 14; j++) {  
             for (int k = 1; k <= j; k++){  
                apartment[i][j] += apartment[i - 1][k];  
             }  
          }  
       }  
  
       return apartment;  
    }  
}
