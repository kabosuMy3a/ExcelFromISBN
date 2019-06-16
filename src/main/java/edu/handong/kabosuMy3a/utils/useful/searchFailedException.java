package edu.handong.kabosuMy3a.utils.useful ;

public class searchFailedException extends Exception{


	public searchFailedException(){

		super("Failed to find bookInfo from keyword");
	}

	public searchFailedException(String msg){

		super(msg);
	}

}
