package es.weso.shex2code.java

import es.weso.shexl.error.ErrorHandler
import es.weso.shexl.parser.generated.{ShExLLexer, ShExLParser}
import es.weso.shexl.visitor.{DefinitionsVisitor, InvocationsVisitor}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object SimpleVisitorTest {

  def main(args: Array[String]): Unit = {
    val input = CharStreams.fromFileName( args(0) )
    val lexer = new ShExLLexer( input )

    val tokens = new CommonTokenStream( lexer )
    val parser = new ShExLParser( tokens )
    val ast = parser.shex_lite_doc().ast

    println(ast)

    val defVisit = new DefinitionsVisitor
    defVisit.visit(ast, null)

    val invVisit = new InvocationsVisitor
    invVisit.visit(ast, null)

    ErrorHandler.showErrors()

    val generator = new JavaCodeGenVisitor
    generator.visit(ast, null)

    for(c <- generator.classes) {
      println("new fileeeeee")
      c.toFile()
    }

  }

}
