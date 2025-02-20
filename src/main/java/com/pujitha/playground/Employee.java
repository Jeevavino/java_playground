package com.pujitha.playground;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//Once the object is created and you can't change its behaviour is called immutable class. 
//Immutable class are thread safe
public final class Employee {
	private String name;
	private String age;
	
	 Employee(String name, String age){
		this.age = age;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return name.equals(employee.name) &&
                age.equals(employee.age);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.age.hashCode() *13;
    }
	
	public static void main(String[] args) {
		Employee e = new Employee("Bala", "40"); // reference created
		Employee d = new Employee("Bala", "40"); // reference created
		Employee f = e;
		//State change is the biggest problem in Java.It cause debug nightmares. for proper coding mostly prepare Encapsulation or immutable class\
		int i = 10;
		int j = 10; //Primitive not object
		int k = i;
		String a = "Pujitha"; //compile will instantiate new String("Pujitha")
		String b = "Pujitha";
		String c = a;
		System.out.println(i == j); //compare the value for primitve not object
		System.out.println(j == k);
		System.out.println(i == k);
		
		System.out.println(a == b);
		System.out.println(b == c);
		System.out.println(c == a);
		
		System.out.println(a.equals(b));
		System.out.println(b.equals(c));
		System.out.println(c.equals(a));
		
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());

		System.out.println("Hashcode comparision"); 
		System.out.println(e == d); //comparing the Object 
		System.out.println(d.equals(e));
		System.out.println(e.hashCode());
		System.out.println(d.hashCode());
		System.out.println(f.hashCode()); // Same hashcode of e that is called pass by reference
		System.out.println(e.equals(d)); 
		
		
		i = 20;
		System.out.println(i);
		
		System.out.println(k);// K will be still 10. it is passed by value
		System.out.println(e.getName().equals(d.getName()));
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put(c, "a");
		map1.put(a, "b");
		map1.put(b, "c");
		//map1.put(20," a");// it won't allow
		System.out.print(map1.toString());
		
		Map map2 = new HashMap();
		map2.put(20, "a");
		map2.put("a", "a");
		
	}
}


 class ImmutableTest {

	

}
