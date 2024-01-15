package q2;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Executor {

	private final List<Command> commands;

	private final DataBase db = DataBase.getInstance();

	public Executor(List<Command> commands) {
		this.commands = commands;
	}

	public void execute() {
		for (Command c : this.commands) {
// 			System.out.println("c: " + c);
			if (c instanceof LoadCommand) {
				loadFrom((LoadCommand) c);
			} else if (c instanceof SaveCommand) {
				saveTo((SaveCommand) c);
			}
		}
	}

	/**
	 * save the persons from the database to the xml file
	 * 
	 * @param sac
	 */
	private void saveTo(SaveCommand sac) {

		List<Person> persons = this.db.load(sac.getKey());

		File f = new File(sac.getFileName());
		if (f.exists()) {
			f.delete();
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			// TODO
			// ########## YOUR CODE STARTS HERE ##########

//			// Create root element "persons"
			Element rootElement = doc.createElement("persons");
			doc.appendChild(rootElement);
//
//			// Iterate over the list of Person objects
//			for (Person person : persons) {
//				// Create "person" element for each Person
//				Element personElement = doc.createElement("person");
//				rootElement.appendChild(personElement);
//
//				// Add child elements for each property of Person
//				Element nameElement = doc.createElement("name");
//				nameElement.appendChild(doc.createTextNode(person.getName()));
//				personElement.appendChild(nameElement);
//
//				Element genderElement = doc.createElement("gender");
//				genderElement.appendChild(doc.createTextNode(person.getGender()));
//				personElement.appendChild(genderElement);
//
//				Element ageElement = doc.createElement("age");
//				ageElement.appendChild(doc.createTextNode(Integer.toString(person.getAge())));
//				personElement.appendChild(ageElement);
//
//				Element occupationElement = doc.createElement("occupation");
//				occupationElement.appendChild(doc.createTextNode(person.getOccupation()));
//				personElement.appendChild(occupationElement);
//			}

			// ########## YOUR CODE ENDS HERE ##########

			Transformer transformer = TransformerFactory.newInstance().newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");

			// INDENT the xml file is optional, you can
			// uncomment the following statement if you would like the xml files to be more
			// readable
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * load the persons from the xml file into the database
	 * 
	 * @param lc
	 */
	private void loadFrom(LoadCommand lc) {

		List<Person> persons = new LinkedList<>();

		File f = new File(lc.getFileName());
		if (!f.exists()) {
			return;
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(f);

			doc.getDocumentElement().normalize();

			// TODO
			// ########## YOUR CODE STARTS HERE ##########
//
//			// Get the root element (in this case, "persons")
//			Element root = doc.getDocumentElement();
//
//			// Iterate over all "person" elements
//			NodeList personsNode = root.getElementsByTagName("person");
//			for (int i = 0; i < personsNode.getLength(); i++) {
//				Node personNode = personsNode.item(i);
//
//				if (personNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element personElement = (Element) personNode;
//
//					// Extracting individual fields
//					String name = personElement.getElementsByTagName("name").item(0).getTextContent();
//					String gender = personElement.getElementsByTagName("gender").item(0).getTextContent();
//					int age = Integer.parseInt(personElement.getElementsByTagName("age").item(0).getTextContent());
//					String occupation = personElement.getElementsByTagName("occupation").item(0).getTextContent();
//
//					// Print or process the data as needed
//					System.out.println("Name: " + name + ", Gender: " + gender + ", Age: " + age + ", Occupation: " + occupation);
//				}
//			}

			// ########## YOUR CODE ENDS HERE ##########

			this.db.save(lc.getKey(), persons);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Command> getCommands() {
		return commands;
	}
}
