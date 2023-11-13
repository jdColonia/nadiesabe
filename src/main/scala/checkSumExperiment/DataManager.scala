package checkSumExperiment

import java.nio.file.{Files, Paths}
import java.util.Random
import scala.io.Source

class DataManager private() {
  def TEST_FILE_PREFIX: String = "testFile"

  def generateData(path: String, testSize: Int): String = {
    if (testSize <= 0) {
      throw new IllegalArgumentException("testSize must be greater than 0")
    }
    val dirPath = Paths.get(path)
    if (!Files.exists(dirPath)) {
      Files.createDirectories(dirPath)
    }
    val filePath = dirPath.resolve(TEST_FILE_PREFIX + testSize + ".txt")
    val random = new Random()
    val data = Array.fill(testSize)(random.nextInt(32,127).toByte)
    Files.write(filePath, data.map(_.toChar).mkString.getBytes)
    filePath.toString
  }

  def loadData(filePath: String): String = {
    val bytes = Files.readAllBytes(Paths.get(filePath))
    bytes.map(b => String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0')).mkString
  }

  def readData(fileBits: String): List[String] = {
    fileBits.grouped(8).toList
  }
}

object DataManager {
  private var instance: DataManager = null

  def getInstance(): DataManager = {
    if (instance == null) {
      instance = new DataManager()
    }
    instance
  }
}