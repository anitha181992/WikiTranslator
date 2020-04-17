package parser;

import java.util.Map;
/**
 * Parser interface to add the parse methods
 * Input: Line of the file read
 * @author Anitha
 *
 */

public interface Parser {
	
	public String parse(String inputLine);
	
	public String parse(String inputLine, Map<String, Integer> map);

}
