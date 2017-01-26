#! /usr/bin/python
# -*- coding: utf-8 -*-
""" ---Decryption Program--- Version 2.0
    Copyright (C) 2016  Leandro Llano Sánchez

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>."""

#Modules And Libraries Used In The Program
from __future__ import print_function
import random
import re

#Compilation Passwords.
password= str(raw_input("Introducir contraseña de descifrado: "))
count=0

while len(password)==0: #Insistent Loop Arguments To Passwords
    print()
    print("No se ha introducido ningún valor, inténtelo de nuevo")
    print()
    password= str(raw_input("Introducir contraseña de descifrado: "))
    count += 1
    if count == 1 and len(password)== 0:
        print("")
        raise SystemExit()

#Data Collection.
code= str(raw_input("Introducir código a descifrar: "))
counter=0

while len(code)==0: #Insistent Loop Arguments To Data
    print()
    print("No se ha introducido ningún valor, inténtelo de nuevo")
    print()
    code= str(raw_input("Introducir código a descifrar: "))
    counter += 1
    if counter == 1 and len(code)==0:
        print("")
        raise SystemExit()


def pswdata(n): #This Function Processes the Passwords.
    val= str("")
    for i in n:
        a= str(ord(i))
        val= val + a
    return val
val1= int(pswdata(password))

if val1 % 2 == 0: #Some Conditions To Collect And Add Data.
    t= val1 / 2
elif val1 % 3 == 0:
    t=val1 /3
else:
    t= val1

#Breakdown Of List And Data Extraction.
code= code.split(":")
index1= str(int(code[1],16))
index2= int(code[2],16)
index3= str(int(code[3],16))
output1= int(index1+ index3)**2

#Time Collection.
time= index1
hour= int(time[2:4])-19
minutes= int(time[4:])/3
week_day= (int(time[0:2])-1)/14
day= index3

#Conditions To Get the Day.
if len(day)== 4:
    a= int(day[1:])/17
    day= int(day[0]) - (week_day * a)

elif len(day)== 5:
    a= int(day[2:])/17
    day= int(day[0:2]) - (week_day * a)

# This Variable Is A Firm That Checks The Encryption Code.
firm= ["Kf0","4ez","Dze","09D","Af1","4xO","Df5","mdX","19A",
"Jr4","vB2","xZd","30F","gG1","sAQ","FDa","0PQ","dF0",
"ef0","Ac6","57X","uVk","EQA","0IV","QWY","Tp2","15N",
"Rd0","def","LiD"]

#These Conditions Get a Random Number, Inherited In the Encryption Code.
if len(code[0]) == 4:
    r= int(code[0][0:1],16)
    if code[0][1:] not in firm: #Here The Firm Is Checks.
        print("---Código de cifrado corrupto---")
        raise SystemExit()

elif len(code[0]) == 5:
    r= int(code[0][0:2],16)
    if code[0][2:] not in firm: #Here The Firm Is Checks.
        print("---Código de cifrado corrupto---")
        raise SystemExit()
b=int(r-8)

#These Operation Returns The Digit Encryted In Numbers.
output= (index2 - ((t * week_day)+1))/day - ((val1*output1) + output1)
val= (str((output - (day*b)) / ((hour + 2) * (minutes + 1))))
lenght= len(val)

#Indexes Control Variables
ai=0
bi=2

codex= str()
while bi != lenght+2: #This Loop Ends The Decryption.
    ci= int(val[ai:bi])
    if ci >= 32:
        char= chr(ci)
        ai= bi
        bi= bi+ 2
        codex= codex + str(char)
    else:
        bi += 1
    continue
print("Código descifrado:", codex)
print("Day ",day," At " ,hour,":",minutes, sep="")
