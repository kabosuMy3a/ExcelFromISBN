package edu.handong.kabosuMy3a.utils.collections;

public class popFromEmptyStackException extends Exception{

	public popFromEmptyStackException(){
		super("Stack is empty");
	}	

	public popFromEmptyStackException(String msg){
		
		super(msg);
	}
}
