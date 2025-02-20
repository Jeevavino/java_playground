package com.pujitha.playground;

public class AccessModifier {
	//There are 4 access modifier , public, private, protected and default
	//Public is most unsecured one, anyone can access from any package - Scope everyone
	//Private is the most strict one only class can access- Scope only Same class
	//If we don't provide any access modifier that is called default. - Scope same package
	//Procted is another modifier- Scope  - Same package  + inheritance
	public int test= 0;
	
	//Encapsulation can be used to hide data members and data functions or methods
	private int catAge= 0;
	
	int testAge = 0; // if we don't put any modifier is called default. 
	
	public int getCatAge() {
		return catAge;
	}

	public void setCatAge(int catAge) throws Exception {
		if(catAge <=0 && catAge >=100) {
			this.catAge = catAge;
		}else {
			throw new Exception("Exception");
		}
	}

	public void setTest(int test) {
		this.test= test;
		
	}

	protected int dogFood= 10;
	
	public int getTestAge() {
		return testAge;
	}

	public void setTestAge(int testAge) {
		this.testAge = testAge;
	}

	public int getDogFood() {
		return dogFood;
	}

	public void setDogFood(int dogFood) throws Exception {
		if(dogFood <=0 && dogFood >=100) {
			this.dogFood = dogFood;
		}else {
			throw new Exception("Exception");
		}
	}

	public int getTest() {
		this.method1();//Only accessible within the class not outiside of the class
		return test;
	}
	
	private void method1() {
		System.out.println("Method 1");
	}
}

class TestExample1 { // class can be either public or default
	
	public static void main(String ...args) throws Exception {
		AccessModifier aa = new AccessModifier();
		//aa.catAge = -3;
		aa.setCatAge(-3);//I will get an exception // we have to protect our data consider if someone put admin details as public
		//aa.method1();// I can't access method1 as it is private
		aa.testAge = 5;// Protected can be accessed within same package
		aa.dogFood = 110;
		System.out.println(aa.getDogFood()); // it print 110 which is wrong. Encapsulation always use private
		
	}
}
