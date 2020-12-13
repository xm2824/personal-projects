def swap(arr=[],i1=int,i2=int):
    tmp=arr[i1]
    arr[i1]=arr[i2]
    arr[i2]=tmp
    print(arr)

def SlowSort(arr=[], left=int, right=int):
    if left>=right:return
    mid =int((left+right)/2)
    SlowSort(arr, left, mid)
    SlowSort(arr, mid + 1, right)
    if arr[mid]>arr[right]:swap(arr,mid,right)
    SlowSort(arr, left, right - 1)

arr=[5,4,3,2,1]
SlowSort(arr, 0, 4)