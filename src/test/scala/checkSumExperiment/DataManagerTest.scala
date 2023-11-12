package checkSumExperiment

import java.io.File

class DataManagerTest extends munit.FunSuite {

  val path = "src/files/"
  val toySize = 50
  val smallSize = 500
  val mediumSize = 15000
  val largeSize = 100000
  val invalidSize0 = 0
  val invalidNegativeSize = -100

  val dataManager = new DataManager()

  test("generateData should generate a file with 50 bits of random data") {
    assert(dataManager.generateData(path, toySize).length * 8 == toySize)
  }

  test("generateData should generate a file with 500 bits of random data") {
    assert(dataManager.generateData(path, smallSize).length * 8 == smallSize)
  }

  test("generateData should generate a file with 15000 bits of random data") {
    assert(dataManager.generateData(path, mediumSize).length * 8 == mediumSize)
  }

  test("generateData should generate a file with 100000 bits of random data") {
    assert(dataManager.generateData(path, largeSize).length * 8 == largeSize)
  }

  test("generateData should attempt to generate a file with 0 bits of data") {
    intercept[IllegalArgumentException] {
      dataManager.generateData(path, invalidSize0)
    }
  }

  test("generateData should attempt to generate a file with a negative size") {
    intercept[IllegalArgumentException] {
      dataManager.generateData(path, invalidNegativeSize)
    }
  }

  val simpleTextFile = "src/files/simple_text.txt"
  val largeFile = "src/files/large_file.txt"
  val nonExistenFile = "src/files/non-existent_file.txt"

  test("loadData should load data from a simple text file") {
    assert(dataManager.loadData(simpleTextFile).length * 8 == 50)
  }

  test("loadData should load data from a large file") {
    assert(dataManager.loadData(largeFile).length * 8 == 100000)
  }

  test("loadData should attempt to load data from a non-existent file") {
    intercept[IllegalArgumentException] {
      dataManager.loadData(nonExistenFile)
    }
  }

  val emptyBitsList: List[String] = List.empty[String]
  val smallBitsFile: String = "0100100001100101011011000110110001101111"
  val largeBitsFile: String = "010010000110010101101100011011000110111101010111011011110110010001100100010100110110000101100011011011110110110011001010110110001100000010100110110000101100101011100110110000101100010011010000110011101100101011011010110000101100100010100100110010101110100011010000110010100100100011011110110110101101100011010010110110001100001"
  val smallBitsFileList: List[String] = List("01001000", "01100101", "01101100", "01101100", "01101111")
  val largeBitsFileList: List[String] = List(
    "01001000", "01100101", "01101100", "01101100", "01101111", "01010111", "01101111", "01110010", "01101100", "01100100",
    "01010011", "01100011", "01100001", "01101100", "01100001", "01010000", "01111001", "01110100", "01101000", "01101111",
    "01101110", "01001010", "01100001", "01110110", "01100001"
  )

  test("readData should read an empty list of bits") {
    assert(dataManager.readData(emptyBitsList) == Nil)
  }

  test("readData should read a list of bits for a small file") {
    assert(dataManager.readData(smallBitsFile) == smallBitsFileList)
  }

  test("readData should read a list of bits for a large file") {
    assert(dataManager.readData(largeBitsFile) == largeBitsFileList)
  }

}