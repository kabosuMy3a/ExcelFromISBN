package edu.handong.kabosuMy3a.utils ;

import org.apache.commons.cli.* ;

public interface CLIOptionable{

	public Options createOptions();
	public void printHelp(Options options);
	public boolean parseOptions(Options options, String[] args);

}
