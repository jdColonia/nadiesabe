package checkSumExperiment

object ExperimentController:
  val inputSizes: List[Int] = List(1000, 100000, 1000000)
  val EXPERIMENTATION_PATH: String = "src/experimentation/"
  val PATH_FOR_TEST_FILES: String = EXPERIMENTATION_PATH + "files/"

  def executeExperiment(): Unit =
    generateDateExperiment()
    val resultsSingleSum = Executer.executeExperiment(inputSizes, new SingleSum)
    Executer.exportResultsToCSV("Single Sum", resultsSingleSum)
    val resultsDualSum = Executer.executeExperiment(inputSizes, new DualSum)
    Executer.exportResultsToCSV("Dual Sum", resultsDualSum)

  private def generateDateExperiment(path: String = PATH_FOR_TEST_FILES, inputSizes: List[Int] = inputSizes): Unit =
    inputSizes.map(inputSize => DataManager.generateData(path, inputSize))