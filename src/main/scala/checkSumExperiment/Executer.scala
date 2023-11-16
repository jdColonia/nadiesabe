package checkSumExperiment

import java.io.{BufferedWriter, FileWriter}
import scala.annotation.tailrec
import java.nio.file.{Files, Paths}

object Executer:
  def executeExperiment(inputSizes: Seq[Int], algorithm: CheckSum): Vector[(Int, Long)] =
    val path = ExperimentController.PATH_FOR_TEST_FILES + DataManager.TEST_FILE_PREFIX

    @tailrec
    def executeExperimentAux(inputSizes: Seq[Int], accResults: Vector[(Int, Long)] = Vector.empty[(Int, Long)]): Vector[(Int, Long)] =
      inputSizes match
        case Nil => accResults
        case inputSize +: t =>
          val bits: String = DataManager.loadData(path + inputSize + ".txt")
          val bitBlocks = DataManager.readData(bits)
          val result = measureExecutionTime(algorithm, bitBlocks)
          executeExperimentAux(t, (inputSize -> result) +: accResults)

    executeExperimentAux(inputSizes)

  def measureExecutionTime(algorithm: CheckSum, blocks: List[String]): Long =
    val startTime: Long = System.currentTimeMillis()
    algorithm.checkSum(blocks)
    val endTime: Long = System.currentTimeMillis()
    endTime - startTime

  def exportResultsToCSV(testedAlgorithmName: String, results: Vector[(Int, Long)]): Unit =
    val dirPath: String = ExperimentController.EXPERIMENTATION_PATH + "results/"
    val filePath: String = dirPath + testedAlgorithmName + " Results.csv"

    if (!Files.exists(Paths.get(dirPath)))
      Files.createDirectories(Paths.get(dirPath))

    val file = new BufferedWriter(new FileWriter(filePath))
    try
      results.foreach { case (size, time) =>
        file.write(s"$size,$time\n")
      }
    finally
      file.close()
