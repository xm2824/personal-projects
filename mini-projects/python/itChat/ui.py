# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'untitled.ui'
#
# Created by: PyQt5 UI code generator 5.12.2
#
# WARNING! All changes made in this file will be lost!
import sys

from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtWidgets import QApplication


class Ui_mainWindow(object):
    def setupUi(self, mainWindow):
        mainWindow.setObjectName("mainWindow")
        mainWindow.resize(1440, 900)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Expanding, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(mainWindow.sizePolicy().hasHeightForWidth())
        mainWindow.setSizePolicy(sizePolicy)
        self.centralwidget = QtWidgets.QWidget(mainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.msgDisplayWIndow = QtWidgets.QTextBrowser(self.centralwidget)
        self.msgDisplayWIndow.setGeometry(QtCore.QRect(10, 30, 521, 171))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.msgDisplayWIndow.sizePolicy().hasHeightForWidth())
        self.msgDisplayWIndow.setSizePolicy(sizePolicy)
        self.msgDisplayWIndow.setAutoFillBackground(False)
        self.msgDisplayWIndow.setFrameShape(QtWidgets.QFrame.WinPanel)
        self.msgDisplayWIndow.setFrameShadow(QtWidgets.QFrame.Sunken)
        self.msgDisplayWIndow.setLineWidth(0)
        self.msgDisplayWIndow.setMidLineWidth(0)
        self.msgDisplayWIndow.setVerticalScrollBarPolicy(QtCore.Qt.ScrollBarAlwaysOn)
        self.msgDisplayWIndow.setHorizontalScrollBarPolicy(QtCore.Qt.ScrollBarAlwaysOff)
        self.msgDisplayWIndow.setTabChangesFocus(True)
        self.msgDisplayWIndow.setAcceptRichText(True)
        self.msgDisplayWIndow.setCursorWidth(0)
        self.msgDisplayWIndow.setObjectName("msgDisplayWIndow")
        self.friendsList = QtWidgets.QListWidget(self.centralwidget)
        self.friendsList.setGeometry(QtCore.QRect(640, 30, 131, 331))
        self.friendsList.setObjectName("friendsList")
        self.chaterLabel = QtWidgets.QLabel(self.centralwidget)
        self.chaterLabel.setGeometry(QtCore.QRect(10, 10, 531, 16))
        self.chaterLabel.setText("")
        self.chaterLabel.setObjectName("chaterLabel")
        self.frsLabel = QtWidgets.QLabel(self.centralwidget)
        self.frsLabel.setGeometry(QtCore.QRect(680, 10, 60, 16))
        self.frsLabel.setObjectName("frsLabel")
        self.translateEditor = QtWidgets.QLineEdit(self.centralwidget)
        self.translateEditor.setGeometry(QtCore.QRect(800, 30, 631, 29))
        self.translateEditor.setClearButtonEnabled(True)
        self.translateEditor.setObjectName("translateEditor")
        self._zwLabel = QtWidgets.QLabel(self.centralwidget)
        self._zwLabel.setGeometry(QtCore.QRect(775, 100, 31, 16))
        self._zwLabel.setObjectName("_zwLabel")
        self.chineseDisplay = QtWidgets.QTextBrowser(self.centralwidget)
        self.chineseDisplay.setGeometry(QtCore.QRect(800, 58, 631, 103))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.chineseDisplay.sizePolicy().hasHeightForWidth())
        self.chineseDisplay.setSizePolicy(sizePolicy)
        font = QtGui.QFont()
        font.setPointSize(18)
        self.chineseDisplay.setFont(font)
        self.chineseDisplay.setAutoFillBackground(False)
        self.chineseDisplay.setHorizontalScrollBarPolicy(QtCore.Qt.ScrollBarAlwaysOff)
        self.chineseDisplay.setObjectName("chineseDisplay")
        self._enLabel = QtWidgets.QLabel(self.centralwidget)
        self._enLabel.setGeometry(QtCore.QRect(774, 202, 60, 16))
        self._enLabel.setObjectName("_enLabel")
        self._deLabel = QtWidgets.QLabel(self.centralwidget)
        self._deLabel.setGeometry(QtCore.QRect(774, 306, 60, 16))
        self._deLabel.setObjectName("_deLabel")
        self.englishDisplay = QtWidgets.QTextBrowser(self.centralwidget)
        self.englishDisplay.setGeometry(QtCore.QRect(800, 160, 631, 103))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.englishDisplay.sizePolicy().hasHeightForWidth())
        self.englishDisplay.setSizePolicy(sizePolicy)
        font = QtGui.QFont()
        font.setPointSize(18)
        self.englishDisplay.setFont(font)
        self.englishDisplay.setHorizontalScrollBarPolicy(QtCore.Qt.ScrollBarAlwaysOff)
        self.englishDisplay.setObjectName("englishDisplay")
        self.germanDisplay = QtWidgets.QTextBrowser(self.centralwidget)
        self.germanDisplay.setGeometry(QtCore.QRect(800, 262, 631, 100))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.germanDisplay.sizePolicy().hasHeightForWidth())
        self.germanDisplay.setSizePolicy(sizePolicy)
        font = QtGui.QFont()
        font.setPointSize(18)
        self.germanDisplay.setFont(font)
        self.germanDisplay.setHorizontalScrollBarPolicy(QtCore.Qt.ScrollBarAlwaysOff)
        self.germanDisplay.setObjectName("germanDisplay")
        self.chatroomsList = QtWidgets.QListWidget(self.centralwidget)
        self.chatroomsList.setGeometry(QtCore.QRect(640, 30, 131, 331))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Expanding, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.chatroomsList.sizePolicy().hasHeightForWidth())
        self.chatroomsList.setSizePolicy(sizePolicy)
        self.chatroomsList.setFrameShape(QtWidgets.QFrame.WinPanel)
        self.chatroomsList.setHorizontalScrollBarPolicy(QtCore.Qt.ScrollBarAlwaysOff)
        self.chatroomsList.setLayoutMode(QtWidgets.QListView.SinglePass)
        self.chatroomsList.setViewMode(QtWidgets.QListView.ListMode)
        self.chatroomsList.setObjectName("chatroomsList")
        self.groupBox = QtWidgets.QGroupBox(self.centralwidget)
        self.groupBox.setGeometry(QtCore.QRect(0, 760, 141, 45))
        self.groupBox.setObjectName("groupBox")
        self.playMusicButton = QtWidgets.QRadioButton(self.groupBox)
        self.playMusicButton.setGeometry(QtCore.QRect(10, 22, 21, 20))
        self.playMusicButton.setText("")
        icon = QtGui.QIcon.fromTheme("edgeDetection")
        self.playMusicButton.setIcon(icon)
        self.playMusicButton.setAutoRepeat(False)
        self.playMusicButton.setObjectName("playMusicButton")
        self.volSlider = QtWidgets.QSlider(self.groupBox)
        self.volSlider.setGeometry(QtCore.QRect(40, 23, 101, 22))
        self.volSlider.setProperty("value", 50)
        self.volSlider.setOrientation(QtCore.Qt.Horizontal)
        self.volSlider.setObjectName("volSlider")
        self.groupBox_3 = QtWidgets.QGroupBox(self.centralwidget)
        self.groupBox_3.setGeometry(QtCore.QRect(140, 760, 141, 45))
        self.groupBox_3.setObjectName("groupBox_3")
        self.queryWeatherEditor = QtWidgets.QLineEdit(self.groupBox_3)
        self.queryWeatherEditor.setGeometry(QtCore.QRect(4, 20, 133, 21))
        self.queryWeatherEditor.setFrame(False)
        self.queryWeatherEditor.setClearButtonEnabled(True)
        self.queryWeatherEditor.setObjectName("queryWeatherEditor")
        self.groupBox_2 = QtWidgets.QGroupBox(self.centralwidget)
        self.groupBox_2.setGeometry(QtCore.QRect(10, 200, 521, 101))
        self.groupBox_2.setTitle("")
        self.groupBox_2.setObjectName("groupBox_2")
        self.fileOpenButton = QtWidgets.QPushButton(self.groupBox_2)
        self.fileOpenButton.setGeometry(QtCore.QRect(-5, -4, 51, 32))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.fileOpenButton.sizePolicy().hasHeightForWidth())
        self.fileOpenButton.setSizePolicy(sizePolicy)
        self.fileOpenButton.setToolTipDuration(23)
        self.fileOpenButton.setIconSize(QtCore.QSize(16, 16))
        self.fileOpenButton.setAutoRepeat(False)
        self.fileOpenButton.setObjectName("fileOpenButton")
        self.openImgButton = QtWidgets.QPushButton(self.groupBox_2)
        self.openImgButton.setGeometry(QtCore.QRect(32, -4, 51, 32))
        self.openImgButton.setToolTipDuration(23)
        self.openImgButton.setObjectName("openImgButton")
        self.openVideoButton = QtWidgets.QPushButton(self.groupBox_2)
        self.openVideoButton.setGeometry(QtCore.QRect(70, -4, 51, 32))
        self.openVideoButton.setToolTipDuration(23)
        self.openVideoButton.setObjectName("openVideoButton")
        self.msgSenderButton = QtWidgets.QPushButton(self.groupBox_2)
        self.msgSenderButton.setGeometry(QtCore.QRect(470, -1, 51, 21))
        self.msgSenderButton.setObjectName("msgSenderButton")
        self.msgEditor = QtWidgets.QLineEdit(self.groupBox_2)
        self.msgEditor.setGeometry(QtCore.QRect(0, 20, 521, 81))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.msgEditor.sizePolicy().hasHeightForWidth())
        self.msgEditor.setSizePolicy(sizePolicy)
        self.msgEditor.setCursor(QtGui.QCursor(QtCore.Qt.IBeamCursor))
        self.msgEditor.setDragEnabled(False)
        self.msgEditor.setCursorMoveStyle(QtCore.Qt.LogicalMoveStyle)
        self.msgEditor.setObjectName("msgEditor")
        self.autoReplyCheckBox = QtWidgets.QCheckBox(self.groupBox_2)
        self.autoReplyCheckBox.setGeometry(QtCore.QRect(240, 0, 87, 20))
        font = QtGui.QFont()
        font.setFamily("Optima")
        font.setBold(True)
        font.setItalic(True)
        font.setWeight(75)
        self.autoReplyCheckBox.setFont(font)
        self.autoReplyCheckBox.setCursor(QtGui.QCursor(QtCore.Qt.ArrowCursor))
        self.autoReplyCheckBox.setTabletTracking(False)
        self.autoReplyCheckBox.setChecked(False)
        self.autoReplyCheckBox.setObjectName("autoReplyCheckBox")
        self.groupBox_4 = QtWidgets.QGroupBox(self.centralwidget)
        self.groupBox_4.setGeometry(QtCore.QRect(10, 300, 201, 23))
        self.groupBox_4.setTitle("")
        self.groupBox_4.setObjectName("groupBox_4")
        self.logOutButton = QtWidgets.QPushButton(self.groupBox_4)
        self.logOutButton.setGeometry(QtCore.QRect(94, -4, 113, 32))
        self.logOutButton.setObjectName("logOutButton")
        self.loginButton = QtWidgets.QPushButton(self.groupBox_4)
        self.loginButton.setGeometry(QtCore.QRect(-6, -4, 113, 32))
        self.loginButton.setObjectName("loginButton")
        self._waiwenLabel = QtWidgets.QPushButton(self.centralwidget)
        self._waiwenLabel.setGeometry(QtCore.QRect(840, 7, 241, 32))
        self._waiwenLabel.setObjectName("_waiwenLabel")
        self.calendarWidget = QtWidgets.QCalendarWidget(self.centralwidget)
        self.calendarWidget.setGeometry(QtCore.QRect(1170, 640, 272, 173))
        self.calendarWidget.setObjectName("calendarWidget")
        self.tumMoodleButton = QtWidgets.QPushButton(self.centralwidget)
        self.tumMoodleButton.setGeometry(QtCore.QRect(530, 30, 111, 32))
        self.tumMoodleButton.setCheckable(False)
        self.tumMoodleButton.setObjectName("tumMoodleButton")
        self.campusOnlineButton = QtWidgets.QPushButton(self.centralwidget)
        self.campusOnlineButton.setGeometry(QtCore.QRect(530, 50, 111, 32))
        self.campusOnlineButton.setCheckable(False)
        self.campusOnlineButton.setObjectName("campusOnlineButton")
        self.liveStreamButton = QtWidgets.QPushButton(self.centralwidget)
        self.liveStreamButton.setGeometry(QtCore.QRect(530, 110, 111, 32))
        self.liveStreamButton.setCheckable(False)
        self.liveStreamButton.setObjectName("liveStreamButton")
        self.getLYC = QtWidgets.QPushButton(self.centralwidget)
        self.getLYC.setGeometry(QtCore.QRect(530, 170, 113, 32))
        self.getLYC.setObjectName("getLYC")
        self.searchChatroomsButton = QtWidgets.QPushButton(self.centralwidget)
        self.searchChatroomsButton.setGeometry(QtCore.QRect(530, 130, 111, 32))
        self.searchChatroomsButton.setCheckable(False)
        self.searchChatroomsButton.setObjectName("searchChatroomsButton")
        self.FamilyButton = QtWidgets.QPushButton(self.centralwidget)
        self.FamilyButton.setGeometry(QtCore.QRect(530, 190, 111, 32))
        self.FamilyButton.setCheckable(False)
        self.FamilyButton.setObjectName("FamilyButton")
        self.TUMButton = QtWidgets.QPushButton(self.centralwidget)
        self.TUMButton.setGeometry(QtCore.QRect(530, 210, 111, 32))
        self.TUMButton.setCheckable(False)
        self.TUMButton.setObjectName("TUMButton")
        self.eraGruppe = QtWidgets.QPushButton(self.centralwidget)
        self.eraGruppe.setGeometry(QtCore.QRect(530, 150, 113, 32))
        self.eraGruppe.setObjectName("eraGruppe")
        self.searchFreindsButton = QtWidgets.QPushButton(self.centralwidget)
        self.searchFreindsButton.setGeometry(QtCore.QRect(530, 230, 111, 32))
        self.searchFreindsButton.setCheckable(False)
        self.searchFreindsButton.setObjectName("searchFreindsButton")
        self.tum_JudgeButton = QtWidgets.QPushButton(self.centralwidget)
        self.tum_JudgeButton.setGeometry(QtCore.QRect(530, 70, 111, 32))
        self.tum_JudgeButton.setCheckable(False)
        self.tum_JudgeButton.setObjectName("tum_JudgeButton")
        self.tum_artemisButton = QtWidgets.QPushButton(self.centralwidget)
        self.tum_artemisButton.setGeometry(QtCore.QRect(530, 90, 111, 32))
        self.tum_artemisButton.setCheckable(False)
        self.tum_artemisButton.setObjectName("tum_artemisButton")
        self.groupBox_5 = QtWidgets.QGroupBox(self.centralwidget)
        self.groupBox_5.setGeometry(QtCore.QRect(280, 760, 115, 43))
        self.groupBox_5.setObjectName("groupBox_5")
        self.wikiLineEdi = QtWidgets.QLineEdit(self.groupBox_5)
        self.wikiLineEdi.setGeometry(QtCore.QRect(1, 19, 121, 21))
        self.wikiLineEdi.setClearButtonEnabled(True)
        self.wikiLineEdi.setObjectName("wikiLineEdi")
        self.groupBox_6 = QtWidgets.QGroupBox(self.centralwidget)
        self.groupBox_6.setGeometry(QtCore.QRect(390, 760, 115, 43))
        self.groupBox_6.setObjectName("groupBox_6")
        self.googleLineEdi = QtWidgets.QLineEdit(self.groupBox_6)
        self.googleLineEdi.setGeometry(QtCore.QRect(1, 19, 121, 21))
        self.googleLineEdi.setClearButtonEnabled(True)
        self.googleLineEdi.setObjectName("googleLineEdi")
        self.groupBox_7 = QtWidgets.QGroupBox(self.centralwidget)
        self.groupBox_7.setGeometry(QtCore.QRect(500, 760, 115, 43))
        self.groupBox_7.setObjectName("groupBox_7")
        self.baiduLineEdi = QtWidgets.QLineEdit(self.groupBox_7)
        self.baiduLineEdi.setGeometry(QtCore.QRect(1, 19, 121, 21))
        self.baiduLineEdi.setClearButtonEnabled(True)
        self.baiduLineEdi.setObjectName("baiduLineEdi")
        self.groupBox_8 = QtWidgets.QGroupBox(self.centralwidget)
        self.groupBox_8.setGeometry(QtCore.QRect(610, 760, 115, 43))
        self.groupBox_8.setObjectName("groupBox_8")
        self.youtubeLineEdi = QtWidgets.QLineEdit(self.groupBox_8)
        self.youtubeLineEdi.setGeometry(QtCore.QRect(1, 19, 121, 21))
        self.youtubeLineEdi.setClearButtonEnabled(True)
        self.youtubeLineEdi.setObjectName("youtubeLineEdi")
        self.notes = QtWidgets.QTextBrowser(self.centralwidget)
        self.notes.setGeometry(QtCore.QRect(1120, 390, 321, 251))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.notes.sizePolicy().hasHeightForWidth())
        self.notes.setSizePolicy(sizePolicy)
        font = QtGui.QFont()
        font.setPointSize(14)
        self.notes.setFont(font)
        self.notes.setHorizontalScrollBarPolicy(QtCore.Qt.ScrollBarAlwaysOff)
        self.notes.setReadOnly(False)
        self.notes.setObjectName("notes")
        self.notesLabel = QtWidgets.QLabel(self.centralwidget)
        self.notesLabel.setGeometry(QtCore.QRect(1120, 370, 60, 16))
        self.notesLabel.setObjectName("notesLabel")
        self.mathFrame = QtWidgets.QFrame(self.centralwidget)
        self.mathFrame.setGeometry(QtCore.QRect(10, 340, 521, 421))
        self.mathFrame.setFrameShape(QtWidgets.QFrame.StyledPanel)
        self.mathFrame.setFrameShadow(QtWidgets.QFrame.Raised)
        self.mathFrame.setObjectName("mathFrame")
        self.mathTabWidget = QtWidgets.QTabWidget(self.mathFrame)
        self.mathTabWidget.setGeometry(QtCore.QRect(-1, -1, 521, 421))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.mathTabWidget.sizePolicy().hasHeightForWidth())
        self.mathTabWidget.setSizePolicy(sizePolicy)
        self.mathTabWidget.setLayoutDirection(QtCore.Qt.LeftToRight)
        self.mathTabWidget.setTabPosition(QtWidgets.QTabWidget.North)
        self.mathTabWidget.setTabShape(QtWidgets.QTabWidget.Rounded)
        self.mathTabWidget.setElideMode(QtCore.Qt.ElideLeft)
        self.mathTabWidget.setUsesScrollButtons(False)
        self.mathTabWidget.setTabsClosable(False)
        self.mathTabWidget.setMovable(False)
        self.mathTabWidget.setTabBarAutoHide(False)
        self.mathTabWidget.setObjectName("mathTabWidget")
        self.General_CalculationTab = QtWidgets.QWidget()
        self.General_CalculationTab.setObjectName("General_CalculationTab")
        self.mathResults = QtWidgets.QTextBrowser(self.General_CalculationTab)
        self.mathResults.setGeometry(QtCore.QRect(0, 270, 511, 121))
        self.mathResults.setObjectName("mathResults")
        self.mathEditor = QtWidgets.QLineEdit(self.General_CalculationTab)
        self.mathEditor.setGeometry(QtCore.QRect(2, 0, 511, 251))
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.mathEditor.sizePolicy().hasHeightForWidth())
        self.mathEditor.setSizePolicy(sizePolicy)
        self.mathEditor.setClearButtonEnabled(False)
        self.mathEditor.setObjectName("mathEditor")
        self.mathTabWidget.addTab(self.General_CalculationTab, "")
        self.functionTab = QtWidgets.QWidget()
        self.functionTab.setObjectName("functionTab")
        self.mathTabWidget.addTab(self.functionTab, "")
        self.bineary_hexadecimalTab = QtWidgets.QWidget()
        self.bineary_hexadecimalTab.setObjectName("bineary_hexadecimalTab")
        self.mathTabWidget.addTab(self.bineary_hexadecimalTab, "")
        self.moduleTab = QtWidgets.QWidget()
        self.moduleTab.setObjectName("moduleTab")
        self.mathTabWidget.addTab(self.moduleTab, "")
        self.groupBox_9 = QtWidgets.QGroupBox(self.centralwidget)
        self.groupBox_9.setGeometry(QtCore.QRect(720, 760, 115, 43))
        self.groupBox_9.setObjectName("groupBox_9")
        self.lingueeEditor = QtWidgets.QLineEdit(self.groupBox_9)
        self.lingueeEditor.setGeometry(QtCore.QRect(1, 19, 121, 21))
        self.lingueeEditor.setClearButtonEnabled(True)
        self.lingueeEditor.setObjectName("lingueeEditor")
        #mainWindow.setCentralWidget(self.centralwidget)

        self.retranslateUi(mainWindow)
        self.mathTabWidget.setCurrentIndex(0)
        QtCore.QMetaObject.connectSlotsByName(mainWindow)

    def retranslateUi(self, mainWindow):
        _translate = QtCore.QCoreApplication.translate
        mainWindow.setWindowTitle(_translate("mainWindow", "wechat"))
        self.frsLabel.setText(_translate("mainWindow", "Friends"))
        self._zwLabel.setText(_translate("mainWindow", "ZH:"))
        self.chineseDisplay.setHtml(_translate("mainWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:\'.SF NS Text\'; font-size:18pt; font-weight:400; font-style:normal;\">\n"
"<p style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><br /></p></body></html>"))
        self._enLabel.setText(_translate("mainWindow", "EN:"))
        self._deLabel.setText(_translate("mainWindow", "DE:"))
        self.englishDisplay.setHtml(_translate("mainWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:\'.SF NS Text\'; font-size:18pt; font-weight:400; font-style:normal;\">\n"
"<p style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px; font-size:10pt;\"><br /></p></body></html>"))
        self.germanDisplay.setHtml(_translate("mainWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:\'.SF NS Text\'; font-size:18pt; font-weight:400; font-style:normal;\">\n"
"<p style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px; font-size:10pt;\"><br /></p></body></html>"))
        self.chatroomsList.setSortingEnabled(False)
        self.groupBox.setTitle(_translate("mainWindow", "Music"))
        self.groupBox_3.setTitle(_translate("mainWindow", "Weather"))
        self.queryWeatherEditor.setText(_translate("mainWindow", "??????"))
        self.fileOpenButton.setText(_translate("mainWindow", "File"))
        self.openImgButton.setText(_translate("mainWindow", "Img"))
        self.openVideoButton.setText(_translate("mainWindow", "Vid"))
        self.msgSenderButton.setText(_translate("mainWindow", "send"))
        self.autoReplyCheckBox.setText(_translate("mainWindow", "AutoReply"))
        self.logOutButton.setText(_translate("mainWindow", "log out"))
        self.loginButton.setText(_translate("mainWindow", "log in"))
        self._waiwenLabel.setText(_translate("mainWindow", "Translate"))
        self.tumMoodleButton.setText(_translate("mainWindow", "TUM-Moodle"))
        self.campusOnlineButton.setText(_translate("mainWindow", "CampusOnline"))
        self.liveStreamButton.setText(_translate("mainWindow", "liveStream"))
        self.getLYC.setText(_translate("mainWindow", "LYC"))
        self.searchChatroomsButton.setText(_translate("mainWindow", "Chatrooms"))
        self.FamilyButton.setText(_translate("mainWindow", "Family"))
        self.TUMButton.setText(_translate("mainWindow", "TUM"))
        self.eraGruppe.setText(_translate("mainWindow", "ERA chatroom"))
        self.searchFreindsButton.setText(_translate("mainWindow", "Friends"))
        self.tum_JudgeButton.setText(_translate("mainWindow", "TUM-Judge"))
        self.tum_artemisButton.setText(_translate("mainWindow", "TUM-Artemis"))
        self.groupBox_5.setTitle(_translate("mainWindow", "Wiki"))
        self.groupBox_6.setTitle(_translate("mainWindow", "Google"))
        self.groupBox_7.setTitle(_translate("mainWindow", "Baidu"))
        self.groupBox_8.setTitle(_translate("mainWindow", "youtube"))
        self.notes.setHtml(_translate("mainWindow", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:\'.SF NS Text\'; font-size:14pt; font-weight:400; font-style:normal;\">\n"
"<p style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><br /></p></body></html>"))
        self.notesLabel.setText(_translate("mainWindow", "Notes"))
        self.mathTabWidget.setTabText(self.mathTabWidget.indexOf(self.General_CalculationTab), _translate("mainWindow", "General Calculation"))
        self.mathTabWidget.setTabText(self.mathTabWidget.indexOf(self.functionTab), _translate("mainWindow", "Function "))
        self.mathTabWidget.setTabText(self.mathTabWidget.indexOf(self.bineary_hexadecimalTab), _translate("mainWindow", "Bineary/Hexadecimal"))
        self.mathTabWidget.setTabText(self.mathTabWidget.indexOf(self.moduleTab), _translate("mainWindow", "Module"))
        self.groupBox_9.setTitle(_translate("mainWindow", "linguee"))


