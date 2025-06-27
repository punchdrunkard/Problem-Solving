import java.util.*;
import java.io.*;

class Solution {
    
    int min = Integer.MAX_VALUE;
    int[] picks;
    String[] minerals;
    int[][] parts;
    
    public int solution(int[] picks, String[] minerals) {
        init(picks, minerals);
        parts = countPartsMineral();
        dfs(0, 0, 0, 0, 0);
        
        return min;
    }
    
    
    void dfs(int i, int cost, int diamondCount, int ironCount, int stoneCount) {
        // base case
        if (i >= parts.length) {
            min = Math.min(min, cost);
            return;
        }
        
        // 현재 남아있는 곡괭이 수를 계산
        int remainDiamond = picks[0] - diamondCount;
        int remainIron = picks[1] - ironCount;
        int remainStone = picks[2] - stoneCount;
        
        // base case 2 : 더이상 사용할 수 있는 곡괭이가 없는 경우
        if (remainDiamond <= 0 && remainIron <= 0 && remainStone <= 0) {
            min = Math.min(min, cost);
            return;
        }
        
        // recursive case
        // 현재 partIdx 에서 곡괭이 0, 1, 2 번 중에 어떤걸 선택?
        if (remainDiamond > 0) {
            dfs(i + 1, cost + calculateCost(0, parts[i]), 
                diamondCount + 1, ironCount, stoneCount);
        }
        
        if (remainIron > 0) {
            dfs(i + 1, cost + calculateCost(1, parts[i]), 
                diamondCount, ironCount + 1, stoneCount);
        }
        
        if (remainStone > 0) {
            dfs(i + 1, cost + calculateCost(2, parts[i]), 
                diamondCount, ironCount, stoneCount + 1);
        }
    }
    
    int calculateCost(int kind, int[] mineralCount) {
        if (kind == 0) {
            return mineralCount[0] + mineralCount[1] + mineralCount[2];
        } else if (kind == 1) {
            return 5 * mineralCount[0] + mineralCount[1] + mineralCount[2];
        } else {
           return 25 * mineralCount[0] + 5 * mineralCount[1] + mineralCount[2];  
        }
    }
    
    // 각 그룹별 다이아/철/돌 갯수를 저장
    int[][] countPartsMineral() { 
        int partLength = (int) Math.ceil((double) minerals.length / 5);
        int[][] part = new int[partLength][3];

        int count = 0;
        
        for (int i = 0; i < minerals.length; i++) {
            int partIdx = i / 5; 
            
            if ("diamond".equals(minerals[i])) {
                part[partIdx][0]++;
            } else if ("iron".equals(minerals[i])) {
                part[partIdx][1]++;
            } else {
                part[partIdx][2]++;
            }
        }
        
        return part;
    }
    
    void print2D(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        sout(sb);
    }
    
    void init(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = minerals;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}