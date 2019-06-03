package edu.handong.kabosuMy3a.utils ;

import java.util.*;
import java.net.*;
import java.io.*;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory ;

import edu.handong.kabsoMy3a.utils.datamodel.bookInfo ;

public class SearchThread implements Runnable{


	private static String clientID = "mzPPplh7Z4KjCfYoj6Ve"
	private static String clientSecret = "bjFwkbK7Ay"
	String keyword ; 
	int option = 0 ; 


	public SearchThread(String keyword, int option){
		
		//option 0 : default(ISBN)
		//option 1 : Title Search
		//option 2 : ISBN Search 
		this.option = option;
		this.keyword = keyword;
	}


	@Override
	public void run(){

	}

	public ArrayList<bookInfo> searching(){
		

		URL url ;
		ArrayList<bookInfo> infoList = null;
		
		try{
	    		if(option == 0 || option ==2){

				url = new URL("https://openapi.naver.com/v1/search/book_adv.xml"+"?d_isbn="+ URLEncoder.encode(keyword, "UTF-8"))
			}else{

				url = new URL("https://openapi.naver.com/v1/search/book.xml?query=" + URLEncoder.encode(keyword, "UTF-8")
                    		+ (display != 0 ? "&display=" + display : "") + (start != 0 ? "&start=" + start : ""));
			}
				
			
			XmlPullParserFactory factory;
 
           		factory = XmlPullParserFactory.newInstance();
            		XmlPullParser parser = factory.newPullParser();
	    	   }catch(Exception e){
     	  		System.out.println(e);
	    	   }

	}
}
