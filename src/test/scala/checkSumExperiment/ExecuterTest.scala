package checkSumExperiment

class ExecuterTest extends munit.FunSuite:
  val singleSum = new SingleSum
  val dualSum = new DualSum
  val helloBitsList: List[String] = List("01001000", "01100101", "01101100", "01101100", "01101111")
  val worldBitsList: List[String] = List("01010111", "01101111", "01110010", "01101100", "01100100")

  test("Test Case 37") {
    val singleSumTime = Executer.measureExecutionTime(singleSum, helloBitsList)
    assert(singleSumTime > 0)
  }

  test("Test Case 38") {
    val dualSumTime = Executer.measureExecutionTime(dualSum, worldBitsList)
    assert(dualSumTime > 0)
  }