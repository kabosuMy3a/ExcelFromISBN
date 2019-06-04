package edu.handong.kabosuMy3a.utils.datamodel ;

import java.util.* ;



public class bookInfo{


	private String title = null ;
	private String author = null ;
	private String publisher = null ;
	private String pubDate = null ;
	private String ISBN = null ;
	private String price = null ;
	private int boxnumber = 0x0;
	private String etc = null;
	private ArrayList<String> bookInfoToString ;
	

	public bookInfo(){
	
		bookInfoToString = new ArrayList<String>();
	}

	public void setTitle(String title){
	
		title = title.replaceAll("\\<[^>]*>","");		

		this.title = title;
	}
	
	public void setAuthor(String author){
	
		author = author.replaceAll("\\<[^>]*>","");	
		this.author = author;
	}

	public void setPublisher(String publisher){

		this.publisher = publisher ;
	}

	public void setPubDate(String pubDate){

		this.pubDate = pubDate ; 
	}

	public void setISBN(String ISBN){

		this.ISBN = ISBN;
	}
	
	public void setPrice(String price){
		
		this.price = price ;
	}

	public void setBoxNumber(int boxnumber){
		this.boxnumber = boxnumber;
	}
	/*
	public void setBookInfoToList(){
		
		bookInfoToString = new ArrayList<String>();
		bookInfoToString.add(title);
		bookInfoToString.add(author);
		bookInfoToString.add(publisher);
	}

	public ArrayList<String> getBookInfoToList(){

		return bookInfoToString ;
	}*/
	
	@Override
	public String toString(){
		
		return title+"/"+author+"/"+publisher+"/"
			+pubDate+"/"+ISBN+"/"+price+"/"
			+Integer.toString(boxnumber)+"/"+etc ;
	}

}

