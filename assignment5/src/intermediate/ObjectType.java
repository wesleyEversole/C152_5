/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 6
 * Project for CS 152
 */
package intermediate;

/**
 * 
 * Object types, for proper execution handling SCHEME_LIST is just
 * IMMUTABLE_METHOD
 * 
 */
public enum ObjectType {
	UNKNOWN, // unknown type
	FORM_LIST, // a tree<TopLvlItem> where left is car(list) and right is
				// cdr(list)
	BUILTIN_PROC, // base scheme immutable functions
	SCHEME_NUMBER, // number complex real rational integer
	SCHEME_STRING, // string?
	SCHEME_VECTOR, // vector? not implemented
	SCHEME_BOOLEAN, // boolean?
	SCHEME_NULL, // null?
	SCHEME_SYMBOL, // symbol?
	SCHEME_CHAR, // char?
	SCHEME_PAIR, // pair? not implemented
	SCHEME_PROCEDURE, // procedure?
}