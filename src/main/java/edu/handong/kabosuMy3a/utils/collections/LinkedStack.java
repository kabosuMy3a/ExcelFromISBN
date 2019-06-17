package edu.handong.kabosuMy3a.utils.collections;


public class LinkedStack<T> {

	private ListNode<T> top;
        private int count ;	

	private class ListNode<T>{
	
		private T data ;
		private ListNode<T> link;
		
		public ListNode(T newData, ListNode linkedNode){
			
			data = newData;
			link = linkedNode ;
		}
	}

	public LinkedStack(){
		top = null ;
		count = 0 ;
	}

	public void push(T newData){
		
		ListNode<T> newNode = new ListNode(newData, top);
		top = newNode ;
		count++;

	}

	public boolean isEmpty(){
		return (top == null) ;
	}

	public int size(){
		return count ;
	}

	public T pop(){
		if (top==null) return null;
		T topItem = top.data ;
		top = top.link ;
		count--;
		return topItem;
	}
}
