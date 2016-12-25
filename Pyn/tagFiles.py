import os
import fileinput,re  
def reTag():
    files = [f for f in os.listdir('.') if os.path.isfile(f) and '.py' in f]
    for f in files:
        t = os.path.splitext(f)
        mdname = t[0].split("[")
        ouf = open(mdname[0]+'.md','a+')
        content = ouf.readlines()
        ouf.seek(0,0)
        s = ''
        try:
            i = content.index('[tag]\n')
        except:
            ouf.write('[tag]\n')
        i = content.index('[tag]\n')   
        for t1 in mdname[1:]:
            s = s + '['+t1 +'\n'
        content.insert(i+1, '{}\n'.format(s))
        ouf.write(''.join(content))
        ouf.close()

reTag()
