package es.weso.shex2code.java

import java.io.{File, PrintWriter}

case class Class(cPackage: String, accessModifier: AccessModifier, className: String, fields: List[Field]) {

  private[this] final var imports = Set[String]()

  def toFile(): Unit = {
    val writer = new PrintWriter( new File(s"$className.java") )
    writer.write(this.toString)
    writer.flush()
  }

  override def toString: String = {
    val sb = new StringBuilder

    // Generating package declaration.
    generatePackage(sb)

    // Generating imports if any exists.
    generateImports(sb)
    sb.append("\n")

    // Start class declaration.
    openClass(sb)

    // Declaring fields.
    generateFieldsDefinitions(sb)
    sb.append("\n")

    // Generating getters and setters.
    generateGettersAndSetters(sb)

    // Closing the class declaration
    closeClass(sb)

    // Returning the resulting class as an string.
    sb.toString()
  }

  private[this] def generatePackage(sb: StringBuilder): StringBuilder ={
    sb.append(s"package $cPackage;\n\n")
  }

  private[this] def generateImports(sb: StringBuilder): StringBuilder = {
    for(field <- fields if field.fType.isInstanceOf[ClassType]) {
      imports += s"import $cPackage.${field.fType};\n"
    }

    for(field <- fields if field.fType.isInstanceOf[ListType]) {
      val list = field.fType.asInstanceOf[ListType]
      val listType = getListType(list)
      if(listType.isInstanceOf[ClassType]) {
        val cl = listType.asInstanceOf[ClassType]
        if(!cl.className.equals(this.className)) {
          imports += s"import $cPackage.$listType;\n"
        }
      }
    }

    if(fields.exists(_.fType.isInstanceOf[ListType])) {
      imports += "import java.util.List;\n"
    }

    for(i <- imports) {
      sb.append(i)
    }

    sb
  }

  private[this] def openClass(sb: StringBuilder): StringBuilder = {
    sb.append(s"$accessModifier class $className {\n\n")
  }

  private[this] def generateFieldsDefinitions(sb: StringBuilder): StringBuilder = {
    for(field <- fields) {
      sb.append(s"\t$field")
    }
    sb
  }

  private[this] def generateGettersAndSetters(sb: StringBuilder): StringBuilder = {
    for(field <- fields) {
      sb.append("\t"+GetterGenerator(field))
      sb.append("\n")
      sb.append("\t"+SetterGenerator(field))
      sb.append("\n")
    }
    sb
  }

  private[this] def closeClass(sb: StringBuilder): StringBuilder = {
    sb.append("}")
  }

  private[this] def getListType(list: ListType): Type ={
    if(list.lType.isInstanceOf[ListType]) {
      return getListType(list.lType.asInstanceOf[ListType])
    }
      list.lType
  }

}
