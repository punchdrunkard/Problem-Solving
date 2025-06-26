import java.util.*;
import java.io.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> finishedTasks = new ArrayList<>();
        
        Arrays.sort(plans, (a, b) -> a[1].compareTo(b[1]));
        Task[] tasks = Arrays.stream(plans)
                             .map(plan -> new Task(plan))
                             .toArray(Task[]::new);
        
        Deque<Task> pendingStack = new ArrayDeque<>();
        
        for (int i = 0; i < tasks.length - 1; i++) {
            Task currentTask = tasks[i];
            Task nextTask = tasks[i + 1];
            int finishTime = tasks[i].start + tasks[i].playtime;
            
            // 현재 과제를 끝낼 수 있는 경우 
            if (finishTime <= nextTask.start) {
                finishedTasks.add(currentTask.key);
                
                // 다음 과제를 시작할 때 까지 남는 시간
                int idleTime = nextTask.start - finishTime; 
 
                while(idleTime > 0 && !pendingStack.isEmpty()) {
                    Task pendingTask =  pendingStack.pollLast();
              
                    if (pendingTask.playtime <= idleTime) { // 멈춰둔 과제를 끝낼 수 있는 경우
                        finishedTasks.add(pendingTask.key);
                        idleTime -= pendingTask.playtime;
                    } else { // 멈춰둔 과제를 끝낼 수 없는 경우
                        pendingTask.playtime -= idleTime;
                        pendingStack.offerLast(pendingTask);
                        idleTime = 0;
                    }
                }
            } else { // 현재 과제를 미뤄야 하는 경우
                int workedTime = nextTask.start - currentTask.start;
                currentTask.playtime -= workedTime;
                pendingStack.offerLast(currentTask);
            }    
        }
        
        // 마지막 과제는 무조건 완료됨
        finishedTasks.add(tasks[tasks.length - 1].key);
        
        // 남은 과제 처리
        while (!pendingStack.isEmpty()) {
            finishedTasks.add(pendingStack.pollLast().key);
        }
        
        return finishedTasks.stream().toArray(String[]::new);
    }
    
    class Task {
        String key;
        int start;
        int playtime;
        
        Task(String key, int start, int playtime) {
            this.key = key;
            this.start = start;
            this.playtime = playtime;
        }
        
        Task(String[] plan) {
            key = plan[0];
            start = convertToMinute(plan[1]);
            playtime = Integer.parseInt(plan[2]);
        }
        
        @Override
        public String toString() {
            return "(" + key + ", start: " + start + ", playtime: " + playtime + ")";
        }
    }
    
    int convertToMinute(String time) {
        int[] parts = Arrays.stream(time.split(":"))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        
        return parts[0] * 60 + parts[1];
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}