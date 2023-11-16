package checkSumExperiment

object ExperimentController:
  val inputSizes: Seq[Int] = (0 to 7).map(math.pow(10, _).toInt)
  val EXPERIMENTATION_PATH: String = "src/experimentation/"
  val PATH_FOR_TEST_FILES: String = EXPERIMENTATION_PATH + "files/"

  def executeExperiment(): Unit =
    generateDateExperiment()
    val resultsSingleSum = Executer.executeExperiment(inputSizes, new SingleSum)
    Executer.exportResultsToCSV("Single Sum", resultsSingleSum)
    val resultsDualSum = Executer.executeExperiment(inputSizes, new DualSum)
    Executer.exportResultsToCSV("Dual Sum", resultsDualSum)

  private def generateDateExperiment(path: String = PATH_FOR_TEST_FILES, inputSizes: Seq[Int] = inputSizes): Unit =
    inputSizes.map(inputSize => DataManager.generateData(path, inputSize))