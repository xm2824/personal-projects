import numpy as np
def hash1(x=()):
    X=np.array(x)
    a=np.array((1,2,4))
    return a @ X%17
def hash2(x=()):
    X=np.array(x)
    a1=np.array((0,0,1))
    a2=np.array((6,6,2))
    return a1@X %7,a2@X%7
def hash3(x=()):
    x1=np.array(x)
    a1=np.array((1,0,0))
    a2=np.array((0,2,2))
    return a1@x1 %3,a2@x1%3

while True:
    x=(int(input()),int(input()),int(input()))
    print(f'1: {hash1(x)}')
    print(f'2: {hash2(x)}')
    print(f'3: {hash3(x)}')



