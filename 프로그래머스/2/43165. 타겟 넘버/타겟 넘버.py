answer = 0

def solution(numbers, target):
    
    # 갈 수 있는 루트 : 더하거나 / 빼기 
    # 목표 : 가능한 모든 경우의 수 
    # 가능한 경우의 수는 연산자를 + 하거나 - 하는거라서 brute force 가 가능 -> dfs
    
    dfs(0, 0, numbers, target)
    return answer

# idx: 현재 보고 있는 숫자 인덱스 
def dfs(idx, currentSum, numbers, target):
    global answer
    
    # base case 
    if idx == len(numbers):
        if currentSum == target:
            answer += 1
        return 

    # 이 숫자를 더할지 뺄지 
    dfs(idx + 1, currentSum + numbers[idx], numbers, target)
    dfs(idx + 1, currentSum - numbers[idx], numbers, target)