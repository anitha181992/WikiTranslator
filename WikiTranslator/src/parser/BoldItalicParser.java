package parser;

import java.util.Map;
/**
 * Method to parse <italic> and <bold>
 * @author Anitha
 *
 */
public class BoldItalicParser implements Parser, Patterns {

	@Override
	public String parse(String inputLine) {
		String newLine = null;
		newLine = inputLine.replaceAll(Patterns.bold, "'''");
		newLine = newLine.replaceAll("</bold>", "'''");
		newLine = newLine.replaceAll(Patterns.italic, "''");
		newLine = newLine.replaceAll("</italic>", "''");
		return newLine;
	}

	@Override
	public String parse(String inputLine, Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
