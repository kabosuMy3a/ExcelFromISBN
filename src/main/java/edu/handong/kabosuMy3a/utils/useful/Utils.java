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
	

	public static void writeAFile(ArrayList<String> lines, String targetFileName){

                PrintWriter outputStream = null;

                try{
                        File file = new File(targetFileName) ;  
                        File abFile = file.getAbsoluteFile();

                        if(!abFile.getParentFile().exists()){
                                        abFile.getParentFile().mkdirs();
                        }


                        //if(!file.exists()){
                                outputStream = new PrintWriter(targetFileName);
                        //}
			/*
                        else{
                                FileWriter filewriter = new FileWriter(targetFileName, true);
                                outputStream = new PrintWriter(filewriter);

                        }*/

                }

                catch(FileNotFoundException e){

                        System.out.println("The file path does not exist.");
                        System.exit(0); 
                }
                
                catch(Exception e){
                        System.out.println(e) ;
                        System.out.println("Error opening the file " + targetFileName);
                        System.exit(0);
                }

                for(String line : lines){
        
                        outputStream.println(line) ;

                }

                outputStream.close();
                //System.out.println("File Writtened Successfully");
        }
	
}
