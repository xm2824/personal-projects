import itchat
from mailbot import Mailbot
from datetime import datetime
import globals
chatRooms = None
keywords_out = None
subject = None
mailbot = None


def run():
    global chatRooms,keywords_out,subject,mailbot
    itchat.auto_login(hotReload=False)
    chatRooms = itchat.get_chatrooms()
    keywords_out = '不可an',
    subject = f'微信群消息提醒 {datetime.now()}'
    mailbot = Mailbot()
    itchat.run()



# 自动回复文本等类别的群聊消息
# isGroupChat=True表示为群聊消息
@itchat.msg_register([itchat.content.TEXT], isGroupChat=True)
def group_reply_text(msg):

    msgContent = msg['Content']


    # if keywords requirements are met
    pred1 = lambda x: x in msgContent
    pred2 = lambda x: x not in msgContent
    if any(map(pred1,globals.keywords)) and all(map(pred2,keywords_out)):
        msgBody =f'''
        群: {msg.User.NickName} \r\n
        发送者: {msg.User.Self.NickName} \r\n
        消息内容: {msgContent}\r\n
        '''
        mailbot.send(globals.sendTo,subject,msgBody)




