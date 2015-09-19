package ch3;

import java.util.LinkedList;

/*
 * Time complexity: O(1)
 * Space complexity: O(n)
 */

abstract class Animal{		// we first create an abstract class Animal which will be extended by Dog and Cat classes
	private int order;		// order is an integer which is incremented every time a new Animal object is inserted
	private String name;	// used to give any arbitrary name to the object
	
	public Animal(String nm){		// default constructor
		this.name = nm;
	}
	
	public void setOrder(int or){	// setter for order variable
		this.order = or;
	}
	
	public boolean isOlderThan(Animal other){	// if this object's order is less than another object's order, it means
		return this.order < other.order;		// this object is older
	}
	
	public String toString(){					// toString method is overridden to print output in main()
		return this.name + " "+this.order;
	}
}

class Dog extends Animal{		// Dog extends Animal class
	public Dog(String nm){
		super(nm);
	}
}

class Cat extends Animal{		// Cat extends Animal class
	public Cat(String nm){
		super(nm);
	}
}

public class Solution06 {
	
	int order = 0;		// initialize the order as zero
	LinkedList<Cat> cats = new LinkedList<Cat>();	// we keep two separate lists for Dogs and Cats so that dequeue takes O(1)
	LinkedList<Dog> dogs = new LinkedList<Dog>();	// time, otherwise if both are under one list, we would've to traverse through 
													// the list to find which Dog or Cat is older
	
	public void enqueue(Animal animal){
		animal.setOrder(order++);		// set the order of the object to be inserted and increment order
		
		if(animal instanceof Dog)			// if the object passed is Dog
			dogs.addFirst((Dog) animal);	// add it to Dog list
		else if(animal instanceof Cat)		// else
			cats.addFirst((Cat) animal);	// add it to Cat list 
	}
	
	public Dog dequeueDog(){				// dequeue from Dog list
		return dogs.pollLast();				// pollLast is used bcoz we have assumed that queue's
											// front is at the end of linked list
	}
	
	public Cat dequeueCat(){				// dequeue from Cat list
		return cats.pollLast();
	}
	
	public Animal dequeueAny(){				// dequeue based on order
		if(dogs.size() == 0)		// if there are no elements in dogs list
			return dequeueCat();	// just dequeue cat list
		else if(cats.size() == 0)	// if there are no elements in cats list
			return dequeueDog();	// just dequeue dog list
		
		// if dogs list's next element to be dequeued is older than cat list's next element to
		// be dequeued, dequeue dog else dequeue cat:
		return dogs.peekLast().isOlderThan(cats.peekLast()) ? dequeueDog() : dequeueCat();
	}
	
	public static void main(String[] args) {
		Solution06 s6 = new Solution06();
		Dog d1 = new Dog("d1");
		Dog d2 = new Dog("d2");
		Cat c1 = new Cat("c1");
		Cat c2 = new Cat("c2");
		s6.enqueue(d1);
		s6.enqueue(d2);
		s6.enqueue(c1);
		s6.enqueue(c2);
		System.out.println(s6.dequeueDog());
		System.out.println(s6.dequeueCat());
		System.out.println(s6.dequeueAny());
	}

}
