package main

import checkSumExperiment.DataManager

object Main:
  def main(args: Array[String]): Unit = {
    val dataManager: DataManager = DataManager.getInstance()
    val newPath = dataManager.generateData("src/files/", 50)
    print(dataManager.loadData(newPath))

  }

  def executeExperiment(): Unit = ???