/** 
* @version 1.00 2018.9.2
* @author xiekun
*/


public class basicDS{
	public static void main(String[] args){
		//mStack s = new mStack();
		//System.out.println(s.empty());
		//s.push(3);
		//System.out.println(s.pop());
		//mQueue q = new mQueue();
		//q.enqueue(9);
		//System.out.println(q.dequeue());
		mList l = new mList();
		System.out.println(l.empty());
		l.insert(3);
		l.insert(2);
		l.insert(1);
		System.out.println(l.head.key);
		Node n = l.delete(2);
		System.out.println(n.key);
		n = l.delete(1);
		System.out.println(n.key);
		n = l.delete(3);
		System.out.println(n.key);
	}
}

class mStack{
	private int[] value = new int[10];
	
	private int top = -1;
	
	public boolean empty(){
		if (this.top == -1) return true;
		return false;
	}
	
	public void push(int x){
		if (this.top == 9) System.out.println("overflow"); //还不会写异常
		this.top++;
		this.value[this.top] = x;
	}
	
	public int getTop(){
		if (this.empty()) System.out.println("empty");
		return value[top];
	}
	
	public int pop(){
		if (this.empty()) System.out.println("empty");
		top--;
		return value[top + 1];
	}
}

class mQueue{
	private int[] value = new int[10];
	
	private int head, tail = 0;
	
	public void enqueue(int x){
		if (tail == 9 && head == 1) System.out.println("full");
		else if (head == tail + 1) System.out.println("full");
		value[tail] = x;
		if (tail == 9) tail = 1;
		else tail++;
	}
	
	public int dequeue(){
		if (head == tail) System.out.println("empty");
		int x = value[head];
		if (head == 9) head = 1;
		else head++;
		return x;
	}
}

class Node{
	Node prev = null;
	Node next = null;
	int key;
}

class mList{
	
	public Node head;
	
	public boolean empty(){
		if (head == null) return true;
		return false;
	}
	
	public void insert(int x){
		Node n = new Node();
		n.key = x;
		n.next = head;
		if (!empty()) head.prev = n;
		head = n;
	}
	
	public Node search(int x){
		Node n = head;
		while(n != null && n.key != x) n = n.next;
		return n;
	}
	
	public Node delete(int x){
		Node n = search(x);
		if (n.prev != null) n.prev.next = n.next;
		else head = n.next;
		if (n.next != null) n.next.prev = n.prev;
		return n;
	}
}























