 # 이진 트리의 부모는 해당 노드를 2로 나눈 값인 것을 이용

import sys
input = sys.stdin.readline

N, Q = map(int,input().split())
owned = [False for _ in range(N+1)]

for _ in range(Q):
    dest = int(input())
    tmp = dest
    flag = 0
    while tmp != 1:
        if owned[tmp]:
            flag = 1
            first_owned = tmp
        if tmp%2:
            tmp -= 1
        tmp //= 2
    if flag:
        print(first_owned)
    else:
        owned[dest] = True
        print(0)
