package edu.handong.kabosuMy3a.utils.useful ;

import java.util.* ;
import java.io.* ;

import edu.handong.kabosuMy3a.utils.datamodel.bookInfo;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;


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

	
	public static void saveWithPOI(ArrayList<bookInfo> searchedList, String targetFileName){
	
		if (searchedList.isEmpty()){
		       	System.out.println("List is empty");
			return;
		}

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		
		int rownum = 0;
		ArrayList<String> rowInput ;
		
		for(bookInfo bI :searchedList){
			
			XSSFRow row = sheet.createRow(rownum++);
			rowInput = bI.getBookInfoToList();
				
			int cellnum = 0;
			for(String cellInput : rowInput){
				XSSFCell cell = row.createCell(cellnum++);
			       	cell.setCellValue(cellInput);
			}	
		}
		try{
			File file = new File(targetFileName) ;  
                        File abFile = file.getAbsoluteFile();
                        if(!abFile.getParentFile().exists()){
                        	abFile.getParentFile().mkdirs();
                        }
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
			System.out.println("Saved in "+targetFileName);
		
		}catch (Exception e){
			e.printStackTrace();
		}

	}	

	public static void writeAFile(ArrayList<String> lines, String targetFileName){

                PrintWriter outputStream = null;

                try{
                        File file = new File(targetFileName) ;  
                        File abFile = file.getAbsoluteFile();

                        if(!abFile.getParentFile().exists()){
                        	abFile.getParentFile().mkdirs();
                        }


                        if(!file.exists()){
                                outputStream = new PrintWriter(targetFileName);
                        }
			
                        else{
                                FileWriter filewriter = new FileWriter(targetFileName, true);
                                outputStream = new PrintWriter(filewriter);

                        }

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
