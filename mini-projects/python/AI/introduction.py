'''
assume the car has the following attributes:
(horizontal velocity, vertical velocity , x, y)
at the beginning :statues=(1,0,0,50)
a random policy(v0,v1,x,y)
with the result of dot product of policy * statues  we decide the car to go up or down or horizontally,
which is
    >0: go up
    =0: horizontal
    <0: go down
according to this result , (up or down or horizontal) ,we should have a new statues -> define a function which calculate the new statues
'''
import numpy as np
initialStatues=(1,0,0,50)   # (horizontal velocity, vertical velocity , x, y) x,y should be integers
destination=(range(80,101),range(21))
def getNextStatues(flag,statues):
    t=1
    Vhor,Vver,x,y=statues
    if flag>0:
        Vver+=1
        x+=Vhor*t
        y+=Vver*t
    elif flag==0:
        Vver=0
        x+=Vhor*t
    else:
        Vver-=1
        x+=Vhor*t
        y+=Vver*t
    return Vhor,Vver,x,y
def arriveDes(statues):
    x,y=statues[2:]
    x,y=int(x),int(y)
    return x in destination[0] and y in destination[1]
def outOfBound(statues):
    x,y=statues[2:]
    return x<0 or x>100 or y<0 or y>100
def run():
    statues=initialStatues
    policy = np.random.rand(1, 4)-0.5
    while True:
        outcome=policy@statues
        statues=getNextStatues(outcome,statues)
        if arriveDes(statues):
            print('get to destination!')
            print(f'policy: {policy}')
            return
        if outOfBound(statues):
            policy=np.random.rand(1,4)-0.5
            statues=initialStatues
run()
# best policy :[ 0.39219965 -0.35616683  0.00705916 -0.11928387]

