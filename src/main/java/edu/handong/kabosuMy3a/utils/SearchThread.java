package edu.handong.kabosuMy3a.utils ;

import java.util.*;
import java.net.*;
import java.io.*;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory ;

import edu.handong.kabosuMy3a.utils.datamodel.bookInfo ;

public class SearchThread implements Runnable{


	private static String clientID = "mzPPplh7Z4KjCfYoj6Ve";
	private static String clientSecret = "bjFwkbK7Ay";
	
	private ArrayList<bookInfo> infoList ; 
	String keyword ; 
	int option = 0 ; 


	public SearchThread(String keyword, int option){
		
		//option 0 : default(ISBN)
		//option 1 : Title Search
		//option 2 : ISBN Search 
		this.option = option;
		this.keyword = keyword;
		infoList = null ;
	}


	@Override
	public void run(){

		searching();
		for(bookInfo b : infoList){
			
			System.out.println(b.toString());
		}
		return ;
	}

	public void searching(){

		URL url ;
		int start = 0;
		int display = 8;

		try{
	    		if(option == 0 || option ==2){
				url = new URL("https://openapi.naver.com/v1/search/book_adv.xml"
						+"?d_isbn="+ URLEncoder.encode(keyword, "UTF-8"));
			}else{
				url = new URL("https://openapi.naver.com/v1/search/book.xml?query="
						+ URLEncoder.encode(keyword, "UTF-8")
						+ (display != 0 ? "&display=" + display : "") 
						+ (start != 0 ? "&start=" + start : ""));
			}
			URLConnection URLcon = url.openConnection();
			URLcon.setRequestProperty("X-naver-Client-Id", clientID);
			URLcon.setRequestProperty("X-naver-Client-Secret", clientSecret);
				
			XmlPullParserFactory factory; 
           		factory = XmlPullParserFactory.newInstance();
            		XmlPullParser parser = factory.newPullParser();
			parser.setInput(new InputStreamReader(URLcon.getInputStream()));

			int eventType = parser.getEventType();
			bookInfo bI = null ;

			while(eventType != XmlPullParser.END_DOCUMENT){

				if (eventType == XmlPullParser.START_DOCUMENT){
					infoList = new ArrayList<bookInfo>();
				}
				else if(eventType == XmlPullParser.START_TAG){
					String tag = parser.getName();
					
					switch (tag){
					
					case "item" : 
						bI = new bookInfo();
						break;
					
					case "title" :
						if(bI != null) bI.setTitle(parser.nextText());							      break;
					
					case "author" :
						if(bI != null) bI.setAuthor(parser.nextText());
						break;
					
					case "price" :
						if(bI != null) bI.setPrice(parser.nextText());
						break;

					case "publisher" :
						if(bI != null) bI.setPublisher(parser.nextText());
						break;
					
					case "isbn" :
						if(bI != null) bI.setISBN(parser.nextText());
						break;

					case "pubdate" :
						if(bI != null) bI.setPubDate(parser.nextText());
						break;
					}

				}
				else if(eventType == XmlPullParser.END_TAG){
					String tag = parser.getName();
					if (tag.equals("item")){
						infoList.add(bI);
						bI = null;
					}
				}
				
				eventType = parser.next();
			}
			
	    	   }catch(XmlPullParserException e){
			   e.printStackTrace();
	    	   
		   }catch(Exception e){
			
			   e.printStackTrace();
		   }
	
	}
}
