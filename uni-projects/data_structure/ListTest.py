import unittest
from GAD.Datenstrukturen.DoppeltVerketteteListe import *

class test(unittest.TestCase):

    head=ListElement('im head')
    a=ListElement('a')
    b=ListElement('b')
    c=ListElement('c')
    t=ListElement('t')
    myList=List(head)
    myList.addElement(a)
    myList.addElement(b)
    myList.addAll([c,t])
    def test_add(self):
        self.assertEqual('im head <==> a <==> b <==> c <==> t', str(test.myList))
    def test_Contain(self):
        self.assertTrue(test.myList.contain(test.head)[0])
        self.assertFalse(test.myList.contain(ListElement('halo'))[0])
    def test_comparePosition(self):
        self.assertTrue(test.myList.comparePosition(test.head,test.a)<0)
    def test_eq(self):
        a=ListElement('a')
        ap=ListElement('ap')
        an=ListElement('an')
        a.prev=ap
        a.next=an

        b=ListElement('a')
        b.next=an
        b.prev=ap
        self.assertEqual(a,b)
    def test_firstAndLast(self):
        self.assertEqual(test.head,test.myList.getFirst())
        self.assertEqual(test.t,test.myList.getLast())
    def test_splice(self):
        head = ListElement('im head')
        a = ListElement('a')
        b = ListElement('b')
        c = ListElement('c')
        t = ListElement('t')
        myList = List(head)
        myList.addAll([a,b,c, t])
        myList.splice(head,c,t)
        print('mylist\n', myList)
        li=[ListElement('im head'),ListElement('a'),ListElement('b'),ListElement('c')]
        expect=List(ListElement('t'))
        expect.addAll(li)
        self.assertEqual(myList,expect)
    def test_iterable(self):
        for i in test.myList:
            print(i)
    def test_in(self):
        self.assertTrue(test.a in test.myList)
        self.assertFalse(ListElement('a') in test.myList)
    def test_sternchen(self):
        print(*test.myList)

if __name__ == '__main__':
    unittest.main()
