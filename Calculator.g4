grammar Calculator;

expr:   expr ('*'|'/') expr     # MulDiv
    |   expr ('+'|'-') expr     # AddSub
    |   INT                     # Int
    |   FLOAT                   # Float
    |   '(' expr ')'            # Parens
    ;

INT :   [0-9]+ ;
FLOAT : [0-9]* '.' [0-9]+ ; // Decimal
WS  :   [ \t\r\n]+ -> skip ;
