package es.weso.shex2code.java

class AccessModifier

object Public extends AccessModifier {

  override def toString(): String = {
    "public"
  }

}

object Protected extends AccessModifier {

  override def toString(): String = {
    "protected"
  }

}

object Private extends AccessModifier {

  override def toString(): String = {
    "private"
  }

}