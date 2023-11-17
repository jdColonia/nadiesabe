package checkSumExperiment

import java.io.{BufferedWriter, FileWriter}
import scala.annotation.tailrec
import java.nio.file.{Files, Paths}

object Executer:
  def executeExperiment(inputSizes: Seq[Int], algorithm: CheckSum): Vector[(Int, Long, String)] =
    val path = ExperimentController.PATH_FOR_TEST_FILES + DataManager.TEST_FILE_PREFIX

    @tailrec
    def executeExperimentAux(inputSizes: Seq[Int], accResults: Vector[(Int, Long, String)] = Vector.empty[(Int, Long, String)]): Vector[(Int, Long, String)] =
      inputSizes match
        case Nil => accResults
        case inputSize +: t =>
          val bits: String = DataManager.loadData(path + inputSize + ".txt")
          val bitBlocks = DataManager.readData(bits)
          val (time, checksum) = measureExecutionTime(algorithm, bitBlocks)
          executeExperimentAux(t, (inputSize, time, checksum) +: accResults)

    executeExperimentAux(inputSizes)

  def measureExecutionTime(algorithm: CheckSum, blocks: List[String]): (Long, String) =
    val startTime: Long = System.currentTimeMillis()
    val result = algorithm.checkSum(blocks)
    val endTime: Long = System.currentTimeMillis()
    (endTime - startTime, result)

  def exportResultsToCSV(testedAlgorithmName: String, results: Vector[(Int, Long, String)]): Unit =
    val dirPath: String = ExperimentController.EXPERIMENTATION_PATH + "results/"
    val filePath: String = dirPath + testedAlgorithmName + " Results.csv"

    if (!Files.exists(Paths.get(dirPath)))
      Files.createDirectories(Paths.get(dirPath))

    val file = new BufferedWriter(new FileWriter(filePath))
    file.write("Size,Time,CheckSum\n")
    try
      results.foreach { case (size, time, checksum) =>
        file.write(s"$size,$time,$checksum\n")
      }
    finally
      file.close()
