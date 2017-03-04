
public class TokenIDs {
	public static int INT = 1;
	public static int ADD = 2;
	public static int MULT = 3;
	public static int DIV = 4;
	public static int SUB = 5;

	public static int getId(String input){
		if(input.matches("[0-9]+"))
				return INT;
		switch(input){
		case "+":
			return ADD;
		case "*":
			return MULT;
		case "/":
			return DIV;
		case "-":
			return SUB;
		default:
			return -1;
		}
	}
}
