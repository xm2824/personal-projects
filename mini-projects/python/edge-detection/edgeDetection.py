from skimage import io
import numpy as np
from itertools import product
def edgeDetection(img):
    '''
    do the edge decetion using Laplace Filter convolution matrix
    M =
        0  1  0
        1 -4  1
        0  1  0

    the calculation can be simplified as following:
         let P(i,j) be the RGB vector value of pixel(i,j)  e.g. (0,0,0)=black
         then P(i,j)' = P(i-1,j) + P(i+1,j) + P(i,j-1) + P(i,j+1) - 4*P(i,j)
    what we do is calculate this for each pixel of an image

    :param img: a 3D array ,elements can be accessed with img[i,j,colorChannel], colorChannel:{0:'R',1:'G',2:'B'}
    :return: none
    '''
    Columns = np.array(range(len(img[0]) ))                                          # Columns=[0,1,2,3,..., width_of_the_image-1]
    Rows = np.array(range(len(img)))                                                 # Rows=[0,1,2,3,..., length_of_the_image-1]

    copy=np.copy(img)                                                                # copy the original array in order to keep it unchanged
                                                                                     #（ We will need to read the value of a certain pixel multiple times )

    for i,j in product(Rows,Columns):                                                # for each(i,j) calculating the pixel value of R,G,B
        '''
        1.
        for i,j in product(Rows,Columns) is equivalent to
        
        for (i=0;i<img.length();i++)
            for (j=0;j<img[0].length();j++)
                {
                    ...
                }
               
        
        2. we need to deal with corner and border pixels:
            during the project we came up with 2 methods to deal with corner and border pixels:
            a) expand the array :  see "_expandingArr(img)" below
            b) do not expand the array ,just deal with each corner and border separately (totally 8 cases), and only considering 
               the actual  surrounding pixels :
               e.g:
                    a b ...
                    c   ...
                    ...
                    so here a is a corner , b and c are its actual surrounding pixels
                    the formula P(i,j)' = P(i-1,j) + P(i+1,j) + P(i,j-1) + P(i,j+1) - 4*P(i,j) should now be adjusted to
                    P(a)' = P(b) + P(c) - 2*P(a)
                    the border-cases are similar
            
            after we explored further, we found out that these 2 methods actually give the same results,
            but method b) is more efficient and simpler in assembly (because we don't need to expand the array in assembly)
            so we used method b) here:
        '''
        if i==0 and j==0 :                                                          # upper left conner
            tmp=copy[i+1,j,:].astype(int)+copy[i,j+1,:].astype(int) -2*copy[i,j,:].astype(int)
            _linearRescalingVecotors(tmp)
            img[i,j,:]=tmp

        elif i==0 and j==len(img[0])-1:                                             # upper right conner
            tmp=copy[i,j-1,:].astype(int)+copy[i+1,j,:].astype(int) -2*copy[i,j,:].astype(int)
            _linearRescalingVecotors(tmp)
            img[i, j, :]=tmp

        elif i== len(img)-1 and j==0:                                               # lower left conner
            tmp=copy[i-1,j,:].astype(int)+copy[i,j+1,:].astype(int) -2*copy[i,j,:].astype(int)
            _linearRescalingVecotors(tmp)
            img[i, j, :] = tmp

        elif i== len(img)-1 and j== len(img[0])-1:                                  # lower right conner
            tmp=copy[i-1,j,:].astype(int)+copy[i,j-1,:].astype(int) -2*copy[i,j,:].astype(int)
            _linearRescalingVecotors(tmp)
            img[i, j, :] = tmp

        elif i==0:                                                                # first row
            tmp=copy[i,j-1,:].astype(int)+copy[i,j+1,:].astype(int)+copy[i+1,j,:].astype(int) -3*copy[i,j,:].astype(int)
            _linearRescalingVecotors(tmp)
            img[i, j, :] = tmp

        elif i==Rows[-1]:                                                         # last row
            tmp=copy[i,j-1,:].astype(int)+copy[i,j+1,:].astype(int)+copy[i-1,j,:].astype(int) -3*copy[i,j,:].astype(int)
            _linearRescalingVecotors(tmp)
            img[i, j, :] = tmp

        elif j==0:                                                                # first column
            tmp=copy[i-1,j,:].astype(int)+copy[i+1,j,:].astype(int)+copy[i,j+1,:] .astype(int)-3*copy[i,j,:].astype(int)
            _linearRescalingVecotors(tmp)
            img[i, j, :] = tmp

        elif j==Columns[-1]:                                                      # last column
            tmp=copy[i-1,j,:].astype(int)+copy[i+1,j,:].astype(int)+copy[i,j-1,:].astype(int) -3*copy[i,j,:].astype(int)
            _linearRescalingVecotors(tmp)
            img[i, j, :] = tmp

        # non-corner and non-border pixels:
        else:
            img[i, j, 0]=_calculate(i, j, copy, 0)
            img[i, j, 1]=_calculate(i, j, copy, 1)
            img[i, j, 2]=_calculate(i, j, copy, 2)



def _expandingArr(img):
    '''
    expand the 3D Array to calculate the boundary pixels:
    We add two more rows and two more columns to the original array,
    and the pixel value of the newly added row or column duplicates the value of the original boundary row or column
    in order to smooth the image.
    example:
        input: (Red value matrix of an image)
                1 2 3
                4 5 6
                7 8 9
        output:
              1 1 2 3 3
              1 1 2 3 3
              4 4 5 6 6
              7 7 8 9 9
              7 7 8 9 9

        after we explored further, we found out that this method gives the same result as method b)
        e.g:
                    a' b ...
                    c   ...
                    ...
                    after we expand it, it should look like
                    a a   ...
                    a a' b ...
                    c c   ...
                    where a' denotes that we are looking at this pixel
                    then the new pixel value of a' should be = a + a + b + c - 4*a
                                                             = 0 + 0 + b + c - 2*a , this term shows actually we don't need to
                                                                                     expand the array ,since we delete all the
                                                                                     expanded pixel values

    :param img: a 3D array ,elements can be accessed with img[x,y,colorChannel], colorChannel:{0:'R',1:'G',2:'B'}
    :return: the expanded 3D array
    '''
    arr=np.zeros((len(img)+2,len(img[0])+2,len(img[0][0]))).astype(int)        # initiate a 3D array with 2 more rows and columns
    arr[1:-1,1:-1,:]=img                                                       # copy the original pixel values to the corresponding pixels,
                                                                               # [1:-1] : 1 indicates the second row /collumn (inclusive), -1 indicates the last row/collumn(exclusive)
    arr[1:-1,0,:]=img[:,0,:]                                                   # copy the first original column
    arr[1:-1,-1,:]=img[:,-1,:]                                                 # copy the last original column
    arr[0,1:-1,:]=img[0,:,:]                                                   # copy the first original row
    arr[-1,1:-1,:]=img[-1,:,:]                                                 # copy the last original row

    arr[0,0,:]=img[0,0,:]                                                      # copy the RGB-Values of 4 corner pixels in the original array
    arr[0,-1:]=img[0,-1,:]                                                     # into the corresponding corners in the expanded array
    arr[-1,0,:]=img[-1,0,:]
    arr[-1,-1,:]=img[-1,-1,:]



    return arr


def _calculate(i, j, img, rgb):
    '''
    calculate non-corner and non-border pixels with formula  P(i,j)' = P(i-1,j) + P(i+1,j) + P(i,j-1) + P(i,j+1) - 4*P(i,j)

    :param i: i.row
    :param j: j.column
    :param img: 3D array
    :param rgb: color channel
    :return: rescaled value
    '''
    result= int(img[i + 1, j, rgb])\
                +int(img[i - 1, j, rgb])\
                +int(img[i, j + 1, rgb])\
                +int(img[i, j - 1, rgb])\
                -4*int(img[i, j, rgb])                                           # used the simplified formula of convolution matrix calculation

    return _rescalingLinear(result)                                              # we choose second rescaling variation , which will make the background grey

def _rescalingClamp(value):
    '''
    # first variatation : set negative value to 0 and larger-than-255 value to 255

    :param value: actual calculated value
    :return: rescaled value
    '''
    return max(min(value,255),0)

def _rescalingLinear(value):
    '''
    # second variatation:
        the actual value range of _calculate(...) is [-1020,1020],
        and the expected value range is [0,255]
        we define a function r to rescale the value:
        r : [-1020,1020] -> [0,255] , x -> (x+1020)/8

        :param value: actual calculated value
        :return: rescaled value
        '''
    return int((value+1020)/8)

def _linearRescalingVecotors(vector):
    '''
    apply rescalingLinear function to each element in the vector
    :param vector: a vector whose elements need to be rescale
    :return: none ( in place operation)
    '''
    for i in range(3):
        vector[i]=_rescalingLinear(vector[i])


def showImg(img):
    io.imshow(img)
    io.show()
def run(img):
    showImg(img)
    edgeDetection(img)
    showImg(img)

path='/Users/taoxiang/team181/resources/lena.bmp'
img=io.imread(path)
run(img)

#run(lena)
io.imsave('lena2.bmp',img)



