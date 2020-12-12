class array:
    def __init__(self,size):
        self.arr=[None for i in range(size)]
    def __str__(self) -> str:
        return str(self.arr)
    def __getitem__(self, item):
        if isinstance(item,int):
            return self.arr[item]
        if isinstance(item,slice):
            return self.arr[item.start:item.stop:item.step]
    def __iter__(self):
        return self.arr.__iter__()
    def __setitem__(self, index, value):
        self.arr[index] = value
    def swap(self,index1,index2):
        tmp=self[index1]
        self[index1]=self[index2]
        self[index2]=tmp
    def _stringOfLevel(self,level,height,size):
        indentNum=2**(level+1)-1
        delimiterNum=2**(level+2)-1
        maxNum=2**(height-level)
        low=maxNum-1
        ret=''
        indent=' '*indentNum
        delimiter=' '*delimiterNum
        for i in range(maxNum):
            if low+i == size:break
            if i==0 : ret+= indent
            ret +=str(self[i+low])+delimiter
        return ret
    def treeView(self,height,size):
        ret = ''
        for level in range(height, -1, -1):
            ret += self._stringOfLevel(level,height,size)
            ret += '\n'
        return ret



