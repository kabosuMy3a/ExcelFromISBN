# kabosuBookDataMaker 

This program will make MS-Office Excel file searched by book ISBN or Title with Naver Search API.


### Step

1. searching Book information with Naver API  
2. parsing data with XML parser (Naver API gives data with XML or JSON format, but detail search applies only XML)  
3. Save to EXCEL with Apache POI  


### Functions

With Apache commons cli, this program can choose functions you want use.  
this program requires -o \<outputfile> file path for saving output file.  

Map\<String ISBN, bookInfo>
Map\<String title, bookInfo>


* -e options: input data from keyboard(Scanner) 

	If you activate -e you can input ISBN code in cli.   
	After that this program's threads will find bookInfo from naverAPI and printout in cli.  
	Then, threads save bookInfo in hashMap. If title is null || author is null don't save.  
	you can use '/b'if you want to search by book name(e.g. /b 아침에는 죽음을 생각하는 것이 좋다).  
	'/d' will delete bookInfo which you find just now. '/delete all' will clear all bookInfo from hashMap.   
	With '/boxnumber \<number>' you can set box number for arrange.  
	if you want make hashmap to Excel file or csv file '/save' or '/quit'.  


* -t or -i options : input data from textfile

	-t will require textfile which consist of book title.  
	-i will require textfile which consist of ISBN.  

	Likewise fuction 1, it will save in HashMap and save it to Excel file or csv file.  
	If program can't find bookInfo, it will stack in errorlog.txt  


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
|customException|utils|
|:---|:----
|||
|customException()<br>customException(String)|getline()<br>writeAfile()|

---
더 해야할 것

README.md /help 기능 정리  
첫 줄 옵션  
price format  
***thread & url error***  
custom exception  
ISBN 둘다 있으면 하나만  
