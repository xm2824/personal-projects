def swap(i1,i2,l=[]):
    tmp=l[i1]
    l[i1]=l[i2]
    l[i2]=tmp
def quickSort1(arr=[]):
    pivot=len(arr)-1
    vergleich=0
    while   pivot>=1:
        left=0
        right=pivot-1
        while left< right and arr[left]<=arr[pivot]:
            left+=1
            vergleich+=1
        while left<right and arr[right] >= arr[pivot]:
            right-=1
            vergleich+=1
        if left<right:swap(left,right,arr)
        elif arr[left]>arr[pivot]:swap(left,pivot,arr)
        else:pivot-=1
    print(f'quickSort1: {vergleich}')

def quickSort2(arr=[],left=int,right=int,vergleich=[]):
    if left>=right : return
    else:
        pivot=right
        i=left
        j=right     # j =right rather then right-1!!!
        while i<j:  # i cant = j !!
            while arr[i]<=arr[pivot] and i<right:
                i+=1
                vergleich[0]+=1
            while arr[j]>=arr[pivot] and j>left:
                j-=1
                vergleich[0] += 1
            if i<j:
                swap(i,j,arr)
        swap(i,pivot,arr)
        quickSort2(arr,left,i-1,vergleich)
        quickSort2(arr,i+1,right,vergleich)

l=[1,2,35,2,4,353,35,24,2,45,654,23,34,1,435,1,46,2,6,24,24,6,54,77,2,1,5,6,54,2,4,1,5,6,3,7,9,3,7,8,34,2,46,1,6,1,6,2,6,1,6,2,6,1,6,1,6,2,6,2]
print(f'elements number n= {len(l)}')
l2=l[:]
quickSort1(l)
print('quickSort1:',l)
vergleich=[0]
quickSort2(l2,0,len(l2)-1,vergleich)
print(f'quickSort2: {vergleich[0]}' )
print('quickSort2:',l2)





