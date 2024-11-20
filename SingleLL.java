class SingleLL{
	Node head;
	class Node{
		int data;
		Node next;
		Node(int data){
			this.data = data;
			this.next = null;
		}
	}
	public void add(int data){
		Node newNode = new Node(data);
		if(head == null){
			head = newNode;
			return;
		}
		Node temp = head;
		while(temp.next != null){
			temp = temp.next;
		}
		temp.next = newNode;
	}
	
	public void delete(){
		if(head == null){
			System.out.println("Queue is empty");
		}
		head = head.next;
	}
	
	public void display(){
		Node curr = head;
		while(curr != null){
			System.out.print(curr.data +" ");
			curr= curr.next;
		}
		System.out.println();
	}
	
	public static void main(String args[]){
		SingleLL list = new SingleLL();
		list.add(10);
		list.add(20);
		list.add(30);
		list.display();
		list.delete();
		list.display();
	}
	
}
