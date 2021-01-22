import itchat
import pprint as pp

itchat.auto_login(hotReload=True)
chatRooms = itchat.get_chatrooms()
changlian = None
for i in chatRooms:
    if i.NickName == '长脸粉丝团':
        changlian = i
        break

msg = '''
@all 
这个404就是进来捣乱的， 半句不离文革， 希望大家不要理他。 --python自动消息 
'''
while True:
    itchat.send_msg(msg,changlian.UserName)
    t.sleep(10)

itchat.run()
