class SingleSum extends CheckSum:
  override def checkSum(data: List[String]): String =
    // Inicializar la suma
    val initSum = "0" * data.head.length

    // Iterar a través de cada bloque en la palabra de datos
    val finalSum = data.foldLeft(initSum) { (sum, block) =>
      // Calcular la nueva suma
      val newSum = binarySum(sum, block)

      // Convertir la suma a un número entero
      val sumInt = Integer.parseInt(newSum, 2)

      // Realizar la operación de módulo
      val modSum = sumInt % ((1 << 16) - 1)

      // Convertir el resultado de nuevo a una cadena binaria
      modSum.toBinaryString.reverse.padTo(newSum.length, '0').reverse
    }

    // El valor de verificación es la suma final
    finalSum