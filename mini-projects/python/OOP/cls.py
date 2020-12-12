
# helper print func
def Pr(func=str):
    print(f'\n\n---- test {func} ----')

class Test():

    __slots__ = ('name','age','a')   ##### now this class can only have 3 attributes: name && age && a

    def __eq__(self, o: object) -> bool:
        print("I'm equal to him")
        return 1
    
    def __ne__(self, value):
        print("I'm not equal to him")
        return 1

    def __le__(self, value):
        print("I'm less equal to him")
        return 1

    def __ge__(self, value):
        print("i'm greater equal to him")
        return 1

    def __lt__(self, value):
        print("I'm less than him")
        return 1

    def __gt__(self, value):
        print("I'm greater than him")
        return 1

    def __str__(self) -> str:
        return 'this is a string^^'
    
    def __repr__(self):
        return 'this is a representation^^'

    def __len__(self):
        return 66666

    # return an iterator that can be used in for loop etc.
    def __iter__(self):
      self.a = 1
      return self

    # python loops while call next([iterator]) to get next item until StopIteration exception
    def __next__(self):
      if self.a == 10:
          raise StopIteration()
      x = self.a
      self.a += 1
      return x
    
    # subscriptable and slice-able
    def __getitem__(self,n):
        if isinstance(n,int):
            return n
        if isinstance(n,slice):
            return list(range(0 if n.start == None else n.start ,n.stop))
    
    # support item assignment
    def __setitem__(self,key,value):
        # e.g. self.array[key] = value
        pass

    
    # callable
    def __call__(self):
        print("Ur calling me...");
    
    # capble to use 'with': so-called context-manager
    def __enter__(self):
        print('entering...')
        return 'this is the return value of the with statement^^'   #*** the return value of __enter__ is $tmp in "with Test() as tmp:"    
    
    def __exit__(self,exc_type,exc_val,exc_tb):
        print('existing...')
        # no return value required
    
    
    

a = Test()
attrs =dir(a)
for i in attrs:
    print(f'{i}: {getattr(a,i,404)}')

# __eq__
Pr('__eq__')
t1=Test()
t2=Test()
print(t1==t2)

#__ne__
Pr('__ne__')
print(t1!=t2)

# __le__    (less equal,operator: <=)
Pr('__le__')
print(t1<=t2)

# __ge__    (greater equal,operator: >=)
Pr("__ge__")
print(t1>=t2)

# __lt__    
Pr('__lt__')
print(t1<t2)

#__gt__
Pr('__gt__')
print(t1>t2)

# __str__
Pr('__str__')
print(t1)

#__repr__
Pr('__repr__')
print(repr(t1))

# __len__
Pr('__len__')
print(len(t1))

# __iter__ and __next__
Pr('__iter__ && __next__')
for i in t1:
    print(i)

# __getitem__
Pr('__getitem__')
print(t1[4])
print(t2[:3])

# __call__
Pr('__call__')
t1()
print(callable(t1))

# with statement capability
Pr('with statement capability')
with Test() as t3:
    print('do work...')
    print(f'print t3: {t3}')
    print('work done')


#///////////////////////////// use 'attrs', 'cattrs' packages to automate the definitions above /////////////////////////////////////////////////////////////
#* installation: pip(3) install attrs cattrs
#* using this package can automatically implement
    #! __init__, __repr__, __eq__, __ne__, __it__, __le__, __gt__, __ge__, __hash__

# use example:
from attr import attrs, attrib
@attrs
class Color(object):
    r = attrib(type=int,default=0)
    g = attrib(type=int,default=0)
    b = attrib(type=int,default=0)

# testing
print('\n---- test attrs, cattrs ----')
c1 = Color(255,255,255)
print(c1)

#* useful parameters for attrib()
    #! default:                                         default value for the attribute
    #! init = bool                                      set to false if one doesn't want to initialize the attribute
    #! kw_only = bool                                   if true, then this attribute must be passed through key words)
    #! validator = [pre-defined function]
    #! converter = {to_int...}                          force converting the passed-in parameter
    #! type                                             define the type of the attribute

# use example
def _is_positive(instance,attribute,value):
    if value <0:
        raise ValueError('value <0')
def _toInt(value):
    try:
        return int(value)
    except:
        return None
@attrs
class Point(object):
    x = attrib(init = False,default=10)                 #* init = False: typical use case when the class has an attribute whose value is constant
    z = attrib(validator=_is_positive)                  #* now z should can only be positive
    d = attrib(converter=_toInt)                        #* now d is always converted to int
    w = attrib(type=float)
    y = attrib(kw_only=True)                            #* non keyword-only attributes must be before keyword-only attributes






#//////////////////////////////////////// obj <==> json :(de)seriarize /////////////////////////////////////////////////
#from attr import attrs, attrib
from cattr import unstructure, structure

@attrs
class Point2(object):
    x = attrib(default=10,type=int)
    y = attrib(default=20,type=int)

# serialize
print('\n\n---- jasonize ----')
p1 = Point2()
json = unstructure(p1)
print('jason',json)

# deserialize
print('\n\n---- deserialize ----')
obj = structure(json,cl=Point2)
print('obj:',obj)