package es.weso.shex2code.java

case class SetterGenerator(field: Field) {

  override def toString: String = {
    val sb = new StringBuilder
    sb.append(s"public void set${field.name.toLowerCase.capitalize}(${field.fType} ${field.name.toLowerCase}) {\n")
    sb.append(s"\t\tthis.${field.name} = ${field.name.toLowerCase};\n")
    sb.append("\t}\n")
    sb.toString()
  }
}
