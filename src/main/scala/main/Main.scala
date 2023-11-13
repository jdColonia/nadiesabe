package main

import checkSumExperiment.{DataManager, DualSum}

object Main:
  def main(args: Array[String]): Unit = {
    val dataManager = DataManager.getInstance()
    val loadData = dataManager.loadData("src/files/large_file.txt")
    val readData = dataManager.readData(loadData)
    val dualSum = new DualSum
    val res = dualSum.checkSum(readData)
    println(res)
    println(res == "1011010011010111")
  }

  def executeExperiment(): Unit = ???