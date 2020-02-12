package es.weso.shex2code.java

case class GetterGenerator(field: Field) {

  override def toString: String = {
    val sb = new StringBuilder
    sb.append(s"public ${field.fType} get${field.name.toLowerCase().capitalize}() {\n")
    sb.append(s"\t\treturn this.${field.name};\n")
    sb.append("\t}\n")
    sb.toString()
  }
}
