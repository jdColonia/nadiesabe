package checkSumExperiment

import checkSumExperiment.*
import munit.*

class CheckSumTest extends munit.FunSuite {

  val helloBitsList : List[String] = List("01001000", "01100101", "01101100", "01101100", "01101111")
  val worldBitsList : List[String] = List("01010111", "01101111", "01110010", "01101100", "01100100")
  val scalaBitsList : List[String] = List("01010011", "01100011", "01100001", "01101100", "01100001")
  val pythonBitsList : List[String] = List("01010000", "01111001", "01110100", "01101000", "01101111", "01101110")
  val javaBitsList : List[String] = List("01001010", "01100001", "01110110", "01100001")

  val checkSum: CheckSum = new CheckSum{
    override def checkSum(blocks: List[String]): String = ???
  }

  test("binarySum should calculate the correct sum for valid inputs") {
    assert(checkSum.binarySum("0", "0") == "0")
    assert(checkSum.binarySum("0", "1") == "1")
    assert(checkSum.binarySum("1", "0") == "1")
    assert(checkSum.binarySum("1", "1") == "10")
    assert(checkSum.binarySum("11011", "1") == "11100")
    assert(checkSum.binarySum("1", "11011") == "11100")
    assert(checkSum.binarySum("10101010101010101010", "11001100110011001100") == "101110111011101110110")
    assert(checkSum.binarySum("11001100110011001100", "111100001111000011110000") == "111111011011110110111100")
    assert(checkSum.binarySum("111100001111000011110000", "100100110010110010100110") == "1100001000001110110010110")
    assert(checkSum.binarySum("100100110010110010100110", "1") == "100100110010110010100111")
  }

}