package Retail_Sales

import Retail_Sales.Arguments
import org.junit.Test

@Test
class ArgumentsTest {

  //<editor-fold desc="Test Argument">

  @Test
  def testisArgValid1(): Unit = {
    assert(!Arguments.isArgValid(new Array[String](1)))
  }

  @Test
  def testisArgValid2(): Unit = {
    assert(Arguments.isArgValid(new Array[String](2)))
  }

  @Test
  def testisArgValid3(): Unit = {
    assert(Arguments.isArgValid(new Array[String](3)))
  }
  //</editor-fold>

}