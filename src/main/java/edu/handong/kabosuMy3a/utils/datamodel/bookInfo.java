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
	private String etc = "";
	private ArrayList<String> bookInfoToList ;
	

	public bookInfo(){
	
		bookInfoToList= new ArrayList<String>();
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
		pubDate = pubDate.substring(0,4);
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
	
	private void setBookInfoToList(){
		
		bookInfoToList = new ArrayList<String>();
		bookInfoToList.add(null);
		bookInfoToList.add(title);
		bookInfoToList.add(author);
		bookInfoToList.add(publisher);
		bookInfoToList.add(null);
		bookInfoToList.add(pubDate);
		bookInfoToList.add(ISBN);
		bookInfoToList.add(null);
		bookInfoToList.add(null);
		bookInfoToList.add(price);
		bookInfoToList.add(Integer.toString(boxnumber));
		//bookInfoToList.add(etc);
	}

	public ArrayList<String> getBookInfoToList(){
                setBookInfoToList();
		return bookInfoToList ;
	}
	
	@Override
	public String toString(){
		
		return title+"/"+author+"/"+publisher+"/"
			+pubDate+"/"+ISBN+"/"+price+"/"
			+Integer.toString(boxnumber) ;
	}

}

