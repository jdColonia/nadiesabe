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
    assert(dataManager.generateData(path, toySize).length * 8 == toySize)
  }

  test("Test case 26") {
    assert(dataManager.generateData(path, smallSize).length * 8 == smallSize)
  }

  test("Test case 27") {
    assert(dataManager.generateData(path, mediumSize).length * 8 == mediumSize)
  }

  test("Test case 28") {
    assert(dataManager.generateData(path, largeSize).length * 8 == largeSize)
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
    assert(dataManager.loadData(simpleTextFile).length * 8 == 50)
  }

  test("Test case 32") {
    assert(dataManager.loadData(largeFile).length * 8 == 100000)
  }

  test("Test case 33") {
    intercept[IllegalArgumentException] {
      dataManager.loadData(nonExistenFile)
    }
  }

  val emptyBitsFile: String = ""
  val smallBitsFile: String = "0100100001100101011011000110110001101111"
  val largeBitsFile: String = "010010000110010101101100011011000110111101010111011011110110010001100100010100110110000101100011011011110110110011001010110110001100000010100110110000101100101011100110110000101100010011010000110011101100101011011010110000101100100010100100110010101110100011010000110010100100100011011110110110101101100011010010110110001100001"
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