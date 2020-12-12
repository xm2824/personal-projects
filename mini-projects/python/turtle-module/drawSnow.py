import turtle as tur
import random as rn
import threading
def drawsnow():
    tur.hideturtle()
    tur.pensize(2)
    tur.pencolor("white")
    for i in range(5): #50朵
        tur.penup()
        tur.setx(rn.randint(-350,350))
        tur.sety(rn.randint(1,270))
        tur.pendown()
        dens = 6  #雪花瓣数设为6
        snowsize = rn.randint(20,30)
        for j in range(dens):
            tur.forward(snowsize)
            tur.backward(snowsize)
            tur.right(360/dens)
def drawgroud():
    tur.hideturtle()

    for i in range(rn.randint(10,15)):
        x=rn.randint(-400,350)
        y=rn.randint(-280,-1)
        tur.pencolor("white")
        tur.penup()
        tur.goto(x,y)
        tur.pendown()
        jiaodu=rn.randint(90,110)
        tur.right(jiaodu)
        tur.forward(rn.randint(40,100))
        tur.left(jiaodu)
tur.setup(800,600,200,200)
#tur.tracer(False)  雪花和背景绘制的过程
tur.bgcolor("#B0E0E6") #天蓝色
drawsnow()
drawgroud()
tur.done()