class array:
    def __init__(self,size):
        self.arr=[None for i in range(size)]

    def __str__(self) -> str:
        return str(self.arr)
class MaxQueue:
    def __init__(self,size):
        self._arr=array(size)
        self.heapSize=0
    def __getitem__(self, item):
        if isinstance(item,int):
            return self._arr.arr[item]
        if isinstance(item,slice):
            return self._arr.arr[item.start:item.stop:item.step]
    def __iter__(self):return self._arr.arr.__iter__()
    def __setitem__(self, index, value):
        self._arr.arr[index]=value

    def leftChildIndex(self,index=int):
        ret=2*index+1
        if ret>=self.heapSize:return -1
        else : return ret
    def rightChildIndex(self,index=int):
        ret=2*index+2
        if ret>=self.heapSize:return -1
        else: return ret
    def parentIndex(self,index=int):
        if index==0 : raise RuntimeError(" root has no parent")
        return int((index-1)/2)
    def setPriority(self,index=int,priority=int):
        assert self[index][0]<priority,'the priority is smaller than the current priority'
        self[index][0]=priority
        while index>0 and self[self.parentIndex(index)][0]<self[index][0]:
            self.swap(index,self.parentIndex(index))
            index=self.parentIndex(index)
    def add(self,element=[]):
        self.heapSize+=1
        self[self.heapSize-1]=element
        value=element[0]
        element[0]=-99999
        self.setPriority(self.heapSize-1,value)
    def addAll(self,li=[]):
        for i in li:self.add(i)
    def swap(self, i1, i2):
        tmp= self[i1]
        self[i1]=self[i2]
        self[i2]=tmp
    def getHeightOf(self,*,index=int):
        maxIndex=self.heapSize-1
        count=0
        while index*2+1<=maxIndex:
            count+=1
            index=index*2+1
        return count
    def heapHeight(self):
        return self.getHeightOf(index=0)
    def __str__(self):
        ret=''
        for level in range(self.heapHeight(),-1,-1):
            ret+=self._stringOfLevel(level)
            ret+='\n'
        return ret

    def _stringOfLevel(self,level):
        indentNum=2**(level+1)-1
        delimiterNum=2**(level+2)-1
        maxNum=2**(self.heapHeight()-level)
        low=maxNum-1
        ret=''
        indent=' '*indentNum
        delimiter=' '*delimiterNum
        for i in range(maxNum):
            if low+i == self.heapSize:break
            if i==0 : ret+= indent
            ret +=str(self[i+low][0])+delimiter
        return ret
    def _maxHeapify(self,index=int):
        if self.isLeaf(index):return
        left=self.leftChildIndex(index)
        right=self.rightChildIndex(index)
        maxIndex=index
        if self[maxIndex][0]<self[left][0]: maxIndex=left
        if right!=-1 and self[maxIndex][0]<self[right][0]:maxIndex=right
        if maxIndex!=index:
            self.swap(maxIndex,index)
            self._maxHeapify(maxIndex)

    def isLeaf(self, index):
        return self.leftChildIndex(index)==-1
    def extractHighestPriorityEvent(self):
        assert self.heapSize>0 ,'queue underflow'
        ret=self[0]
        self[0]=self[self.heapSize-1]
        self.heapSize-=1
        self._maxHeapify(0)
        return ret
    def searchEventOfPriority(self,priority):
        return list(filter(lambda ele:ele!=None and ele[0]==priority,self))
    def filterEvents(self,priotiry):
        return list(filter(lambda ele:ele!=None and ele[0]>=priotiry,self))




