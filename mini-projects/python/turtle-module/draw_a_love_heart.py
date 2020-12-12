'''
Invoke the turtle module to draw a love heart on the screen, and write a confession in the middle of the love, 
the lower right corner of the love heart stands for the person to confess. The words for confession and the person who is confessing can be set.
It took about 10 secs to draw. All worlds can be chinese.
'''

import turtle
import time
def LittleHeart():
    for i in range(200):
        turtle.right(1)
        turtle.forward(2)
love=input('''请输入表白语句，然后回车，默认为"I Love You":\nPlease enter your words for the confession, then press "Enter", by default "I Love You"\n ''')
me=input('请输入你的名字:\nPlease enter your name:\n')
if love=='':                    
    love='I Love you'
turtle.setup(width=900,height=600)  #figure size
turtle.color('red','pink')  # color of love-heart and the pen
turtle.pensize(5)   
turtle.speed(1000000)       # drawing speed

turtle.up()

turtle.hideturtle()
turtle.goto(0,-180)
turtle.showturtle()
turtle.down()
turtle.speed(5)

#* draw the heart
turtle.begin_fill()
turtle.left(140)
turtle.forward(224)
LittleHeart()
turtle.left(120)
LittleHeart()
turtle.forward(224)
turtle.end_fill()

#* draw the words for confesstion
turtle.pensize(5)
turtle.up()
turtle.hideturtle()
turtle.goto(0,0)
turtle.showturtle()
turtle.color('#CD5C5C','pink')
turtle.write(love,font=('gungsuh',30,),align="center")

#* draw my name
turtle.up()
turtle.hideturtle()
if me !='':
    turtle.color('black', 'pink')
    time.sleep(2)
turtle.goto(180,-180)
turtle.showturtle()
turtle.write(me, font=(20,), align="center", move=True)

#* show the screen
window=turtle.Screen()
window.exitonclick()
