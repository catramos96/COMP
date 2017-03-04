
public class Main {

	public static Seq seq = new Seq();
	public static Token token = null;
	
	public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("java Main <String>");
            return;
        }
        
        /*
         * Lexical
         */
        if(lexicalAnalyser(args[0]) == false)
        {
        	System.out.println("Rejected on the lexicalAnalyser");
        	return;
        }
        else
        	System.out.println("Accepted on the lexicalAnalyser");
        
        /*
         * Syntax
         */
        if(syntaxAnalyser() == false){
        	System.out.println("Rejected on the syntaxAnalyser");
        	return;
        }
        else
        	System.out.println("Accepted on the syntaxAnalyser");
    }
	
	/*
	 * LexicalAnalyser
	 */
	public static boolean lexicalAnalyser(String input){
		
		String[] elements = input.split("");		//split in tokens
		int id;
		
		for(String elem : elements){
			
			if((id = TokenIDs.getId(elem)) == -1)	//analyze if elem is in [+,-,*,/,INT]
				return false;
			
			seq.addToken(new Token(elem,id));		//add token to seq
		}
		return true;
	}
	
	/*
	 * Syntax Analyser
	 * Start -> Expr
	 */
	public static boolean syntaxAnalyser(){
		//System.out.println("Start");
		token = seq.nextToken();
		return  Expr();
	}
	
	/*
	 * Expr -> Term ExprP
	 */
	public static boolean Expr(){
		//System.out.println("Expr");
		return(Term() && ExprP());
	}
	
	/*
	 * ExprP -> "+" Term ExprP
	 * ExprP -> "-" Term ExprP
	 * ExprP -> e
	 */
	public static boolean ExprP(){
		//System.out.println("ExprP");
		
		if(token == null)
			return true;
		
		if(token.ID == TokenIDs.ADD || token.ID == TokenIDs.SUB){		
			//System.out.println("ADD/SUB");
			token = seq.nextToken();
			return (Term() && ExprP());
		}
		
		return true;
	}
	
	/*
	 * Term -> INT TermP
	 */
	public static boolean Term(){
		//System.out.println("Term");
		
		if(token.ID == TokenIDs.INT){
			//System.out.println("INT");
			token = seq.nextToken();
			return TermP();
		}
		else		
			return false;
	}
	
	/*
	 * TermP -> "*" INT TermP
	 * TermP -> "/" INT TermP
	 * TermP -> e
	 */
	public static boolean TermP(){
		//System.out.println("TermP");
		
		if(token == null)
			return true;
		
		if(token.ID == TokenIDs.MULT || token.ID == TokenIDs.DIV){
			//System.out.println("MULT/DIV");
			token = seq.nextToken();
			
			if(token.ID != TokenIDs.INT)
				return false;
			
			//System.out.println("INT");
			token = seq.nextToken();
			return TermP();
		}
		return true;
	}
}
