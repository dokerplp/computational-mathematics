package util

import scala.jdk.CollectionConverters.SeqHasAsJava

object Converter {
  def scalaListToJavaList[A, B](list: List[A]): java.util.List[B] = {
    val castedList = list.map(f => f.asInstanceOf[B])
    SeqHasAsJava(castedList).asJava
  }

  def scalaListToJavaListDouble(list: List[Double]): java.util.List[java.lang.Double] = {
    scalaListToJavaList[scala.Double, java.lang.Double](list)
  }
}
