from flask import *
import globals
import groupmsg_filter

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







if __name__ == '__main__':




    app.run()


