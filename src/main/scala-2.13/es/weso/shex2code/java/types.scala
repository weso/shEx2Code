package es.weso.shex2code.java

class Type

object String extends Type {

  override def toString(): String = {
    "String"
  }

}

object Integer extends Type {

  override def toString(): String = {
    "int"
  }

}

object Double extends Type {

  override def toString(): String = {
    "double"
  }

}

case class ListType(lType: Type) extends Type {

  override def toString: String = {
    s"List<$lType>"
  }

}

case class ClassType(className: String) extends Type {

  override def toString: String = {
    s"${className.toLowerCase.capitalize}"
  }

}
