import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class EvalVisitor extends CalculatorBaseVisitor<Double> {

    @Override
    public Double visitMulDiv(CalculatorParser.MulDivContext ctx) {
        double left = visit(ctx.expr(0));  // Obtener el valor de la subexpresión izquierda
        double right = visit(ctx.expr(1)); // Obtener el valor de la subexpresión derecha
        String op = ctx.getChild(1).getText(); // Obtener el texto del operador

        switch (op) {
            case "*":
                return left * right;
            case "/":
                if (right == 0) {
                    System.err.println("Error: División por cero.");
                    return Double.NaN; // Retorna NaN en caso de división por cero
                }
                return left / right;
            default:
                throw new IllegalArgumentException("Operador no soportado: " + op);
        }
    }

    @Override
    public Double visitAddSub(CalculatorParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));  // Obtener el valor de la subexpresión izquierda
        double right = visit(ctx.expr(1)); // Obtener el valor de la subexpresión derecha
        String op = ctx.getChild(1).getText(); // Obtener el texto del operador

        switch (op) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            default:
                throw new IllegalArgumentException("Operador no soportado: " + op);
        }
    }

    @Override
    public Double visitInt(CalculatorParser.IntContext ctx) {
        return Double.valueOf(ctx.INT().getText());
    }

    @Override
    public Double visitFloat(CalculatorParser.FloatContext ctx) {
        return Double.valueOf(ctx.FLOAT().getText());
    }

    @Override
    public Double visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr()); // Regresar el valor dentro de los paréntesis
    }
}
