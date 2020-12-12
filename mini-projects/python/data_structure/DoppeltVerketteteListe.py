
class ListElement(object):

    def __init__(self,value):
        self.next=self
        self.prev=self
        self.value=value

    def __eq__(self, o: object) -> bool:
        if not isinstance(o,ListElement):
            return False
        return self.value==o.value and self.prev.value==o.prev.value and self.next.value==o.next.value

    def __str__(self) -> str:
        return str(self.value)




class List(object):
    def __init__(self,head=ListElement):
        self.size=1
        self.head=head
    @staticmethod
    def bind2ListElements(first=ListElement,second=ListElement):
        first.next=second
        second.prev=first
    #splice entfernt <a,...,b> aus der Sequenz und fuegt sie hinter Item insert an

    def splice(self,a=ListElement,b=ListElement,insert=ListElement):
        assert self.contain(a) and self.contain(b),'List muss a und b enthalten'
        assert self.comparePosition(a,b)<=0 ,'a muss vor b sein'
        assert self.comparePosition(insert,a)<0 or self.comparePosition(insert,b)>0
        tmp=self.cutOff(a,b)
        self.insertBehind(tmp,insert)

    #List contains elem
    def contain(self, elem=ListElement):
        tmp=self.head
        for index in range(self.size):
            if tmp is elem:
                return True,index
            tmp=tmp.next
        return False,-1

    def isEmpty(self):
        return self.size==0

    def __str__(self) -> str:
        ret=''
        tmp=self.head
        for index in range(self.size):
            if(index!=self.size-1):
                ret+=str(tmp)+' <==> '
            else:
                ret+=str(tmp)
            tmp=tmp.next
        return ret

    def getFirst(self):
        assert not self.isEmpty()
        return self.head

    def getLast(self):return self.head.prev

    def add(self,value):
        if(self.size==0):
            self.head=ListElement(value)
        else:
            tmp=ListElement(value)
            List.bind2ListElements(self.getLast(),tmp)
            List.bindHeadAndLast(self.head,tmp)
        self.size+=1
    def addElement(self,element=ListElement):
        if(self.size==0):
            self.head=element
        else:
            List.bind2ListElements(self.getLast(), element)
            List.bindHeadAndLast(self.head, element)
        self.size+=1

    @classmethod
    def bindHeadAndLast(cls, head, last):
        head.prev=last
        last.next=head

    def comparePosition(self,a, b):
        containA,indexA= self.contain(a)
        containB,indexB=self.contain(b)
        if not containA or not containB:
            raise RuntimeError('doesnt contain a or b')
        return indexA-indexB

    def addAll(self,l=[]):
        for i in l:
            self.addElement(i)

    def __eq__(self, o: object) -> bool:
        if not isinstance(o,List):
            return False
        if self.size!=o.size:
            return False
        tmp1 =self.head
        tmp2=o.head
        for index in range(self.size):
            if(tmp1!=tmp2) :
                return False
            tmp1=tmp1.next
            tmp2=tmp2.next
        return True

    def cutOff(self, a, b):
        assert self.comparePosition(a,b)<=0
        head=self.head
        last=self.getLast()
        ret=self.buildList(a,b)
        bn = b.next
        ap = a.prev
        if a==head and b==last:
            self.head=None
            return []
        elif a==head and b!=last:
            List.bindHeadAndLast(bn,last)
            self.head=bn
        elif a != head and b==last:
            List.bindHeadAndLast(head,ap)
        else:List.bind2ListElements(ap,bn)
        return ret


    def buildList(self, a, b):
        ret = []
        tmp = a
        while True:
            ret.append(tmp)
            if tmp is b:
                break
            tmp = tmp.next
        return ret

    def insertBehind(self,t=[], toInsert=ListElement):
        if t==None:return
        elif toInsert==None:raise RuntimeError('to insert element is none !')
        elif toInsert==self.getLast():
            List.bind2ListElements(self.getLast(),t[0])
            List.bindHeadAndLast(self.head,t[-1])
        else:
            bn=toInsert.next
            List.bind2ListElements(toInsert,t[0])
            List.bind2ListElements(t[-1],bn)

    def __iter__(self):
        self._tmpPointer1=self.getLast()
        self._tmp=0
        return self

    def __next__(self):
        self._tmpPointer1=self._tmpPointer1.next
        if  self._tmpPointer1==self.head and self._tmp!=0:
            raise StopIteration()
        self._tmp+=1
        return self._tmpPointer1

















