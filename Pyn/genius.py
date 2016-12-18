
import os

from sys import *
from datetime import datetime, timedelta
def tagDate(f, dstr):
    t = os.path.splitext(f)
    newname = t[0]+dstr+t[1]
    os.rename(f, newname)
one_days_ago = datetime.now() - timedelta(days=1)
two_days_ago = datetime.now() - timedelta(days=2)
seven_days_ago = datetime.now() - timedelta(days=7)
eight_days_ago = datetime.now() - timedelta(days=8)

twonine_days_ago = datetime.now() - timedelta(days=29)
thirty_days_ago = datetime.now() - timedelta(days=30)

# its win32, maybe there is win64 too?
is_windows = platform.startswith('win')
if not is_windows:
    agpath = r'/mnt/c/Users/zhiqi/Documents/KennyOwn/AGPractice/Pyn'
else:
    agpath = r'C:\\Users\\zhiqi\\Documents\\KennyOwn\\AGPractice\\Pyn'
files = os.listdir(agpath)
files_py = [f for f in files if f[-2:] == 'py']
listOfD1 = []
listOfD2 = []
listOfW1 = []
listOfM1 = []
 
if version_info > (3,0):
    response = input("Finish task now?")
else:
    response = raw_input("Finish task now?")
if response.upper() == "Y":
    for f in files_py:
        filetime = datetime.fromtimestamp(os.path.getctime(f))
        if filetime > one_days_ago:
            listOfD1.append(f)
        elif filetime < one_days_ago and filetime > two_days_ago:
            listOfD2.append(f)
        elif filetime < seven_days_ago and filetime > eight_days_ago:
            listOfW1.append(f)
        elif filetime < twonine_days_ago and filetime > thirty_days_ago:
            listOfM1.append(f)
print("one day ago")
for f in listOfD1:
    print(f)
print("two day ago")
for f in listOfD2:
    print(f)
print("7 day ago")
for f in listOfW1:
    print(f)
map = {0:"[D1]", 1:"[W1]", 2:"[M1]"}            
for i, sublist in enumerate([listOfD1, listOfW1, listOfM1]):
    print("Beginning review files of "+ map[i])
    print()
    for f in sublist:
        if map[i] not in f:
            print(f)
            if version_info > (3,0):
                response = input("Done read file?")
            else:
                response = raw_input("Done read file?")    
            if response.upper() == "Y":
                tagDate(f, map[i])
            elif response.upper() == "S":
                break
 