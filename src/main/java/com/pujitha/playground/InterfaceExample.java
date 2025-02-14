package com.pujitha.playground;

public interface InterfaceExample {

	void eat();
	
}
//InterfaceExample Equalivant to InterfaceExample1
 abstract class InterfaceExample1 {

	public abstract void eat();
	
}

 //Extends is only for Inheritance not for interface, you can't extend interface, Heirachy won't work in interface
class Impl implements InterfaceExample{

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		
	}
	
}

class Impl1 implements InterfaceExample {

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		
	}
	
}


abstract class Animal {
	String age = "10"; //new String object Equalivant to String age = new String() age = 10;
	public  abstract void eat();
	public  void sleep() {
		System.out.print("sleeping");
	}
	
	public  void walking() {
		System.out.print("walking");
	}
}

class Dog extends Animal {
	
	int age = 0;

	//This is how a constructor look like. Comment this code to see its functionality
	//Dog(){
		//super();
	//}
	
	
	//Overriding the default constructor
	Dog(int age){
		
		this.age= age;
	}

	@Override
	public void eat() {
		System.out.print("I am eating");
	}
	
	public void tail() {
		System.out.print("tail tail" +age);
	}
	
	public void walkAndTalk() {
		super.walking();
		System.out.println("talking");
	}
}


//Inheritance GermanShepard inherits Dog, Dog inherits Animal
class GermanShepard extends  Dog {

	GermanShepard(int age) {
		super(age);
	}
	
}



//All class will by default extends Object
class TestExample extends Object{
	
	public static void main(String ...args) {
		//Animal a = new Animal(); Abstract method can't be instantiated 
		
		//Dog d = new Dog(); // you shouldn't do this
		//d.eat();
		Animal d = new Dog(10); 
		
		GermanShepard c = new GermanShepard(10); 
		c.sleep();//Inherits from Animal
		c.walkAndTalk();//Inherits from Dog
	}
	
}
