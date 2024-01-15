package q2;

import java.util.LinkedList;
import java.util.List;

public class Parser {

	private final Tokeniser tokeniser;

	public Parser(Tokeniser tokeniser) {
		this.tokeniser = tokeniser;
	}

	public List<Command> parseCmds() {

		List<Command> commands = new LinkedList<>();

		// TODO
		// ########## YOUR CODE STARTS HERE ##########

		// Part 2) Implement the `parseCmds()` method of the `Parser` class to create
		// two types of commands from the tokens:
		//
		//* `LoadCommand` contains 2 parameters,
		// 		a `key` indicating the object_key,
		// 		e.g. persons, and a `fileName` indicating the file_name, e.g. persons.xml.
		//* `SaveCommand` contains two parameters,
		// 		a `key` indicating the object_key,
		// 		e.g. persons, and a `fileName` indicating the file_name, e.g. persons.xml.

		// Latest - got a load/save command "unmatched" to something?
		Token lastLoad = null;
		Token lastKey = null;
		Token lastFileName = null;
		Token lastSave = null;
		// Token lastTerminator = null;

		while (this.tokeniser.hasNext()) {
			var token = this.tokeniser.current();
			if (token==null) continue;
			System.out.println("token ... " + token);

			if (token.getType()==Token.Type.LOAD) {
				if (lastLoad!=null) return null; // ERROR - 2 loads
				lastLoad = token;
			} else if (token.getType()==Token.Type.SAVE) {
				if (lastSave != null) return null; // ERROR - 2 saves
				lastSave = token;
			} else if (token.getType()==Token.Type.TERMINATOR) {
				// no nd check or set - since will check All tokens & reset (error if not all ava)
				// System.out.println("terminate");
				// System.out.println("   lastLoad: " + lastLoad+", lastSave: " + lastSave);
				// System.out.println("   lastKey: " + lastKey+", lastFileName: " + lastFileName);
				if ((lastLoad != null) && (lastSave != null)) return null; // impossible}
				if ((lastLoad == null) && (lastSave == null)) return null; // impossible}
				if (lastKey==null) return null; // also invalid
				if (lastFileName==null) return null; // also invalid

				if (lastLoad!=null) {
					commands.add(new LoadCommand(lastKey.getValue(), lastFileName.getValue()));
				} else {
					commands.add(new SaveCommand(lastKey.getValue(), lastFileName.getValue()));
				}

				// reset
				lastLoad = null;
				lastKey = null;
				lastFileName = null;
				lastSave = null;


			// Param - can only assume correct (if follow load XOR save)
			} else if (token.getType()==Token.Type.PARAMETER) {
				if (lastKey==null) {
					lastKey = token;
				} else if (lastFileName==null){
					lastFileName = token;
				} else {
					return null; // impossible (invalid)
				}
			}


			this.tokeniser.next();


		}



		// ########## YOUR CODE ENDS HERE ##########

		return commands;
	}
}
