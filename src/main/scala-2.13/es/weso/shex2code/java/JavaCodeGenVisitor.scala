package es.weso.shex2code.java

import es.weso.shexlite.compiler.ast.{PrefixInvNode, ShapeExpressionsFileNode, ShapeDefNode, ShapeInvNode}
import es.weso.shexlite.compiler.visitor.AbstractVisitor

import scala.collection.JavaConverters
import scala.collection.mutable.ListBuffer

class JavaCodeGenVisitor extends AbstractVisitor {

  var classes = ListBuffer[Class]()

  

  override def visit(node:ShapeExpressionsFileNode, param:Any) = {
    for(shapeDef <- JavaConverters.asScalaIterator(node.definitions.iterator()) if shapeDef.isInstanceOf[ShapeDefNode]) {
      shapeDef.accept(this, param)
    }
  }

  override def visit(node: ShapeDefNode, param: Any) = {
    var fields = ListBuffer[Field]()

    for(constraint <- JavaConverters.asScalaIterator(node.constraints.iterator())) {
      val fieldName = constraint.field.prefixInv.propertyName;
      var fType: Type = null

      if(constraint.cType.invocation.isInstanceOf[PrefixInvNode]) {
        val innerType = constraint.cType.invocation.asInstanceOf[PrefixInvNode].propertyName

        if(innerType.toLowerCase.equals("string")) {
          fType = String
        } else if(innerType.toLowerCase.equals("integer")) {
          fType = Integer
        } else if(innerType.toLowerCase.equals("double")) {
          fType = Double
        }

      } else if(constraint.cType.invocation.isInstanceOf[ShapeInvNode]) {
        val innerType = ClassType(constraint.cType.invocation.asInstanceOf[ShapeInvNode].shapeName)
        fType = innerType
      }

      val field = Field(Public, fType, fieldName)
      fields += field
    }

    val cclass = Class("", Public, node.name, fields.result())
    classes += cclass
  }

}
