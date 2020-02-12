package es.weso.shex2code.java

object SimpleTest {

  def main(args: Array[String]): Unit = {
    val name = Field(Public, String, "name")
    val age = Field(Public, Integer, "age")
    val friends = Field(Public, ListType(String), "friends")
    val family = Field(Public, ListType(ListType(ListType(ClassType("Car")))), "cars")
    val dog = Field(Public, ClassType("Dog"), "dog")
    val dog2 = Field(Public, ClassType("Dog"), "dog2")

    val person = Class("es.weso.pojos", Public, "PersonExample", List(name, age, family, friends, dog, dog2))
    print(person)
    person.toFile()
  }
}
