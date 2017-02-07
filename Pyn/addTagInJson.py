import fileinput,re  
import sys
def  addJsonTag(file_name,pattern,value=""):  
    fh=fileinput.input(file_name,inplace=True)  
    for line in fh:  
        replacement='{"tags":[' +  '{"title":'+value+',"type":"sidecar","style":"color: #ffffff !important; background-color: #4986e7 !important;"},'
        line=re.sub(pattern,replacement,line)  
        sys.stdout.write(line)  
    fh.close()  

pattern = r'\s{"tags":[\s'
addJsonTag('/Users/zhitaoq/Documents/AGPractice/Pyn/.ts/wiggleSequence.py.json', pattern, '5')