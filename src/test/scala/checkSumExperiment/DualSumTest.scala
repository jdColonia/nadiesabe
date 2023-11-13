package checkSumExperiment

class DualSumTest extends munit.FunSuite {

  val helloBitsList: List[String] = List("01001000", "01100101", "01101100", "01101100", "01101111")
  val worldBitsList: List[String] = List("01010111", "01101111", "01110010", "01101100", "01100100")
  val scalaBitsList: List[String] = List("01010011", "01100011", "01100001", "01101100", "01100001")
  val pythonBitsList: List[String] = List("01010000", "01111001", "01110100", "01101000", "01101111", "01101110")
  val javaBitsList: List[String] = List("01001010", "01100001", "01110110", "01100001")

  val dualSum = new DualSum

  test("Test case 6") {
    assert(dualSum.checkSum(helloBitsList) == "1000110011110101")
  }

  test("Test case 7") {
    assert(dualSum.checkSum(worldBitsList) == "0000011100001010")
  }

  test("Test case 8") {
    assert(dualSum.checkSum(scalaBitsList) == "1000110011100101")
  }

  test("Test case 9") {
    assert(dualSum.checkSum(pythonBitsList) == "1001100110000100")
  }

  test("Test case 10") {
    assert(dualSum.checkSum(javaBitsList) == "1001101110000011")
  }

  test("Test case 13") {
    assert(dualSum.checkSum(List("01001000", "01100101", "01101100", "01101100", "01100101")) != "1000110011110101")
  }

  test("Test case 14") {
    assert(dualSum.checkSum(List("01010111", "01100101", "01110010", "01101100", "01100100")) != "0000011100001010")
  }

}