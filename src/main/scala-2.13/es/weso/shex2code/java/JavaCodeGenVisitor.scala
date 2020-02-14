package es.weso.shex2code.java

import es.weso.shexl.ast.{PrefixInv, ShExL, ShapeDef, ShapeInv}
import es.weso.shexl.visitor.AbstractVisitor

import scala.collection.JavaConverters
import scala.collection.mutable.ListBuffer

class JavaCodeGenVisitor extends AbstractVisitor {

  var classes = ListBuffer[Class]()

  

  override def visit(node:ShExL, param:Any) = {
    for(shapeDef <- JavaConverters.asScalaIterator(node.definitions.iterator()) if shapeDef.isInstanceOf[ShapeDef]) {
      shapeDef.accept(this, param)
    }
  }

  override def visit(node: ShapeDef, param: Any) = {
    var fields = ListBuffer[Field]()

    for(constraint <- JavaConverters.asScalaIterator(node.constraints.iterator())) {
      val fieldName = constraint.field.prefixInv.propertyName;
      var fType: Type = null

      if(constraint.cType.invocation.isInstanceOf[PrefixInv]) {
        val innerType = constraint.cType.invocation.asInstanceOf[PrefixInv].propertyName

        if(innerType.toLowerCase.equals("string")) {
          fType = String
        } else if(innerType.toLowerCase.equals("integer")) {
          fType = Integer
        } else if(innerType.toLowerCase.equals("double")) {
          fType = Double
        }

      } else if(constraint.cType.invocation.isInstanceOf[ShapeInv]) {
        val innerType = ClassType(constraint.cType.invocation.asInstanceOf[ShapeInv].shapeName)
        fType = innerType
      }

      val field = Field(Public, fType, fieldName)
      fields += field
    }

    val cclass = Class("", Public, node.name, fields.result())
    classes += cclass
  }

}
