# Program which checks if the input number is even or odd


def is_even(n):
    if n % 2 == 0:
        return 1
    else:
        return 0


num = raw_input("Enter an integer: ")
res = is_even(int(num))
if res:
    print num + ' is even'
else:
    print num + ' is odd'
