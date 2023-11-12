class SingleSum extends CheckSum:
  override def checkSum(blocks: List[String]): String =
    // Añadir un bloques de 8 bits si la cantidad de bloques es impar
    val blocksPadded =
      if (blocks.length % 2 != 0) "0" * 8 :: blocks
      else blocks

    // Agrupar los bloques de 8 bits en 16 bits
    val blocksGrouped = blocksPadded.grouped(2).map(_.mkString).toList

    // Inicializar la suma
    val initSum = "0" * blocksGrouped.head.length

    // Iterar a través de cada bloque en la palabra de datos
    val finalSum = blocksGrouped.foldLeft(initSum) { (sum, blocksGrouped) =>
      // Calcular la nueva suma
      val newSum = binarySum(sum, blocksGrouped)

      // Convertir la suma a un número entero
      val sumInt = Integer.parseInt(newSum, 2)

      // Realizar la operación de módulo
      val modSum = sumInt % ((1 << 16) - 1)

      // Convertir el resultado de nuevo a una cadena binaria
      modSum.toBinaryString.reverse.padTo(16, '0').reverse
    }

    // El valor de verificación es la suma final
    finalSum