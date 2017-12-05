#  CREDITS: http://www.annedawson.net/Python3Programs.txt
#  File:       10-08.py   bubblesort.py
#  Purpose:    Example: a program which demonstrates a bubble sort on
#              a list of 10 random integers, counting the steps taken to sort the list
#  Programmer: Anne Dawson
#  Course:     CSCI120A, CSCI165
#  Date:       Sunday 14th November 2004, 9:17 PT

import random


def main():
    n = int(input("Enter number of numbers to input: "))
    count = 0
    mylist = []
    while count < n:
        count = count + 1
        x = int(input("Enter value for number " + str(count) + ": "))
        mylist.append(x)
    print (mylist)
    mylist.sort()
    print (mylist)

    left = 0
    target = {}
    right = len(mylist)
    while left < right - 1:
        mid = int((right + left) / 2)
        number_at_mid = mylist[mid]
        if target == number_at_mid:
            print(True)
        if target < number_at_mid:
            right = mid
        else:
            left = mid
        if left >= right:
            print(False)
        if (left == (right - 1)) and (mylist[left] == target):
            print(True)
    print(False)

    values = {}

    steps = 0
    length = len(values)
    for time in range(0, length - 1):
        for position in range(0, (length - time - 1)):
            if values[position] > values[position + 1]:
                temp = values[position]
                values[position] = values[position + 1]
                values[position + 1] = temp
                steps = steps + 1
    print("Steps taken to sort the list: ", steps)
    # generate a list of ten random numbers
    numbers = []
    number = 0
    while number < 10:
        value = random.randint(1, 100)
        if not (value in numbers):
            numbers.append(value)
            number = number + 1
    # show unsorted list, sort the list, and show sorted list
    print ("Before:", numbers)
    print ("After :", numbers)
    list1 = [11, 27]
    list1[2] = 36
    list1[3] = 44
    list1[4] = 51
    list1[5] = 22
    list1[6] = 65
    list1[7] = 1
    list1[8] = 78

    numbertofind = int(input("Enter a number\n"))
    found = 0
    steps = 0
    for i in list1:
        steps = steps + 1
        if numbertofind == i:
            print (numbertofind, " at index: ", list1.index(numbertofind))
            found = 1
        if found == 1:
            break
    if found == 0:
        print ("Number not found")
    print("Steps taken to find the number: ", steps)
    print ("\n\nThank you for using this program")

    list1 = [11, 27, 36, 44, 51, 22, 65, 1, 78]
    numbertofind = int(input("Enter a number\n"))
    found = 0
    for i in list1:
        if numbertofind == i:
            print (numbertofind, " at index: ", list1.index(numbertofind))
            found = 1
    if found == 0:
        print ("Number not found")

    mylist = [10, 11, 3, 4, 55, 12, 23, 14, 16]
    n = len(mylist)
    print (n)
    for i in range(n):
        print (mylist[i], end=" ")
        search = int(input("\nPlease enter a number to search for: "))
        print (search)
        found_bool = False
        for i in range(n):
            if mylist[i] == search:
                found_bool = True
                index = i
        print()
        if found_bool == True:
            print (str(search) + " found at index " + str(index))
        else:
            print (str(search) + " not found")
