
'''
Given a list in variable `x`, write a one-line Python generator expression that returns only the elements from x that
are odd integers or have an even (zero-based) index in `x`. The given list may contain items other than numbers.
The solution must be a generator expression.
'''
def practice_generators():
    x = [0,1,"A",2,9,10,11,12,34,5453,"23q23rsdf", "asdfasdf"]
    return([b for b in x if type(b)== int and b%2==1 or x.index(b)%2==0 ])

if __name__ == "__main__":
    result = practice_generators()
    print result


