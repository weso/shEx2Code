package es.weso.pojos;

import es.weso.pojos.Dog;
import es.weso.pojos.Car;
import java.util.List;

public class PersonExample {

	public String name;
	public int age;
	public List<List<List<Car>>> cars;
	public List<String> friends;
	public Dog dog;
	public Dog dog2;

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

	public List<List<List<Car>>> getCars() {
		return this.cars;
	}

	public void setCars(List<List<List<Car>>> cars) {
		this.cars = cars;
	}

	public List<String> getFriends() {
		return this.friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	public Dog getDog() {
		return this.dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public Dog getDog2() {
		return this.dog2;
	}

	public void setDog2(Dog dog2) {
		this.dog2 = dog2;
	}

}