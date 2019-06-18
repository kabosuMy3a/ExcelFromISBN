# kabosuBookDataMaker 

This program will make MS-Office Excel file searched by book ISBN or Title with Naver Search API.  
This program is customized for me, for examples, result data's format of xlsx file.

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
	
	you can use '/t' also if you want to search by book name(e.g. /t 아침에는 죽음을 생각).  
	If you use '/t' program displays 8 most-relative book information.  
	You should choose one of them to save or input 0 if you don't want to save.   

	please input '/help' first, then you can check all cli functions. 


* -I(upper case of i) \<file name> options : input data from textfile

	-I will require textfile which consist of ISBN.  
	Each line is only ISBN code in textfile.  

	Likewise fuction 1, it will save in ArrayList and save it to Excel file.  


### Class Diagram

#### package edu.handong.kabosuMy3a.utils

|Main|kabosuBookDataMaker|searchThread|
|:---|:---|:---
||searchedList<br>ISBNlist<br>resultpath<br>ISBNPath<br>boolean cliMenu<br>boxnumber|keyword<br>bookInfo(custom datamodel)<br>synchronized searchedList|
|main()|run()<br>function -c()<br>function -i()<br>saveWithPOI()<br>forApacheCLI()|run()<br>searchWithAPI()<br>XMLparsingBookInfo()|
|just implements run|implements and run threads|thread will do bothering job :)|

##### Datamodel
|bookInfo|
|:---
|title<br>author<br>publisher<br>pubdate<br>ISBN<br>price<br>boxnumber<br>bookInfoToList<br>|
|setter&getter()<br>toString()<br>setter&getter BookInfoToList()|


##### collections  
|LinkedStack\<T>|
|:---
|class ListNode\<T>(innerClass)<br>top<br>count<br>|
|LinkedStack()<br>push(T)<br>T pop() <br> boolean isEmpty()|

##### useful
|searchFailedException|utils|
|:---|:----
|||
|searchFailedException()<br>searchFailedException(String)|getLines()<br>savedWithPOI()<br>writeAfile()|

---
더 해야할 것

~README.md /help 기능 정리~    
~first line~
~price format~  
~***thread & url error***~  
~custom exception~  
~ISBN 둘다 있으면 하나만~  
~generics~(reverse를 위한 LinkedStack을 generics로 구현했다.)  
~/save csv~

---
에러:

distribuiton으로 만든 bat파일로 실행할 때 (window CMD창에서)  
리눅스에서는 모두 정상적으로 돌아감.  


1. thread가 높은 확률로 접속이 안됨  
2. 책 검색 결과가 한글일 때 깨짐...   
3. 윈도우에서 왜 많은 기능이 안되는지 이해가 안됨 ..(내가 생각하는 가장 유력한 근거는 UTF-8관련 cmd에러) 
