def swap(i1,i2,l=[]):
    tmp=l[i1]
    l[i1]=l[i2]
    l[i2]=tmp
def quickSelect(arr=[],left=int,right=int,k=int):
    if left==right : return arr[left]
    else:
        pivot=right
        i=left
        j=right     # j =right rather then right-1!!!
        while i<j:  # i cant = j !!
            while arr[i]<=arr[pivot] and i<right:
                i+=1
            while arr[j]>=arr[pivot] and j>left:
                j-=1
            if i<j:
                swap(i,j,arr)
        swap(i,pivot,arr)
        if k<i: return quickSelect(arr,left,i-1,k)
        if k>i: return quickSelect(arr,i+1,right,k-i-l)
        else :return arr[k]

l=[1,2,35,2,4,353,35,24,2,45,654,23,34,1,435,1,46,2,6,24,24,6,54,77,2,1,5,6,54,2,4,1,5,6,3,7,9,3,7,8,34,2,46,1,6,1,6,2,6,1,6,2,6,1,6,1,6,2,6,2]


lSorted=[1,1,1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 8, 9, 23, 24, 24, 24, 34, 34, 35, 35, 45, 46, 46, 54, 54, 77, 353, 435, 654]


for i in range(0,len(l)):
    print(quickSelect(l,0,len(l)-1,i)==lSorted[i])

