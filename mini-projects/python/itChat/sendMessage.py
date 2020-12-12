
import itchat
import time
import threading
import datetime
itchat.auto_login(hotReload=True)
chatrooms=itchat.get_chatrooms()
replyMessage=''
@itchat.msg_register(itchat.content.TEXT)
def text_reply(msg):
    global replyMessage
    print(f'{msg.user.NickName}: {msg.content}')
    #replyMessage=''
    timeCounter=0
    threading.Thread(target=input_,args=('enter a reply:\n',)).start()
    while replyMessage=='':
        if timeCounter==10:
            msg.user.send(f'hi！我正在學習，稍後回覆。 \n---{str(datetime.datetime.now())[:-7]}')
            return
        time.sleep(1)
        timeCounter+=1
    msg.user.send(replyMessage)
    replyMessage=''

def input_(promt,):
    global replyMessage
    replyMessage=func(promt)

#pprint.pprint(chatrooms[0])
#itchat.send_msg('hi,i'm sending this with python',chatrooms[0]['UserName'])
itchat.run()

