package edu.handong.kabosuMy3a.utils.datamodel ;

import java.util.* ;



public class bookInfo{


	private String title = null ;
	private String author = null ;
	private String publisher = null ;
	private int year = 0x0 ;
	private String ISBN = null ;
	private String price = null ;
	private int boxnumber = 0x0;

	private ArrayList<String> bookInfoToString ;
	
	public bookInfo(){
	
		bookInfoToString = new ArrayList<String>();
	}

	public void setTitle(String title){

		this.title = title;
	}
	
	public void setAuthor(String author){
		
		this.author = author;
	}

	public void setPublisher(String publisher){

		this.publisher = publisher ;
	}

	public void setBookInfoToString(){
		
		bookInfoToString = new ArrayList<String>();
		bookInfoToString.add(title);
		bookInfoToString.add(author);
		bookInfoToString.add(publisher);
	}

	public ArrayList<String> getBookInfoToString(){

		return bookInfoToString ;
	}

}

