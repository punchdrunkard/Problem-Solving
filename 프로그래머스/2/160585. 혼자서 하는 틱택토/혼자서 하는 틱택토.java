import java.util.*;
import java.io.*;

class Solution {
    
    static final char O = 'O'; // 선공 
    static final char X = 'X'; // 후공 
    static final char EMPTY = '.';
    
    public int solution(String[] board) {
        return isValidGameBoard(board) ? 1 : 0;
    }
    
    boolean isValidGameBoard(String[] board) {
        int roundCount = countHowManyRoundProceed(board);
        int[] count = calculateEachAttackCount(board);
        
        sout("roundCount: " + roundCount);
        
        return doesProceedCorrectOrder(board, roundCount, count) 
            && doesProceedNotEndedGame(makeCharArray(board), roundCount, count);
    }
    
    // 순서에 대한 것을 판단하는 함수
    // - 순서를 지키지 않은 경우
    // 1. 1개인데 X만 있는 경우 => 2와 통합 가능 
    // 2. 현재 홀수 개의 표시가 있는데 X가 많은 경우 (항상 O가 더 많아야 함)
    // 3. 현재 짝수 개의 표시가 있는데 둘의 카운팅이 안맞는 경우
    boolean doesProceedCorrectOrder(String[] board, int roundCount, int[] count) {  
        int oCount = count[0];
        int xCount = count[1];
        
        if (roundCount % 2 == 0) {
            return oCount == xCount;
        } else { // 항상 선공이 먼저! 
            return oCount == xCount + 1;
        }
    }
    
    // 승리에 대한 것을 판단하는 함수 
    boolean doesProceedNotEndedGame(char[][] board, int roundCount, int[] count){
        // - 게임이 종료되는 경우 : 누군가가 이기는 경우 
        // 가로 / 세로 / 대각선 (양쪽 방향에 주의!) 판단하기 
        // 3 * 3 이라는게 정해져있어서 정안되면 하드코딩 ok 
        int oCount = count[0];
        int xCount = count[1];
        
        boolean isGameEnded = false;
        
        boolean[] win = new boolean[2];
        calculateWhoisWinner(board, win);
        boolean oWin = win[0];
        boolean xWin = win[1];
        
        if (oWin && xWin) {
            return false;
        }
        
        if (oWin) {
            return oCount == xCount + 1;
        }
        
        if (xWin) {
            return xCount == oCount;
        }
        
        
        
        return true;
    }
    
    void calculateWhoisWinner(char[][] board, boolean[] win) {
        // 세로
        for (int row = 0; row < board.length; row++) {
            int oCount = 0;
            int xCount = 0;
            
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == EMPTY) {
                    continue;
                }
                
                if (board[row][col] == O) {
                    oCount++;
                } else {
                    xCount++;
                }
            }
            
            if (oCount == 3) {
                win[0] = true;
            } else if (xCount == 3) {
                win[1] = true;
            }
        }
        
        // 가로
        for (int col = 0; col < 3; col++) {
            int oCount = 0;
            int xCount = 0;
            
            for (int row = 0; row < 3; row++) {
                if (board[row][col] == EMPTY) {
                    continue;
                }
                
                if (board[row][col] == O) {
                    oCount++;
                } else {
                    xCount++;
                }
            }
            
            if (oCount == 3) {
                win[0] = true;
            } else if (xCount == 3) {
                win[1] = true;
            }
        }
        
        // 대각선
        if (board[0][0] == O && board[1][1] == O && board[2][2] == O) {
           win[0] = true;
       }
        
       if (board[0][0] == X && board[1][1] == X && board[2][2] == X) {
           win[1] = true;
       }
        
        if (board[0][2] == O && board[1][1] == O && board[2][0] == O) {
           win[0] = true;
       }
        
       if (board[0][2] == X && board[1][1] == X && board[2][0] == X) {
           win[1] = true;
       }
    }
    
    int[] calculateEachAttackCount(String[] board) {
        int[] count = new int[2]; 
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == EMPTY) {
                    continue;
                }
                
                if (board[i].charAt(j) == O) {
                    count[0]++;
                } else if (board[i].charAt(j) == X) {
                    count[1]++;
                }
            }
        }
        
        return count;
    }
    
    int countHowManyRoundProceed(String[] board) {
        int roundCount = 0;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == EMPTY) {
                    continue;
                }
                
                roundCount++;
            }
        }
        
        return roundCount;
    }
    
    char[][] makeCharArray(String[] arr) {
        char[][] res = new char[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i].toCharArray();
        }
        return res;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}