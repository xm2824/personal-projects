'''Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.

A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/toeplitz-matrix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。'''
from typing import List
class Solution:
    def isToeplitzMatrix(self, matrix: List[List[int]]) -> bool:
        height = len(matrix)
        width = len(matrix[0])
        for i in range (height):
            for j in range(width):
                pivot = matrix[i][j]
                if pivot == 'visited': continue

                tmpi = i + 1
                tmpj = j + 1
                while tmpi < height and tmpj < width:
                    if matrix[tmpi][tmpj] != pivot:
                        return False;

                    #set visited
                    matrix[tmpi][tmpj] = 'visited'

                    tmpi += 1
                    tmpj += 1

        return True
    