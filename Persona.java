package ;

import .Car;

public class Persona {

	public String name;
	public int age;
	public double id;
	public Car has;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getId() {
		return this.id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public Car getHas() {
		return this.has;
	}

	public void setHas(Car has) {
		this.has = has;
	}

}