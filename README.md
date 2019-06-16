# kabosuBookDataMaker 

This program will make MS-Office Excel file searched by book ISBN or Title with Naver Search API.


### Step

1. searching Book information with Naver API  
2. parsing data with XML parser (Naver API gives data with XML or JSON format, but detail search applies only XML)  
3. Save to EXCEL with Apache POI  


### Functions

With Apache commons cli, this program can choose functions you want use.  
this program requires -o \<outputfile> file path for saving output file.  
#### Should input -o \<\*.xlsx> format.


* -c options: input data from keyboard(Scanner) 

	If you activate -c you can input ISBN code in cli.   
	After that this program's threads will find bookInfo from naverAPI and printout in cli.  
	Then, threads save bookInfo in ArrayList.	
	you can use '/t' also if you want to search by book name(e.g. /t 아침에는 죽음을 생각하는 것이 좋다).  
	
	please input '/help' first, then you can check all cli functions. 


* -I \<file name> options : input data from textfile

	-I will require textfile which consist of ISBN.  
	Each line is only ISBN code in textfile.  

	Likewise fuction 1, it will save in ArrayList and save it to Excel file.  


### Class Diagram

#### package edu.handong.kabosuMy3a.utils

|Main|kabosuBookDataMaker|searchThread|
|:---|:---|:---
||HM\<ISBN,bookInfo><br>HM\<title,bookInfo><br>tempISBN,tempTitle<br>|ISBN<br>title<br>bookInfo(custom datamodel)<br>hashMap1(ISBN),2(title)\<synchronized|
|main()|run()<br>function -e()<br>function -t or -i()<br>saveWithPOI()<br>forApacheCLI()|run()<br>int titleOrISBN()<br>searchWithAPI()<br>XMLparsingBookInfo()|
|just implements run|implements and run threads|thread will do bothering job :)|

##### Datamodel
|bookInfo|
|:---
|title<br>author<br>company<br>year<br>ISBN<br>price<br>boxnumber<br>|
||

##### useful
|searchFailedException|utils|
|:---|:----
|||
|customException()<br>customException(String)|getlines()<br>savedWithPOI()<br>writeAfile()|

---
더 해야할 것

~README.md /help 기능 정리~  
첫 줄 옵션  
price format  
~***thread & url error***~  
~custom exception~  
ISBN 둘다 있으면 하나만 
generics
~/save csv~

---
에러:

distribuiton으로 만든 bat파일로 실행할 때 (window CMD창에서)  
리눅스에서는 모두 정상적으로 돌아감.  


1. thread가 높은 확률로 접속이 안됨  
2. 책 검색 결과가 한글일 때 깨짐...  
~3. jar로 실행할 때 MainClass를 못찾는 문제(리눅스에서도)~ build.gradle 세팅으로 해결
 
