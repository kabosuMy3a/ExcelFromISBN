package edu.handong.kabosuMy3a.utils ;

import edu.handong.kabosuMy3a.utils.datamodel.bookInfo;
import edu.handong.kabosuMy3a.utils.useful.Utils ;
import edu.handong.kabosuMy3a.utils.collections.LinkedStack;

import java.util.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.cli.*;


public class kabosuBookDataMaker implements CLIOptionable{
	
	//for convert excel file
	private ArrayList<bookInfo> searchedInfo ;
	private LinkedStack<bookInfo> infoInStack ;
	//from input File
	private ArrayList<String> ISBNlist ;
	//for Apache CLI
	private String resultPath ;	
	private boolean cliMenu ;
	private String ISBNPath;
	private boolean help;
	private int boxnumber = 0 ;

	public void run(String[] args){

		Options options = createOptions();

		if(parseOptions(options,args)){
			if(help){
				printHelp(options);
				return;
			}	
		}else return;

		searchedInfo = new ArrayList<bookInfo>();
		infoInStack = new LinkedStack<bookInfo>();

		if(ISBNPath != null){
			int numOfCoresInMyCpu = Runtime.getRuntime().availableProcessors();
			ISBNlist = Utils.getLines(ISBNPath) ;
			
			try{
				searchWithFile(numOfCoresInMyCpu);
			}catch(Exception e){
				e.printStackTrace();
			}
			Utils.saveWithPOI(searchedInfo,resultPath);
		}		
		
		if(cliMenu){	
			try{
				searchWithCLI();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}

	private void searchWithFile(int numOfCoresInMyCpu) throws Exception{
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		ArrayList<Thread> tl = new ArrayList<Thread>();	
		for(String ISBN : ISBNlist){
			if(ISBN == null || ISBN.equals("\n")|| 
					ISBN.equals("\r\n") || ISBN.equals("")) continue; 
			Thread st = new Thread(new SearchThread(searchedInfo,ISBN,2,boxnumber));
			//st.start();
			executor.execute(st);
			st.sleep(175);
			tl.add(st);
			//st.join();
		}
		/*
		for( Thread t : tl){
			t.join();
		}*/
		
		executor.shutdown();
		while(!executor.isTerminated()){
		}
	}

	private void searchWithCLI() throws Exception {

		Scanner keyboard = new Scanner(System.in);
		String command;
		String partition ="--------------------------------------------";

		System.out.println("Input ISBN or \"/t Title\" you want find");
		System.out.println(partition);
		System.out.println("If you want help, input \"/help\"");
		while(true){
				
			command= keyboard.nextLine();

			if(command.equals("/help")){
				System.out.println(partition);
				System.out.println("Input ISBN code to find book Info");
				System.out.println("\"/t <Book Title>\" : book info by Title Search");
				System.out.println("\"/push <Book Title>\" : push book info consists of only title in the list ");
				System.out.println("\"/pau <Author>\" : push Author to Last bookInfo");
				System.out.println("\"/ppu <Publisher>\" : push Author to Last bookInfo");
				System.out.println("\"/pda <Date>\" : push Date to Last bookInfo");
				System.out.println("\"/pis <ISBN>\" : push Author to Last bookInfo");
				System.out.println("\"/ppr <Price>\" : push Author to Last bookInfo");
				System.out.println("\"/d <index>\" : delete book Info by index which you can check with \"/show\"");
				System.out.println("\"/delete all\" : clear all book Info");
				System.out.println("\"/reverse\" : reverse Info order");
				System.out.println("\"/save\" : save bookInfo to excel file");
				System.out.println("\"/save <file name>\" : save bookInfo to <excel file>");
				System.out.println("\"/set boxnumber <num>\" : set boxnumber in bookInfo");
				System.out.println("\"/show\" : show bookInfo you found");
				System.out.println("\"/quit\" : save and quit");
				System.out.println("\"q!\" : don't save and quit");
				System.out.println(partition);

			}
			
			else if(command.equals("q!")) break;
			else if(command.equals("/quit")){
				
				Utils.saveWithPOI(searchedInfo,resultPath);
				break;
			}
			else if(command.indexOf("/d ")==0){
				try{
					int deleteline = Integer.parseInt(command.substring(command.indexOf(" ")+1)) ; 
					if(deleteline < 1 || deleteline > searchedInfo.size()){
						throw new Exception("input number within line size");
					}
					else{
						searchedInfo.remove(deleteline-1);
						System.out.println(Integer.toString(deleteline)+" line removed");
					}

				}catch(NumberFormatException e){
				 	System.out.println("please input number next time"); 
				}catch(Exception e){
					System.out.println(e);
				}
				
			}

			else if(command.indexOf("/pau ")==0){
				int index = command.indexOf(" ")+1 ;
				String keyword = command.substring(index);
				searchedInfo.get(searchedInfo.size()-1).pushAuthor(keyword);
			}

			else if(command.indexOf("/ppu ")==0){
				int index = command.indexOf(" ")+1 ;
				String keyword = command.substring(index);
				searchedInfo.get(searchedInfo.size()-1).pushPublisher(keyword);
			}
		
			else if(command.indexOf("/pda ")==0){
				int index = command.indexOf(" ")+1 ;
				String keyword = command.substring(index);
				searchedInfo.get(searchedInfo.size()-1).pushPubDate(keyword);
			}

			else if(command.indexOf("/pis ")==0){
				int index = command.indexOf(" ")+1 ;
				String keyword = command.substring(index);
				searchedInfo.get(searchedInfo.size()-1).pushISBN(keyword);
			}

			else if(command.indexOf("/ppr ")==0){
				int index = command.indexOf(" ")+1 ;
				String keyword = command.substring(index);
				searchedInfo.get(searchedInfo.size()-1).pushPrice(keyword);
			}

			else if(command.equals("/delete all")){
				searchedInfo.clear();
				System.out.println("All book information deleted");
			}
			else if(command.equals("/save")){
				Utils.saveWithPOI(searchedInfo,resultPath);
			}
			else if(command.indexOf("/save ")==0){
				int index = command.indexOf(" ")+1;
				Utils.saveWithPOI(searchedInfo,command.substring(index));
			}

			else if(command.equals("/show")){
				System.out.println("you found "+searchedInfo.size()+" informations");
				int lineNum = 0;	
				for(bookInfo b : searchedInfo){
					System.out.print(Integer.toString(++lineNum)+". ");
					System.out.println(b.toString());
				}
				
			}
			else if(command.indexOf("/set boxnumber ")==0){
				int index = command.indexOf(" ",5)+1 ;
				boxnumber = Integer.parseInt(command.substring(index));
				System.out.println("boxnumber set by "+command.substring(index));
			}

			else if(command.indexOf("/push ")==0){
				int index = command.indexOf(" ")+1 ;
				String keyword = command.substring(index) ;
				bookInfo coin = new bookInfo(keyword);
				coin.setBoxNumber(boxnumber);
				searchedInfo.add(coin);
				System.out.println(keyword+" pushed to list successfully");
			}

			else if(command.indexOf("/t ")==0){
				int index = command.indexOf(" ")+1 ;
				String keyword = command.substring(index) ;
				Thread st = new Thread(new SearchThread(searchedInfo,keyword,1,boxnumber));
				st.start();
				st.join();
			}

			else if (command.equals("/reverse")){
				for(bookInfo b : searchedInfo){
					infoInStack.push(b);
				}
				searchedInfo.clear();
				while(!infoInStack.isEmpty()){
					searchedInfo.add(infoInStack.pop());
				}
			}

			else if(command.indexOf("/")==0){
				System.out.println("wrong command, check /help");
			}

			else{
				String keyword = command ;
				Thread st = new Thread(new SearchThread(searchedInfo,keyword,2,boxnumber));
			        st.start();
			}

		}
	}


	public Options createOptions(){

		Options options = new Options();
		
		//only required
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		options.addOption(Option.builder("c").longOpt("cli")
			        .desc("Search by CLI menu")	
			        .build());

		
		options.addOption(Option.builder("b").longOpt("boxnumber")
				.desc("Set boxnumber")
				.hasArg()
				.argName("Box number for arrange")
				.build());
		

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

	public void printHelp(Options options){

		HelpFormatter formatter = new HelpFormatter();
		String header = "KabosuMy3a Book Data Search & Save";
		String footer = "github.com/kabosuMy3a/kabosuBookDataMaker";
		
		formatter.printHelp("KabosuBookDataMaker", header, options, footer, true);
	}

	public boolean parseOptions(Options options, String[] args){


		CommandLineParser parser = new DefaultParser();

		try{

			CommandLine cmd = parser.parse(options,args);

			resultPath = cmd.getOptionValue("o");
			cliMenu = cmd.hasOption("c");
			if(cmd.hasOption("b")) boxnumber = Integer.parseInt(cmd.getOptionValue("b"));
			ISBNPath = cmd.getOptionValue("I");
			help = cmd.hasOption("h");
					

		} catch(Exception e){
			
			printHelp(options);
			return false;
		}

		return true;
	}
}
