import java.util.Vector;

public   class Seq   {
	
	public static Vector<Token>  tokens;
	public static int   i;
	
	public Seq(){
		i = 0;
		tokens = new Vector<Token>();
	}
	
	public Token nextToken() {
		if(i < tokens.size())
			return   (Token) tokens.elementAt(i++);
		else  return null;
	}
	
	public void addToken(Token t) {
		tokens.add(t);
	}
	
	public static void reinit() {
		i=0;
	}
	
	public static void clear() {
		i=0; tokens.clear();
	}

}
