package checkSumExperiment

import checkSumExperiment.*
import munit.*

class CheckSumTest extends munit.FunSuite {

  val zero = "0"
  val one = "1"
  val twentySeven = "11011"
  val largeNumber1 = "10101010101010101010"
  val largeNumber2 = "11001100110011001100"
  val largeNumber3 = "111100001111000011110000"
  val largeNumber4 = "100100110010110010100110"

  val checkSum: CheckSum = new CheckSum{
    override def checkSum(blocks: List[String]): String = ???
  }

  test("binarySum should calculate the correct sum for valid inputs") {
    assert(checkSum.binarySum(zero, zero) == "0")
    assert(checkSum.binarySum(zero, one) == "1")
    assert(checkSum.binarySum(one, zero) == "1")
    assert(checkSum.binarySum(one, one) == "10")
    assert(checkSum.binarySum(twentySeven, one) == "11100")
    assert(checkSum.binarySum(one, twentySeven) == "11100")
    assert(checkSum.binarySum(largeNumber1, largeNumber2) == "101110111011101110110")
    assert(checkSum.binarySum(largeNumber2, largeNumber3) == "111111011011110110111100")
    assert(checkSum.binarySum(largeNumber3, largeNumber4) == "1100001000001110110010110")
    assert(checkSum.binarySum(largeNumber4, one) == "100100110010110010100111")
  }

}