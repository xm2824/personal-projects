import enum
from typing import Iterable, List,Tuple

def all_x(iter:Iterable,x):
            for i in iter:
                if isinstance(i,Iterable) and not isinstance(i,str) and len(i)>=1:
                    ret = all_x(i,x)
                    if not ret: return False
                elif i != str(x) and i != x: return False 
            
            return True

hash_map = {1:{(1,1)},2:{(1,2),(2,1)}, 3:{(1,3),(3,1)}}


def getFactors(n, h_len, v_len):
    if n in hash_map.keys():
        return hash_map[n]

    se = set()
    if n<=h_len: se.add((1, n))
    if n<=v_len: se.add((n, 1))

    for i in range(2, min(n // 2 + 1, max(h_len, v_len))):
        if n % i == 0:
            if n//i <= h_len and i<=v_len: se.add((i, n // i))
            if n//i <= v_len and i<=h_len: se.add((n // i, i))

    hash_map[n] = se
    return se


class Solution:
    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        if len(matrix) ==0: return 0
        if all_x(matrix,0)  :return 0

        h_len = len(matrix[0])
        v_len = len(matrix)

        ret = 0
        max_probe = h_len*v_len

        for i in range(v_len):
            for j in range(h_len):
                if (v_len-i)*(h_len-j) <= ret: continue

                probe = ret +1
                #print(probe)
                while probe <= max_probe:
                    pairs = getFactors(probe,h_len,v_len)
                    
                    for k in pairs:
                        delta_i = k[0]
                        delta_j = k[1]

                        lst_i = i +delta_i
                        lst_j = j +delta_j 

                        if lst_i > v_len or lst_j > h_len: continue

                        t= [matrix[tmp][j:lst_j] for tmp in range(i,lst_i)]
                        #print(f't:{t}\t\t\t\t\t\t(i,j): {i,j}\t\t\t\t pari:{k}')
                        if all_x(t,1):
                            #print(t,probe,i,j)
                            ret = probe
                            #print(f'\n\n{ret}\n\n')
                            break
                    probe += 1
        return ret


a=Solution().maximalRectangle([["0","0","1","0"],["1","1","1","1"],["1","1","1","1"],["1","1","1","0"],["1","1","0","0"],["1","1","1","1"],["1","1","1","0"]])

print(a)






