import smtplib
from email.mime.text import MIMEText
from email.header import Header
from typing import List
import sys

class Mailbot():
    def __init__(self):
        self.gmail_user = 'yunshu.tech@gmail.com'
        self.gmail_password = 'xt2333333XT'

    def send(self,sentTo,subject,msgBody):
        gmail_user = self.gmail_user
        sent_from = self.gmail_user
        gmail_password = self.gmail_password
        email_text = """

                %s
                """ % (msgBody)

        msg = MIMEText(email_text,_charset= 'utf-8')
        msg["Subject"] = Header(subject, charset = 'utf-8')
        msg["From"] = gmail_user
        msg["To"] = sentTo

        try:
            server = smtplib.SMTP_SSL('smtp.gmail.com', 465)
            server.ehlo()
            server.login(gmail_user, gmail_password)

            server.sendmail(sent_from,sentTo,msg.as_string())
            server.close()

            print('Email sent!')
        except Exception as e:
            print('Something went wrong...')
            raise e



