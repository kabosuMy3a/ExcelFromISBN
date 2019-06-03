package edu.handong.kabosuMy3a.utils ;

import edu.handong.kabosuMy3a.utils.datamodel.bookInfo;
import edu.handong.kabosuMy3a.utils.useful.Utils ;

import java.util.*;
import java.io.*;

import org.apache.commons.cli.*;


public class kabosuBookDataMaker{
	
	//for convert excel file
	private HashMap<String,bookInfo> infoByTitle ; 
	private HashMap<String,bookInfo> infoByISBN ;
	//from input File
	private ArrayList<String> titlelist ;
	private ArrayList<String> ISBNlist ;
	//for Deletion
	private String tempTitle ;
	private String tempISBN ;
	//for Apache CLI
	private String resultPath ;	
	private boolean cliMenu ;
	private String titlePath;
	private String ISBNPath;
	private boolean help;


	public void run(String[] args){

		Options options = createOptions();

		if(parseOptions(options,args)){
			if(help){
				printHelp(options);
				return;
			}	
		}else return;

		infoByTitle = new HashMap<String,bookInfo>();
		infoByISBN = new HashMap<String,bookInfo>();

		/* if you use getLines, exception handling doesn't be required.
		* Exception handling was implemented in getLines.
		*/
		/*
		if(titlePath != null){
			titlelist = Utils.getLines(titlePath);
			
			//run title search
			 
		}*/
		

		if(ISBNPath != null){
			ISBNlist = Utils.getLines(ISBNPath) ;
		       /*
			*  run ISBN search
			*/	
		}		
		
		if(cliMenu){	
			try{
				searchWithCLI();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}


	private void searchWithCLI() throws Exception {

		Scanner keyboard = new Scanner(System.in);
		String command;
		String partition ="--------------------------------------------";
		int boxnumber = 0 ;

		System.out.println("Input ISBN or \"/t Title\" you want find");
		System.out.println(partition);
		System.out.println("If you want help, input \"/help\"");
		while(true){
			
			command= keyboard.nextLine();

			if(command.equals("/help")){
				System.out.println(partition);
				System.out.println("Input ISBN code to find book Info");
				System.out.println("\"/t <Book Title>\" : book info by Title Search");
				System.out.println("\"/d\" : delete book Info you find just now");
				System.out.println("\"/delete all\" : clear all book Info");
				System.out.println("\"/save\" : save bookInfo to excel file");
				System.out.println("\"/set boxnumber <num>\" : set boxnumber in bookInfo");
				System.out.println("\"/show\" : show bookInfo you found");
				System.out.println("\"/quit\" : save and quit");
				System.out.println("\"q!\" : don't save and quit");
				System.out.println(partition);

			}
			
			else if(command.equals("q!")) break;
			else if(command.equals("/quit")){
				//save();
				break;
			}
			else if(command.equals("/d")){
			
			}
			else if(command.equals("/delete all")){
				infoByTitle.clear();
				infoByISBN.clear() ;
				System.out.println("BookInfo deleted");
			}
			else if(command.equals("/save")){

			}
			else if(command.equals("/show")){
				for(String line : infoByTitle.keySet()){
					//System.out.println(line+"|| "+infoByTitle.get(line).show());
				}
				for(String line : infoByISBN.keySet()){
					//System.out.println(line+"|| "+infoByISBN.get(line).show());
				}
				
			}
			else if(command.indexOf("/set boxnumber ")==0){
				int index = command.indexOf(" ",5)+1 ;
				boxnumber = Integer.parseInt(command.substring(index));
				System.out.println("boxnumber set by "+command.substring(index));
			}
			else if(command.indexOf("/t ")==0){
				int index = command.indexOf(" ")+1 ;
				String titleForSearch = command.substring(index) ;
				/*
				 * search();
				 */
			}
			else{
				String ISBNForSearch = command ;
				/*
				 * search();
				 */
			}

		}
	}


	private Options createOptions(){

		Options options = new Options();
		
		//only required
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		//for function1 
		options.addOption(Option.builder("c").longOpt("cli")
			        .desc("Search by CLI menu")	
			        .build());

		//for function2
		/*
		options.addOption(Option.builder("t").longOpt("title")
				.desc("Search by book title text file")
				.hasArg()
				.argName("Title text file Path")
				.build());
		*/

		options.addOption(Option.builder("I").longOpt("ISBN")
				.desc("Search by book ISBN text file")
				.hasArg()
				.argName("ISBN text file Path")
				.build());
		
		//help
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				.build());
		
		return options ;
	}

	private void printHelp(Options options){

		HelpFormatter formatter = new HelpFormatter();
		String header = "KabosuMy3a Book Data Search & Save";
		String footer = "github.com/kabosuMy3a/kabosuBookDataMaker";
		
		formatter.printHelp("KabosuBookDataMaker", header, options, footer, true);
	}

	private boolean parseOptions(Options options, String[] args){


		CommandLineParser parser = new DefaultParser();

		try{

			CommandLine cmd = parser.parse(options,args);

			resultPath = cmd.getOptionValue("o");
			cliMenu = cmd.hasOption("c");
			//titlePath = cmd.getOptionValue("t");
			ISBNPath = cmd.getOptionValue("I");
			help = cmd.hasOption("h");
					

		} catch(Exception e){
			
			printHelp(options);
			return false;
		}

		return true;
	}
}
