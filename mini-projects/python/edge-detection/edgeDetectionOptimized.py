from skimage import io
#img=io.imread('/Users/taoxiang/team181/lena.bmp')
big=io.imread('pexels-photo-414612.jpeg.jpg')
import numpy as np
from itertools import product
from skimage import data,color
def showImg(img):
    io.imshow(img)
    io.show()

def edgeDetection(E):
    X = np.array(range(1, len(E)-1))  # X range
    Y = np.array(range(1, len(E[0])-1))
    copy=np.copy(E)
    for x,y in product(X,Y):
        #print(x,y)
        E[x,y,:3]=calculate(x,y,copy)

def calculate(x, y, img):
        result=np.zeros(3).astype(int)
        for i in [-1,1]:
            result+=img[x+i,y,:3].astype(int)
            result+=img[x,y+i,:3].astype(int)
        result-=4*img[x,y,:3].astype(int)
        return  np.array([ max(min(i,255),0) for i in result])
def run(img):
    showImg(img)
    edgeDetection(img)
    showImg(img)



run(big)
io.imsave('big_.jpg',big)