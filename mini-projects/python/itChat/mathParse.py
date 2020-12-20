import numpy as np
binOperationDict={
    '+':lambda x,y:x+y,
    '-':lambda x,y:x-y,
    '*':lambda x,y:x*y,
    '/':lambda x,y:x/y,
    '%':lambda x,y:x%y,
    '@':lambda x,y:x@y,
    '.T':lambda x,_:x.T,
    '**':lambda x,y:x**y
}
operators=binOperationDict.keys()
variableDict={}
functions={}
def _isVector(math): return math.count('[') == 1 and math.count(']') == 1 and ';' not in math
def _isWeekPureMathExpr(math=str):return not (math.islower() or math.isupper())

def _is2DArr(math):return math.count('[') == 1 and math.count(']') == 1 and ';' in math


def isVectorBinOperation(math):
    if _isFunctionCall(math) :return -1,
    # ret = index of the corresbonding operator

    for i in operators:
        if i not in math:continue
        index=math.find(i)
        if math.rfind('(',0,index) > math.rfind(')',0,index):continue
        ret = index
        return (ret, )if isinstance(ret,int) else ret
    return -1,


def _isAssignment(math): return math.count('=')==1


def _rank(math):
    if ('rank' in math and '(' in math and ')' in math) :
        return True,math.find('('),math.rfind(')')
    else : return False,


def _hasBrackets(math):
    if '(' in math and ')' in math: return True,math.find('('),math.rfind(')')
    else: return False,
def parseFunction(func=str):
    colonIndex = func.find(':')
    arrowIndex = func.find('->'), func.find('>')
    functionName = func[:colonIndex].strip()
    parameters=func[colonIndex+1:arrowIndex[0]].strip().split(',')
    value = func[arrowIndex[1] + 1:].strip()
    functions[functionName] = {'para':{a:None for a in parameters},'value':value}
    return f'{functionName} = {value}'
def getValue(expr=str):         # expr : ' f ( 4 , 5 ) '
    lBracketIndex=expr.rfind('(')
    rBracketIndex=expr.find(')')
    funcName=expr[:lBracketIndex].strip()
    innerExpr=expr[lBracketIndex+1:rBracketIndex].strip()
    innerValue=mathParser(innerExpr)[0]
    if not isinstance(innerValue,tuple):
        innerValue=innerValue,
    para=functions[funcName]['para']
    paraKeys=para.keys()
    party=tuple(zip(paraKeys,innerValue))
    for i in party:
        para[i[0]]=i[1]
    valueExp=functions[funcName]['value']
    for i in paraKeys:
        valueExp=valueExp.replace(i,str(para[i]))
        print(valueExp)
    return eval(valueExp),expr


def _isFunction(math): return ':' in math and '->' in math


def _isFunctionCall(math):
    ret='(' in math and math[-1]==')'
    if ret:
        funcName=math[:math.find('(')].strip()
        if funcName in functions.keys():
            firstRBracketIndex=math.find(')')
            count=math.count('(',0,firstRBracketIndex)
            times=count

            index=firstRBracketIndex-1
            while True:
                if count==0:return index==len(math)-1
                index=math.find(')',index+1)
                count-=1
    return False

def mathParser(math=str):
    global variableDict
    math=math.strip()
    if math[0]=='(' and math[-1]==')':math=math[1:-1]
    if math=='clr':
        variableDict.clear()
        return str(variableDict),None
    if math=='' :return'',
    if math=='whos':return str(variableDict),None
    if math=='funcs':return str(functions),None
    if _isWeekPureMathExpr(math) and not _is2DArr(math) and not isVectorBinOperation(math) and not _isAssignment(math) and not _isVector(math) :
        try:return eval(math),None
        except Exception:pass
    if variableDict.get(math)is not None :return variableDict[math],math
    elif functions.get(math) is not None: return functions[math]['value'],math
    elif math.isalnum() and not math.isdigit() and 'T' not in math  :raise RuntimeError(f'Error: {math} is not initialized ')
    parsed=math
    result=None
    if _hasBrackets(math)[0] and not _isFunctionCall(math) and isVectorBinOperation(math)[0]==-1 and not _isAssignment(math):
        tmp=_hasBrackets(math)
        i1=tmp[1]
        i2=tmp[2]

        unbracked=mathParser(math[i1+1:i2])[0]
        return unbracked,None
    if ',' in math:
        li=math.split(',')
        return tuple([mathParser(i)[0] for i in li ]),None
    if _isFunction(math):
        exp=parseFunction(math)
        return exp,None

    if _rank(math)[0]:
        tmp=_rank(math)
        #print(tmp)
        #breakpoint()
        exp=mathParser(math[tmp[1]+1:tmp[2]])[0]
        return np.linalg.matrix_rank(exp),None
    if _isAssignment(math):
        index=math.find('=')
        result=mathParser(math[index+1:])[0]
        var=math[:index].strip()
        if ',' not in var:
            variableDict[var]=result
        else:
            li=var.split(',')
            for index,i in enumerate(li):variableDict[i]=result[index]
        return result,var
    elif isVectorBinOperation(math)[0]!=-1:
        #print('True')
        operatorIndex=isVectorBinOperation(math)
        noTuple = len(operatorIndex) == 1

        leftOperand = mathParser(math[:operatorIndex[0]].strip())[0]
        #print(leftOperand,'lp')
        rightOperand=mathParser(math[operatorIndex[0]+1:].strip())[0]  if noTuple else mathParser(math[operatorIndex[1]+1:].strip())[0]
        #print(rightOperand,'rp')
        binOp=binOperationDict.get(math[operatorIndex[0]]) if noTuple else binOperationDict.get(math[slice(operatorIndex[0],operatorIndex[1]+1)])
        #print(f'{leftOperand}:{type(leftOperand)}   {rightOperand}: {type(rightOperand)}')
        result=binOp(leftOperand,rightOperand) if leftOperand!='' else binOp(0,rightOperand)
        #print('143')
        return result,None
    elif _isFunctionCall(math):           # f(g(3))
        #print('functionCall',math)
        lBracket=math.find('(')
        rBracket=math.rfind(')')
        innerExp=math[lBracket+1:rBracket]
        #print(innerExp,'innerExp')
        innerValue=str(mathParser(innerExp)[0])
        value=getValue(math.replace(innerExp,innerValue))
        return value[0],value[1]
    elif _isVector(math) :

        index1=math.find('[')
        index2=math.rfind(']')
        math=f"[{math[index1+1:index2].strip()}]"
        parsed=f"np.array({math.replace(' '*math.count(' '), ',')})"

    elif _is2DArr(math)  :
        parsed = f"np.array([{math.replace('; ', '],[').replace(';', '],[').replace(' ', ',')}])"


    if result is None:
        #print(parsed,'parsed')
        result=eval(parsed)
    #print(f"{type(result)}: {result}")
    return result,None

print('---parsing---')

#print(mathParser(''))
t='f(3)'

#mathParser('f:index->index+2')
#print(functions)

#print(_isFunctionCall(t))


