# Program which checks if the input number is even or odd


def is_even(n):
    if n % 2 == 0:
        return 1
    else:
        return 0


x = raw_input("Enter an integer: ")
res = is_even(int(x))
if res:
    print x + ' is even'
else:
    print x + ' is odd'
