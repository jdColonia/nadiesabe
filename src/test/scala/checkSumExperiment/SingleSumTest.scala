package checkSumExperiment

class SingleSumTest extends munit.FunSuite {

  val helloBitsList: List[String] = List("01001000", "01100101", "01101100", "01101100", "01101111")
  val worldBitsList: List[String] = List("01010111", "01101111", "01110010", "01101100", "01100100")
  val scalaBitsList: List[String] = List("01010011", "01100011", "01100001", "01101100", "01100001")
  val pythonBitsList: List[String] = List("01010000", "01111001", "01110100", "01101000", "01101111", "01101110")
  val javaBitsList: List[String] = List("01001010", "01100001", "01110110", "01100001")

  val singleSum = new SingleSum

  test("singleSum checksum should calculate the correct checksum for valid inputs") {
    assert(singleSum.checkSum(helloBitsList) == "1101001000100011")
    assert(singleSum.checkSum(worldBitsList) == "1101110000101101")
    assert(singleSum.checkSum(scalaBitsList) == "1101000000010101")
    assert(singleSum.checkSum(pythonBitsList) == "0011010001010000")
    assert(singleSum.checkSum(javaBitsList) == "1100000011000010")
  }

  test("singleSum checksum should detect changes in the input data") {
    assert(singleSum.checkSum(List("01001000", "01100101", "01101100", "01101100", "01100101")) != "1101001000100011")
    assert(singleSum.checkSum(List("01010111", "01100101", "01110010", "01101100", "01100100")) != "1101110000101101")
  }

}