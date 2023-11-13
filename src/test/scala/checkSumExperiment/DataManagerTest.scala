package checkSumExperiment

import java.io.File

class DataManagerTest extends munit.FunSuite {

  val path = "src/files/"
  val toySize = 50
  val smallSize = 500
  val mediumSize = 15000
  val largeSize = 100000
  val invalidSize0 = 0
  val invalidNegativeSize: Int = -100

  val dataManager: DataManager = DataManager.getInstance()

  test("Test case 25") {
    val newPath = dataManager.generateData(path, toySize)
    val file = new File(newPath)
    assert(file.length() == toySize)
  }

  test("Test case 26") {
    val newPath = dataManager.generateData(path, smallSize)
    val file = new File(newPath)
    assert(file.length() == smallSize)
  }

  test("Test case 27") {
    val newPath = dataManager.generateData(path, mediumSize)
    val file = new File(newPath)
    assert(file.length() == mediumSize)
  }

  test("Test case 28") {
    val newPath = dataManager.generateData(path, largeSize)
    val file = new File(newPath)
    assert(file.length() == largeSize)
  }

  test("Test case 29") {
    intercept[IllegalArgumentException] {
      dataManager.generateData(path, invalidSize0)
    }
  }

  test("Test case 30") {
    intercept[IllegalArgumentException] {
      dataManager.generateData(path, invalidNegativeSize)
    }
  }

  val simpleTextFile = "src/files/simple_text.txt"
  val largeFile = "src/files/large_file.txt"
  val nonExistenFile = "src/files/non-existent_file.txt"

  test("Test case 31") {
    assert(dataManager.loadData(simpleTextFile).length == 400)
  }

  test("Test case 32") {
    assert(dataManager.loadData(largeFile).length == 800000)
  }

  test("Test case 33") {
    intercept[IllegalArgumentException] {
      dataManager.loadData(nonExistenFile)
    }
  }

  val emptyBitsFile: String = ""
  val smallBitsFile: String = "0100100001100101011011000110110001101111"
  val largeBitsFile: String = "01001000011001010110110001101100011011110101011101101111011100100110110001100100010100110110001101100001011011000110000101010000011110010111010001101000011011110110111001001010011000010111011001100001"
  val smallBitsFileList: List[String] = List("01001000", "01100101", "01101100", "01101100", "01101111")
  val largeBitsFileList: List[String] = List(
    "01001000", "01100101", "01101100", "01101100", "01101111", "01010111", "01101111", "01110010", "01101100", "01100100",
    "01010011", "01100011", "01100001", "01101100", "01100001", "01010000", "01111001", "01110100", "01101000", "01101111",
    "01101110", "01001010", "01100001", "01110110", "01100001"
  )

  test("Test case 34") {
    assert(dataManager.readData(emptyBitsFile) == Nil)
  }

  test("Test case 35") {
    assert(dataManager.readData(smallBitsFile) == smallBitsFileList)
  }

  test("Test case 36") {
    assert(dataManager.readData(largeBitsFile) == largeBitsFileList)
  }

}