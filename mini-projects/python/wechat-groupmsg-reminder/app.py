from flask import *
import globals
import groupmsg_filter
import os.path, os
from shutil import copyfile
import threading
app = Flask(__name__)


@app.route('/<int:id>', methods=['GET', 'POST'])
@app.route('/', methods=('GET', 'POST'))
def index(id=None):
    if request.method == 'POST':
        keywords = request.form.get('keywords')
        email = request.form.get('email')

        globals.keywords = keywords.split(',')
        globals.sendTo = email

        groupmsg_filter.run()



    return render_template('index.html')



def _copyfile():
    while not os.path.exists('QR.png'):pass
    copyfile('QR.png','static/images/QR.png')



if __name__ == '__main__':
    if os.path.exists('QR.png'):
        os.remove('QR.png')
    if os.path.exists('static/images/QR.png'):
        os.remove('static/images/QR.png')

    threading.Thread(target=_copyfile).start()

    app.run()

    print('hello')
