package es.weso.shex2code.java

case class Field(accessModifier: AccessModifier, fType: Type, name: String) {

  override def toString: String = {
    s"$accessModifier $fType $name;\n"
  }
}
