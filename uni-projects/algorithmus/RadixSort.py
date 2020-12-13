toBeSort=[1,30,5,872,90,54,13,653,23,345,24,43,21,546,324,54,21]
expected=sorted(toBeSort)

def radixSort(arr=[],maxNumOfDigits=int):
    digitsBuckets=[[] for _ in range(10)]
    div=1
    for i in range(maxNumOfDigits):
        div*=10
        for num in arr:
            tmp=num
            for _ in range(i):tmp/=10
            index=int(tmp%10)
            digitsBuckets[index].append(num)   # index = 當前位的值= bucketsIndex
        arr.clear()
        for bucket in digitsBuckets:
            if len(bucket)!=0:
                arr+=bucket                    # concatenate buckets
                bucket.clear()
        print(f'the {i+1}.th concatenating: {arr}')

test2=toBeSort.copy()
radixSort(test2,3)
print(test2==expected)                          # True

