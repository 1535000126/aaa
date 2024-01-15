package q2;

import static q2.Token.Type.*;

public class Tokeniser {

	private String buffer; // save text
	private Token currentToken; // save token extracted from next()

	public Tokeniser(String text) {
		buffer = text; // save input text (string)
		next(); // extracts the first token.
	}

	/**
	 * This function will find and extract a next token from {@code buffer} and save
	 * the token to {@code currentToken}.
	 */
	public void next() {

		buffer = buffer.trim(); // remove whitespace
		if (buffer.isEmpty()) {
			currentToken = null; // if there's no string left, set currentToken null and return
			return;
		}

		// TODO [Cathy]
		// ########## YOUR CODE STARTS HERE ##########
		//`LOAD` object_key `FROM` file_name;
		// `SAVE` object_key `TO` file_name;
		//
		// ... types: 		// LOAD, FROM, SAVE, TO, TERMINATOR, PARAMETER;
		// TODO !!! tokenizer testCase didnt catch e.g. 'persons.xml;LOAD'


		// TODO - terminatr as special case now (cuz as delimiter - AWAY)
		if (buffer.charAt(0)==';') {
			currentToken = new Token(TERMINATOR, ";");
		} else {
			int nextSplitByTerminator = buffer.indexOf(';')!=-1 ? buffer.indexOf(';') :buffer.length();
			int nextSplitBySpace = buffer.indexOf(' ')!=-1 ? buffer.indexOf(' ') :buffer.length();
			int nextSplit = Math.min(nextSplitByTerminator, nextSplitBySpace);

			String tokenStr = buffer.substring(0, nextSplit);

			// System.out.println("tokenStr: " + tokenStr);

			// Token Types (*keywords not case sensitive)
			if (tokenStr.equalsIgnoreCase("LOAD")) {
				currentToken = new Token(LOAD, tokenStr);

			} else if (tokenStr.equalsIgnoreCase("SAVE")) {
				currentToken = new Token(SAVE, tokenStr);

			} else if (tokenStr.equalsIgnoreCase("FROM")) {
				currentToken = new Token(FROM, tokenStr);

			} else if (tokenStr.equalsIgnoreCase("TO")) {
				currentToken = new Token(TO, tokenStr);

				// ";"
//			} else if (tokenStr.equals(";")) {
//				currentToken = new Token(TERMINATOR, tokenStr);

//			// file_name;
//		} else if (tokenStr.endsWith(";")) {
//			currentToken = new Token(PARAMETER, tokenStr);	// no longer "attached with" ;
////			currentToken = new Token(PARAMETER, tokenStr.substring(0, tokenStr.length()-1)); // EXCL ";" ! next ALONE

				// object_key				// no longer "attached with" ;
				// OR file_name
			} else {
				currentToken = new Token(PARAMETER, tokenStr);

			}

		}






			// ########## YOUR CODE ENDS HERE ##########

		// Remove the extracted token from buffer
		int tokenLen = currentToken.getValue().length();
		buffer = buffer.substring(tokenLen);
	}

	/**
	 * returned the current token extracted by {@code next()} **** please do not
	 * modify this part ****
	 * 
	 * @return type: Token
	 */
	public Token current() {
		return currentToken;
	}

	/**
	 * check whether there still exists another tokens in the buffer or not ****
	 * please do not modify this part ****
	 * 
	 * @return type: boolean
	 */
	public boolean hasNext() {
		return currentToken != null;
	}

}