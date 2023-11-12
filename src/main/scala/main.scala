/**abstract class CheckSum:

  def checkSum(blocks: List[String]): String

    // Definimos la función binarySum que toma dos cadenas de caracteres
  def binarySum(a: String, b: String): String =
    // Asegurarse de que ambas cadenas tengan la misma longitud
    val maxLength = math.max(a.length, b.length)
    val aPadded = a.reverse.padTo(maxLength, '0').reverse
    val bPadded = b.reverse.padTo(maxLength, '0').reverse

    // Definimos una función interna addBinaryStrings que realiza la suma binaria
    // Esta función toma dos listas de caracteres (los números binarios) y un entero (el acarreo)
    def addBinaryStrings(a: List[Char], b: List[Char], carry: Int): String = (a, b) match
      // Si ambas listas están vacías, devolvemos "" si no hay acarreo, o "1" si hay acarreo
      case (Nil, Nil) => if (carry == 0) "" else "1"
      // Si ambas listas tienen elementos, sumamos los dígitos correspondientes y el acarreo
      // Luego llamamos recursivamente a addBinaryStrings con el resto de las listas y el nuevo acarreo
      case (x :: xs, y :: ys) =>
        val sum = x.asDigit + y.asDigit + carry
        (sum % 2).toString + addBinaryStrings(xs, ys, sum / 2)
      // En cualquier otro caso (que no debería ocurrir si las cadenas de entrada son de la misma longitud), devolvemos ""
      case _ => ""

    // Llamamos a addBinaryStrings con las cadenas de entrada convertidas a listas y revertidas, y un acarreo inicial de 0
    // Finalmente, revertimos la cadena resultante porque la suma se realiza de derecha a izquierda
    addBinaryStrings(a.reverse.toList, b.reverse.toList, 0).reverse

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

class DualSum extends CheckSum:
  override def checkSum(data: List[String]): String =
    // Inicializar las sumas
    val initSumA = "0" * 8
    val initSumB = "0" * 8

    // Iterar a través de cada bloque en la palabra de datos
    val (finalSumA, finalSumB) = data.foldLeft((initSumA, initSumB)) { case ((sumA, sumB), block) =>
      // Calcular la nueva suma de A
      val newSumA = binarySum(sumA, block)
      val sumAInt = Integer.parseInt(newSumA, 2)
      val modSumA = sumAInt % 255
      println(modSumA)
      val finalSumA = modSumA.toBinaryString.reverse.padTo(8, '0').reverse
      println(finalSumA)
      // Calcular la nueva suma de B
      val newSumB = binarySum(sumB, finalSumA)
      val sumBInt = Integer.parseInt(newSumB, 2)
      val modSumB = sumBInt % 255
      println(modSumB)
      val finalSumB = modSumB.toBinaryString.reverse.padTo(8, '0').reverse
      println(finalSumB)
      (finalSumA, finalSumB)
    }

    // El valor de verificación es la suma final
    finalSumB + finalSumA

val singleSum = new SingleSum
singleSum.checkSum(List("01010111", "01101111", "01110010", "01101100", "01100100"))

val dualSum = new DualSum
dualSum.checkSum(List("01010111", "01101111", "01110010", "01101100", "01100100"))*/