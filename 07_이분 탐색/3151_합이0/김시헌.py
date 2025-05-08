# 언어 : Python3 , (성공/실패) : 1/2 , 메모리 : 33432 KB , 시간 : 5916 ms

import sys
input = sys.stdin.readline

N = int(input())
A = []
freq = {}

nums = list(map(int, input().split()))
for num in nums:
    if num not in freq:
        freq[num] = 0
        A.append(num)
    freq[num] += 1

A.sort()

ans = 0

# (0, 0, 0) 경우 따로 처리
if 0 in freq:
    val0 = freq[0]
    if val0 >= 3:
        ans += val0 * (val0 - 1) * (val0 - 2) // 6

for i in range(len(A)):
    num1 = A[i]
    if num1 >= 0:   # num1부터 0 이상인거는 제껴
        break

    val1 = freq[num1]
    if val1 > 1:    # ex. (-4, -4, 8) 경우
        counter = -(num1 * 2)
        if counter in freq:
            ans += (val1 * (val1 - 1) // 2) * freq[counter]     # pC2 * qC1

    for j in range(i + 1, len(A)):
        num2 = A[j]
        val2 = freq[num2]

        sum_ = num1 + num2
        num3 = -sum_

        if num3 < num2:         # ex. (-4, 3, 1) 경우
            break

        if num3 in freq:
            val3 = freq[num3]

            if num3 == num2:    # ex. (-4, 2, 2) 경우
                ans += val1 * (val3 * (val3 - 1) // 2)      # pC1 * rC2

            else:               # ex. (-4, 1, 3) 경우
                ans += val1 * val2 * val3                   # pC1 * qC1 * rC1

print(ans)
