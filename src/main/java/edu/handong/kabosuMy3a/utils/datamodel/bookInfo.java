package edu.handong.kabosuMy3a.utils.datamodel ;

import java.util.* ;
import java.text.NumberFormat;


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

	public bookInfo(String title){

		bookInfoToList = new ArrayList<String>();
		this.title = title;
	}

	public void setTitle(String title){
	
		title = title.replaceAll("\\<[^>]*>","");		

		this.title = title;
	}
	
	public void setAuthor(String author){
	
		author = author.replaceAll("\\<[^>]*>","");	
		this.author = author;
	}

	public void pushAuthor(String author){
		this.author = author;
	}

	public void setPublisher(String publisher){

		this.publisher = publisher ;
	}

	public void pushPublisher(String publisher){
		this.publisher = publisher;
	}

	public void setPubDate(String pubDate){	
		if(pubDate != null && pubDate.length() > 4)
			pubDate = pubDate.substring(0,4);
		this.pubDate = pubDate ; 

	}

	public void pushPubDate(String pubDate){
		this.pubDate = pubDate;
	}

	public void setISBN(String ISBN){

		int space = ISBN.indexOf(" ");
		if (space > 0 && ISBN.charAt(space+1)=='9'){
			ISBN = ISBN.substring(space+1);
		}
		else if(space > 0){
			ISBN = ISBN.substring(0,space);
		}
		this.ISBN = ISBN;

	}

	public void pushISBN(String ISBN){
		this.ISBN = ISBN;
	}
	
	public void setPrice(String price){
		
		
		try{
			int priceInt = Integer.parseInt(price);
			NumberFormat nf = NumberFormat.getInstance(new Locale("ko","KR"));
			this.price = nf.format(priceInt);
		}catch (Exception e){
			this.price = price ;
		}

	}

	public void pushPrice(String price){
		this.price = price;
	}


	public void setBoxNumber(int boxnumber){
		this.boxnumber = boxnumber;
	}	

	private void setBookInfoToList(){
		
		bookInfoToList = new ArrayList<String>();
		bookInfoToList.add(null);//No.
		bookInfoToList.add(title);
		bookInfoToList.add(author);
		bookInfoToList.add(publisher);
		bookInfoToList.add(null);//EA
		bookInfoToList.add(pubDate);
		bookInfoToList.add(ISBN);
		bookInfoToList.add(null);//Donator
		bookInfoToList.add(null);//Donate-date
		bookInfoToList.add(price);
		bookInfoToList.add(Integer.toString(boxnumber));
		bookInfoToList.add(null);//etc
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

