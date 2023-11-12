package checkSumExperiment

abstract class CheckSum:

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
    addBinaryStrings(aPadded.reverse.toList, bPadded.reverse.toList, 0).reverse