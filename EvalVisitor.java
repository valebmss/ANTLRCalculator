import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class EvalVisitor extends CalculatorBaseVisitor<Integer> {

    @Override
    public Integer visitMulDiv(CalculatorParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));  // Obtener el valor de la subexpresión izquierda
        int right = visit(ctx.expr(1)); // Obtener el valor de la subexpresión derecha
        String op = ctx.getChild(1).getText(); // Obtener el texto del operador
        if (op.equals("*")) {
            return left * right;
        } else {
            return left / right;
        }
    }

    @Override
    public Integer visitAddSub(CalculatorParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));  // Obtener el valor de la subexpresión izquierda
        int right = visit(ctx.expr(1)); // Obtener el valor de la subexpresión derecha
        String op = ctx.getChild(1).getText(); // Obtener el texto del operador
        if (op.equals("+")) {
            return left + right;
        } else {
            return left - right;
        }
    }

    @Override
    public Integer visitInt(CalculatorParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr()); // Regresar el valor dentro de los paréntesis
    }
}
