import java.util.Stack;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Solicitar al usuario que ingrese una expresión infija
        System.out.println("Ingresa una expresión infija:");
        String expresionInfija = scan.nextLine();

        // Llamar a la función infijaASufija para convertir la expresión
        String postfixExpression = infijaASufija(expresionInfija);

        // Imprimir la expresión convertida en notación sufija
        System.out.println("Expresión sufija (posfija):");
        System.out.println(postfixExpression);

        System.out.println("\n\nBeltran Calvo Brayan \n#222217084");
    }

    // Función para convertir una expresión infija en sufija
    public static String infijaASufija(String expresionInfija) {
        String sufija = ""; // Variable para almacenar la expresión sufija
        Stack<Character> pila = new Stack<>(); // Pila para operadores

        for (int i = 0; i < expresionInfija.length(); i++) {
            char caracter = expresionInfija.charAt(i);

            // Si el carácter actual es un paréntesis de apertura, '('
            if (caracter == '(') {
                pila.push(caracter);
            }

            // Si el carácter actual es un paréntesis de cierre, ')'
            else if (caracter == ')') {
                // Desapilar y agregar operadores hasta encontrar el paréntesis de apertura correspondiente '('
                while (!pila.isEmpty() && pila.peek() != '(') {
                    sufija += pila.pop() + " ";
                }
                pila.pop(); // Desapilar el '(' de la pila
            }

            // Si el carácter actual es un operador (+, -, *, /)
            else if (operador(caracter)) {
                // Desapilar y agregar operadores con mayor o igual precedencia
                while (!pila.isEmpty() && precedencia(pila.peek()) >= precedencia(caracter)) {
                    sufija += pila.pop() + " ";
                }
                pila.push(caracter); // Apilar el operador actual
            }

            // Si el carácter actual es un dígito (operando)
            else if (Character.isDigit(caracter)) {
                String operando = "";
                // Construir el operando completo
                while (i < expresionInfija.length() && (Character.isDigit(expresionInfija.charAt(i)) || expresionInfija.charAt(i) == '.')) {
                    operando += expresionInfija.charAt(i);
                    i++;
                }
                i--; // Decrementar i para corregir el incremento adicional en el bucle
                sufija += operando + " ";
            }
        }

        // Desapilar y agregar cualquier operador restante en la pila
        while (!pila.isEmpty()) {
            sufija += pila.pop() + " ";
        }

        return sufija.trim(); // Devolver la expresión sufija sin espacios adicionales
    }

    // Función para verificar si un carácter es un operador
    public static boolean operador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Función para determinar la precedencia de un operador
    public static int precedencia(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
}
    //Beltran Calvo Brayan #222217084