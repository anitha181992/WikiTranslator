package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Main Class
 * Input: Path of input directory, Path of output directory
 * Output: Creates new wiki files
 * @author Anitha
 *
 */
public class Mainprocessor implements Patterns {

	public static void main(String[] args) {

		BufferedReader buffereader;
		BufferedWriter bufferwriter;
		Scanner scanner;
		String nameofInput = null;
		File fileName = null;

		try {
			scanner = new Scanner(System.in);
			System.out.print("Enter the location of input directory name: ");
			nameofInput = scanner.nextLine().trim();

			File directory = new File(nameofInput);
			File[] files = directory.listFiles();
			for (int i = 0; i < files.length; i++) {
				String nameofFile = files[i].getName();
				if (nameofFile.endsWith("xml")) {

					fileName = files[i];
					System.out.print("Enter the location of output directory name: ");
					String nameofOutput = scanner.nextLine();
					File outputfile = getOutputFile(nameofOutput, fileName.getName());
					bufferwriter = new BufferedWriter(new FileWriter(outputfile));

					buffereader = new BufferedReader(new FileReader(fileName));
					buffereader.readLine();
					buffereader.readLine();
					String inputLine = null;
					String result = null;
					Map<String, Integer> map = new HashMap<String, Integer>();

					while ((inputLine = buffereader.readLine()) != null) {

						Pattern special = Pattern.compile("[=<>]");
						Matcher m = special.matcher(inputLine);
						boolean b = m.find();
						if (!b) {
							result = inputLine;
						} else {
							if (inputLine.contains(Patterns.section)) {
								SectionParser parser = new SectionParser();
								result = parser.parse(inputLine, map);
							} else if (inputLine.contains(Patterns.endsection)) {
								map.replace(Patterns.section, map.get(Patterns.section) - 1);
								continue;
							} else if (inputLine.contains(Patterns.bold) || inputLine.contains(Patterns.italic)) {
								BoldItalicParser parser = new BoldItalicParser();
								result = parser.parse(inputLine);
							} else if (inputLine.contains(Patterns.endreport)) {
								break;
							}
						}
						bufferwriter.write(result.trim());
						bufferwriter.newLine();
					}
					bufferwriter.close();
				} else {
					continue;
				}
			}

		} catch (Exception ex) {

		}

	}

	public static File getOutputFile(String outdirectory, String name) throws IOException {
		String fileSeparator = System.getProperty("file.separator");
		String replacedname = name.replaceAll(".xml", ".wiki");
		String path = outdirectory + fileSeparator + replacedname;
		File file = new File(path);
		if (file.createNewFile()) {
			System.out.println("Created a new wiki file");
		}
		return file;
	}
}
