import scala.io.Source
import scala.io.StdIn.readLine

/**
  * Created by erich on 10/20/2016.
  */
object FileWordCount {
  def  main(args: Array[String]): Unit = {
    val name = readLine("Enter Filename? ")
    val Word = "\\b([A-Za-z\\-])+\\b".r//    val getCurrentDirectory = new java.io.File(".").getCanonicalPath

    val wordCounter = Source.fromFile("input.txt")("UTF-8")
      .getLines()
      .flatMap(line => Word.findAllIn(line.toLowerCase()))
      .toList
      .groupBy((word: String) => word)

    for ((key,value) <- wordCounter) {
      printf("%s %s\n", value.size, key)
    }
  }
}
