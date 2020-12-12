from skimage import io
import numpy as np
from imageProcessing.basic_Image_Operations import *
from numpy import linalg as la
from itertools import product
luoshen=io.imread('/Users/taoxiang/Python/python/imageProcessing/LuoShen.png')
#print(len(luoshen))
#print(len(luoshen[0]))
#print(len(luoshen[0][0]))

result=np.zeros(shape=(len(luoshen),len(luoshen[0]),len(luoshen[0][0])),dtype=int)
#show(tmp)
columns=np.arange(len(luoshen[0]))
rows=np.arange(len(luoshen))
A=np.array([[500,1000,400],[200,200,600],[1,1,1]])
A_=np.array([[1000,400,900],[200,600,600],[1,1,1]])
B=np.array([[0,1317,0],[0,0,819]])
B_=np.array([[1317,0,1317],[0,819,819]])
#print(result[0,0,:])
#print(luoshen[0,0,:])
def _isInScope1(x, y):
    return y >= 200 and y+4*x >= 2200 and 3*y+2*x <= 2600


def _calculate(x, y, A,B):
    b = np.array([x, y, 1])
    alpha_beta_sita = la.solve(A, b).reshape(3)
    tx, ty = tuple((B @ alpha_beta_sita).astype(int))
    result[y, x, :] = luoshen[ty, tx, :]


def _isInScope2(x, y):
    return 3*y+2*x-2600>0 and y<=600 and y+4*x-4200<=0


for y,x in product(rows,columns):
    if _isInScope1(x, y):
        _calculate(x,y,A,B)
    elif _isInScope2(x,y):
        _calculate(x,y,A_,B_)

io.imsave('textured.png',result)
show(result)


