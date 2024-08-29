import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Calculator {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            try {
                // Crear un input desde la línea actual
                CharStream input = CharStreams.fromString(line);

                // Crear el lexer y parser
                CalculatorLexer lexer = new CalculatorLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                CalculatorParser parser = new CalculatorParser(tokens);

                // Parsear la expresión
                ParseTree tree = parser.expr();

                // Crear un visitor para evaluar la expresión
                EvalVisitor visitor = new EvalVisitor();
                Double result = visitor.visit(tree);

                if (!result.isNaN()) {
                    System.out.println("Result: " + result);
                } else {
                    System.err.println("Resultado no disponible debido a error.");
                }
            } catch (Exception e) {
                System.err.println("Error al procesar la expresión: " + e.getMessage());
            }
        }
    }
}
