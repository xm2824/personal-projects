# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'caculator.ui'
#
# Created by: PyQt5 UI code generator 5.11.3
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets

class Ui_Form(object):
    def setupUi(self, Form):
        Form.setObjectName("Form")
        Form.resize(417, 410)
        self.verticalLayout = QtWidgets.QVBoxLayout(Form)
        self.verticalLayout.setObjectName("verticalLayout")
        self.lineEdit = QtWidgets.QLineEdit(Form)
        self.lineEdit.setMinimumSize(QtCore.QSize(0, 60))
        font = QtGui.QFont()
        font.setPointSize(20)
        self.lineEdit.setFont(font)
        self.lineEdit.setStyleSheet("background-color: rgb(221, 221, 221);")
        self.lineEdit.setAlignment(QtCore.Qt.AlignRight|QtCore.Qt.AlignTrailing|QtCore.Qt.AlignVCenter)
        self.lineEdit.setReadOnly(True)
        self.lineEdit.setObjectName("lineEdit")
        self.verticalLayout.addWidget(self.lineEdit)
        self.gridLayout = QtWidgets.QGridLayout()
        self.gridLayout.setObjectName("gridLayout")
        self.pushButton_15 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_15.sizePolicy().hasHeightForWidth())
        self.pushButton_15.setSizePolicy(sizePolicy)
        self.pushButton_15.setStyleSheet("")
        self.pushButton_15.setObjectName("pushButton_15")
        self.gridLayout.addWidget(self.pushButton_15, 0, 0, 1, 1)
        self.pushButton_14 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_14.sizePolicy().hasHeightForWidth())
        self.pushButton_14.setSizePolicy(sizePolicy)
        self.pushButton_14.setStyleSheet("")
        self.pushButton_14.setObjectName("pushButton_14")
        self.gridLayout.addWidget(self.pushButton_14, 0, 1, 1, 1)
        self.pushButton_13 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_13.sizePolicy().hasHeightForWidth())
        self.pushButton_13.setSizePolicy(sizePolicy)
        self.pushButton_13.setStyleSheet("")
        self.pushButton_13.setObjectName("pushButton_13")
        self.gridLayout.addWidget(self.pushButton_13, 0, 2, 1, 1)
        self.pushButton_20 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_20.sizePolicy().hasHeightForWidth())
        self.pushButton_20.setSizePolicy(sizePolicy)
        self.pushButton_20.setStyleSheet("")
        self.pushButton_20.setObjectName("pushButton_20")
        self.gridLayout.addWidget(self.pushButton_20, 0, 3, 1, 1)
        self.pushButton_9 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_9.sizePolicy().hasHeightForWidth())
        self.pushButton_9.setSizePolicy(sizePolicy)
        self.pushButton_9.setStyleSheet("")
        self.pushButton_9.setObjectName("pushButton_9")
        self.gridLayout.addWidget(self.pushButton_9, 1, 0, 1, 1)
        self.pushButton_7 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_7.sizePolicy().hasHeightForWidth())
        self.pushButton_7.setSizePolicy(sizePolicy)
        self.pushButton_7.setStyleSheet("")
        self.pushButton_7.setObjectName("pushButton_7")
        self.gridLayout.addWidget(self.pushButton_7, 1, 1, 1, 1)
        self.pushButton_8 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_8.sizePolicy().hasHeightForWidth())
        self.pushButton_8.setSizePolicy(sizePolicy)
        self.pushButton_8.setStyleSheet("")
        self.pushButton_8.setObjectName("pushButton_8")
        self.gridLayout.addWidget(self.pushButton_8, 1, 2, 1, 1)
        self.pushButton_17 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_17.sizePolicy().hasHeightForWidth())
        self.pushButton_17.setSizePolicy(sizePolicy)
        self.pushButton_17.setStyleSheet("")
        self.pushButton_17.setObjectName("pushButton_17")
        self.gridLayout.addWidget(self.pushButton_17, 1, 3, 1, 1)
        self.pushButton_4 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_4.sizePolicy().hasHeightForWidth())
        self.pushButton_4.setSizePolicy(sizePolicy)
        self.pushButton_4.setStyleSheet("")
        self.pushButton_4.setObjectName("pushButton_4")
        self.gridLayout.addWidget(self.pushButton_4, 2, 0, 1, 1)
        self.pushButton_3 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_3.sizePolicy().hasHeightForWidth())
        self.pushButton_3.setSizePolicy(sizePolicy)
        self.pushButton_3.setStyleSheet("")
        self.pushButton_3.setObjectName("pushButton_3")
        self.gridLayout.addWidget(self.pushButton_3, 2, 1, 1, 1)
        self.pushButton_2 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_2.sizePolicy().hasHeightForWidth())
        self.pushButton_2.setSizePolicy(sizePolicy)
        self.pushButton_2.setStyleSheet("")
        self.pushButton_2.setObjectName("pushButton_2")
        self.gridLayout.addWidget(self.pushButton_2, 2, 2, 1, 1)
        self.pushButton_19 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_19.sizePolicy().hasHeightForWidth())
        self.pushButton_19.setSizePolicy(sizePolicy)
        self.pushButton_19.setStyleSheet("")
        self.pushButton_19.setObjectName("pushButton_19")
        self.gridLayout.addWidget(self.pushButton_19, 2, 3, 1, 1)
        self.pushButton = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton.sizePolicy().hasHeightForWidth())
        self.pushButton.setSizePolicy(sizePolicy)
        self.pushButton.setStyleSheet("")
        self.pushButton.setObjectName("pushButton")
        self.gridLayout.addWidget(self.pushButton, 3, 0, 1, 1)
        self.pushButton_6 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_6.sizePolicy().hasHeightForWidth())
        self.pushButton_6.setSizePolicy(sizePolicy)
        self.pushButton_6.setStyleSheet("")
        self.pushButton_6.setObjectName("pushButton_6")
        self.gridLayout.addWidget(self.pushButton_6, 3, 1, 1, 1)
        self.pushButton_5 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_5.sizePolicy().hasHeightForWidth())
        self.pushButton_5.setSizePolicy(sizePolicy)
        self.pushButton_5.setStyleSheet("")
        self.pushButton_5.setObjectName("pushButton_5")
        self.gridLayout.addWidget(self.pushButton_5, 3, 2, 1, 1)
        self.pushButton_18 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_18.sizePolicy().hasHeightForWidth())
        self.pushButton_18.setSizePolicy(sizePolicy)
        self.pushButton_18.setStyleSheet("")
        self.pushButton_18.setObjectName("pushButton_18")
        self.gridLayout.addWidget(self.pushButton_18, 3, 3, 1, 1)
        self.pushButton_11 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_11.sizePolicy().hasHeightForWidth())
        self.pushButton_11.setSizePolicy(sizePolicy)
        self.pushButton_11.setStyleSheet("")
        self.pushButton_11.setObjectName("pushButton_11")
        self.gridLayout.addWidget(self.pushButton_11, 4, 0, 1, 2)
        self.pushButton_10 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_10.sizePolicy().hasHeightForWidth())
        self.pushButton_10.setSizePolicy(sizePolicy)
        self.pushButton_10.setStyleSheet("")
        self.pushButton_10.setObjectName("pushButton_10")
        self.gridLayout.addWidget(self.pushButton_10, 4, 2, 1, 1)
        self.pushButton_16 = Btn(Form)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.pushButton_16.sizePolicy().hasHeightForWidth())
        self.pushButton_16.setSizePolicy(sizePolicy)
        self.pushButton_16.setStyleSheet("")
        self.pushButton_16.setObjectName("pushButton_16")
        self.gridLayout.addWidget(self.pushButton_16, 4, 3, 1, 1)
        self.verticalLayout.addLayout(self.gridLayout)

        self.retranslateUi(Form)
        self.pushButton_15.title_clicked.connect(Form.btn_clicked)
        self.pushButton_14.title_clicked.connect(Form.btn_clicked)
        self.pushButton_13.title_clicked.connect(Form.btn_clicked)
        self.pushButton_20.title_clicked.connect(Form.btn_clicked)
        self.pushButton_9.title_clicked.connect(Form.btn_clicked)
        self.pushButton_7.title_clicked.connect(Form.btn_clicked)
        self.pushButton_8.title_clicked.connect(Form.btn_clicked)
        self.pushButton_17.title_clicked.connect(Form.btn_clicked)
        self.pushButton_4.title_clicked.connect(Form.btn_clicked)
        self.pushButton_3.title_clicked.connect(Form.btn_clicked)
        self.pushButton_2.title_clicked.connect(Form.btn_clicked)
        self.pushButton_19.title_clicked.connect(Form.btn_clicked)
        self.pushButton.title_clicked.connect(Form.btn_clicked)
        self.pushButton_6.title_clicked.connect(Form.btn_clicked)
        self.pushButton_5.title_clicked.connect(Form.btn_clicked)
        self.pushButton_18.title_clicked.connect(Form.btn_clicked)
        self.pushButton_11.title_clicked.connect(Form.btn_clicked)
        self.pushButton_10.title_clicked.connect(Form.btn_clicked)
        self.pushButton_16.title_clicked.connect(Form.btn_clicked)
        QtCore.QMetaObject.connectSlotsByName(Form)

    def retranslateUi(self, Form):
        _translate = QtCore.QCoreApplication.translate
        Form.setWindowTitle(_translate("Form", "Form"))
        self.lineEdit.setText(_translate("Form", "0.0"))
        self.pushButton_15.setText(_translate("Form", "AC"))
        self.pushButton_15.setProperty("type", _translate("Form", "control"))
        self.pushButton_14.setText(_translate("Form", "+/-"))
        self.pushButton_14.setProperty("type", _translate("Form", "control"))
        self.pushButton_13.setText(_translate("Form", "%"))
        self.pushButton_13.setProperty("type", _translate("Form", "control"))
        self.pushButton_20.setText(_translate("Form", "/"))
        self.pushButton_20.setProperty("type", _translate("Form", "operator"))
        self.pushButton_9.setText(_translate("Form", "7"))
        self.pushButton_9.setProperty("type", _translate("Form", "num"))
        self.pushButton_7.setText(_translate("Form", "8"))
        self.pushButton_7.setProperty("type", _translate("Form", "num"))
        self.pushButton_8.setText(_translate("Form", "9"))
        self.pushButton_8.setProperty("type", _translate("Form", "num"))
        self.pushButton_17.setText(_translate("Form", "*"))
        self.pushButton_17.setProperty("type", _translate("Form", "operator"))
        self.pushButton_4.setText(_translate("Form", "4"))
        self.pushButton_4.setProperty("type", _translate("Form", "num"))
        self.pushButton_3.setText(_translate("Form", "5"))
        self.pushButton_3.setProperty("type", _translate("Form", "num"))
        self.pushButton_2.setText(_translate("Form", "6"))
        self.pushButton_2.setProperty("type", _translate("Form", "num"))
        self.pushButton_19.setText(_translate("Form", "-"))
        self.pushButton_19.setProperty("type", _translate("Form", "operator"))
        self.pushButton.setText(_translate("Form", "1"))
        self.pushButton.setProperty("type", _translate("Form", "num"))
        self.pushButton_6.setText(_translate("Form", "2"))
        self.pushButton_6.setProperty("type", _translate("Form", "num"))
        self.pushButton_5.setText(_translate("Form", "3"))
        self.pushButton_5.setProperty("type", _translate("Form", "num"))
        self.pushButton_18.setText(_translate("Form", "+"))
        self.pushButton_18.setProperty("type", _translate("Form", "operator"))
        self.pushButton_11.setText(_translate("Form", "0"))
        self.pushButton_11.setProperty("type", _translate("Form", "num"))
        self.pushButton_10.setText(_translate("Form", "."))
        self.pushButton_10.setProperty("type", _translate("Form", "num"))
        self.pushButton_16.setText(_translate("Form", "="))
        self.pushButton_16.setProperty("type", _translate("Form", "equal"))

from Custom_Widget import Btn

if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    Form = QtWidgets.QWidget()
    ui = Ui_Form()
    ui.setupUi(Form)
    Form.show()
    sys.exit(app.exec_())

