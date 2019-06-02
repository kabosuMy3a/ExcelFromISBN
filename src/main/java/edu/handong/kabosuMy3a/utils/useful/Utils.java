package edu.handong.kabosuMy3a.utils.useful ;

import java.util.* ;
import java.io.* ;

public class Utils{



	public static ArrayList<String> getLines(String filepath){


		ArrayList<String> extractedList = new ArrayList<String>();
		String line = "";

		try{
			Scanner inputStream = new Scanner(new File(filepath));
			
			while(inputStream.hasNextLine()){
				line = inputStream.nextLine();
				extractedList.add(line);
			}
			inputStream.close();

		} catch(FileNotFoundException e){

			System.out.println("check your input file");
			System.exit(0);

		} catch(Exception e){
			
			e.printStackTrace();
		}
		
		return extractedList ;
	}




}
