package frontend;

public enum TokenType {
PUNCTUATION, SYMBOL, NUMBER,
OPEN_LIST, CLOSE_LIST,
BANG, STAR, OPEN_BRACKET, CLOSE_BRACKET,
AND, BEGIN, BEGIN0, BREAK_VAR, CASE, COND, CYCLE, DEFINE, 
DELAY, DELAY_LIST_CONS, DO, ELSE, EXTEND_SYNTAX, FOR, 
FREEZE, IF, LAMBDA, LET, LETREC, LET_STAR, MACRO, OBJECT_MAKER, 
OR, QUOTE, REPEAT, SAFE_LETREC, SET_BANG, STREAM_CONS, 
VARIABLE_CASE, WHILE, WRAP, TRUE, FALSE, IGNORE
}