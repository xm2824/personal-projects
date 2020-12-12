from GAD.Datenstrukturen.array import *
import numpy as np
class BinearySearchTree(array):
    def __init__(self,size=int):
        super(BinearySearchTree,self).__init__(size)
        self._size=0

    def lChild(self,index):
        return self[2*index+1]

    def set_lChild(self,index,value):
        self[2*index+1]=value

    def rChild(self,index):
        return self[2*index+2]

    def set_rChild(self,index,value):
        self[2*index+2]=value
    @property
    def root(self):return self[0]
    @property
    def height(self): return int(np.log2(self.size))
    def isFull(self):return self.size==self.bound
    @property
    def size(self):return self._size
    @property
    def bound(self): return len(self.arr)

    def parent(self,index):
        if index==0: return None
        return self[(index-1)//2]
    def add(self,key):
        assert not self.isFull(),'tree overflow!'
        tmp=0
        bound=self.bound
        while tmp <bound and self[tmp]is not None:
            node=self[tmp]
            if key<node:tmp=2*tmp+1
            elif key> node:tmp=2*tmp+2
            else:
                child=2*tmp+1
                if child <bound:
                    if self[child] is None: tmp=child
                    elif self[child+1] is None : tmp=child+1
                    else: return False

        if tmp>bound:return False
        if self[tmp] is None:
            self[tmp]=key
            self._size+=1
            return True
        return False
    def addAll(self,elems=[]):
        for i in elems:self.add(i)
    def inorderTreeWalk(self,index):
        if index>=self.bound:return
        node=self[index]
        if node is not None:
            self.inorderTreeWalk(2*index+1)
            print(node)
            self.inorderTreeWalk(2*index+2)
    def search(self,key):
        return self._doSearch(key,0)
    def _doSearch(self,key=int,index=int):
        if index>=self.bound:return None,
        node=self[index]
        if key==node:return True,index
        elif key<node:return self._doSearch(key,2*index+1)
        else:return self._doSearch(key,2*index+2)

    def minFrom(self,index):
        tmp=index
        while tmp<self.bound and self[tmp]is not None:
            tmp=2*tmp+1
        return self.parent(tmp)

    def maxFrom(self,index):
        tmp=index
        while tmp<self.bound and self[tmp] is not None:
            tmp=2*tmp+2
        return self.parent(tmp)
    def successor(self,index):
        tmp=index*2+2
        if tmp<self.bound and self[tmp] is not None:return self.minFrom(tmp)

        ancestorIndex=(index-1)//2
        ancestor = self[ancestorIndex]
        while ancestor is not None and  index == self.rChild(ancestorIndex):
            index=ancestorIndex
            ancestorIndex=(ancestorIndex-1)//2
            ancestor=self[ancestorIndex]
        return ancestor



binTree=BinearySearchTree(10)
binTree.addAll([6,5,7,2,5,8])
print(binTree)
print(binTree.inorderTreeWalk(0))
print(binTree.search(7))
print(binTree.maxFrom(0))
print(binTree.minFrom(0))
print(binTree.successor(2))



