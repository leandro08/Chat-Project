#! /usr/bin/python
# -*- coding: utf-8 -*-

""" ---Encryption Program--- Version 2.0
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
import time

#Compilation Passwords.
password= str(raw_input("Introducir contraseña de cifrado: "))
count=0

while len(password)==0: #Insistent Loop Arguments To Passwords
    print()
    print("No se ha introducido ningún valor, inténtelo de nuevo")
    print()
    password= str(raw_input("Introducir contraseña de cifrado: "))
    count += 1
    if count == 1 and len(password)== 0:
        print("")
        raise SystemExit()

def pswdata(n): #Password And Data Function.
    val= str("")
    for i in n:
        a= str(ord(i))
        val= val + a
    return val
val1=format(int(pswdata(password)), "x")
val2= int(val1,16)

#Data Collection.
digit= str(raw_input("Introducir dígitos a cifrar: "))
counter=0

while len(digit)==0: #Insistent Loop Arguments To Data
    print()
    print("No se ha introducido ningún valor, inténtelo de nuevo")
    print()
    digit= str(raw_input("Introducir dígitos a cifrar: "))
    counter += 1
    if counter == 1 and len(digit)==0:
        print("")
        raise SystemExit()

digit= pswdata(digit) #Call To The Above Function.

#Time Collection.
time= time.localtime()
day= time[2]
hour= time[3]
minutes= time[4]
week_day= time[6]

#Index Collection and Time Operator.
r= random.randint(2,20)
def operator(digit):
    values= str((int(digit) * (hour + 2) * (minutes + 1)) + (day*r))
    return values
code= operator(digit)

#This Code Processes The First Part Of The Encryption.
code= list(code)
firm=["Kf0","4ez","Dze","09D","Af1","4xO","Df5","mdX","19A",
"Jr4","vB2","xZd","30F","gG1","sAQ","FDa","0PQ","dF0",
"ef0","Ac6","57X","uVk","EQA","0IV","QWY","Tp2","15N",
"Rd0","def","LiD"]

a= random.choice([6,7,8])
b= random.choice(firm)
code.insert(0, str(r) + b + ":" + str((week_day + 1)* 14) + str(hour + 19)
+ str(minutes * 3)+":")
code.append(":" + str(day + (week_day * a)) + str(a*17))

def outs(n): #Output Function Of Code.
    val3= str("")
    for i in n:
        val3= val3 + i
    return val3
out= outs(code)

#This Code Processes The Second Part Of The Encryption.
out= out.split(":")
output= int(out[2])
output1= int(out[1]+ out[3]) **2

if val2 % 2 == 0: #Some Conditions To Collect And Add Data.
    t= val2/2
elif val2 % 3 == 0:
    t= val2/3
else:
    t= val2

val4= (((output + output1) + (val2 * output1)) * day) + ((t * week_day)+1)

#This Code Processes The Finally Part Of The Encryption.
out[2]=str(val4)
he= format(int(out[1]),"x")
he1= format(int(out[2]),"x")
he2= format(int(out[3]),"x")
codeout= [he+":", he1+":", he2]
codeout.insert(0, format(r+8,"x")+ b + ":")

codefnly= outs(codeout) #Calling Output Function Of Code.
print("Código cifrado: " + codefnly)
