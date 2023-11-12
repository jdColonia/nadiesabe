package checkSumExperiment

class DualSum extends CheckSum:
  override def checkSum(blocks: List[String]): String =
    // Inicializar las sumas
    val initSumA = "0" * 8
    val initSumB = "0" * 8

    // Iterar a través de cada bloque en la palabra de datos
    val (finalSumA, finalSumB) = blocks.foldLeft((initSumA, initSumB)) { case ((sumA, sumB), block) =>
      // Calcular la nueva suma de A
      val newSumA = binarySum(sumA, block)
      val sumAInt = Integer.parseInt(newSumA, 2)
      val modSumA = sumAInt % 255
      val finalSumA = modSumA.toBinaryString.reverse.padTo(8, '0').reverse

      // Calcular la nueva suma de B
      val newSumB = binarySum(sumB, finalSumA)
      val sumBInt = Integer.parseInt(newSumB, 2)
      val modSumB = sumBInt % 255
      val finalSumB = modSumB.toBinaryString.reverse.padTo(8, '0').reverse

      (finalSumA, finalSumB)
    }

    // El valor de verificación es la suma final
    finalSumB + finalSumA