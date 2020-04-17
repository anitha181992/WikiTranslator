package parser;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
/**
 * Parser for the <section> tag
 * @author Anitha
 *
 */
public class SectionParser implements Parser, Patterns {

	@Override
	public String parse(String inputLine, Map<String, Integer> map) {
		String newLine = null;
		String result = null;
		if (map.containsKey(Patterns.section)) {
			map.replace(Patterns.section, map.get(Patterns.section) + 1);
		} else {
			map.put(Patterns.section, 1);
		}
		int count = map.get(Patterns.section);
		String pattern = "=";
		String countpattern = StringUtils.repeat(pattern, count);
		newLine = inputLine.replace("<section heading=\"", countpattern);
		newLine = newLine.replace("\">", countpattern);
		result = newLine;
		return result;
	}

	@Override
	public String parse(String inputLine) {
		// TODO Auto-generated method stub
		return null;
	}
}
