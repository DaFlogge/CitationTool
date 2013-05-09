package control;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Utility {

	private Utility() {
	}

	public static String getContent(URL url) throws IOException {
		StringBuilder content = new StringBuilder();
		URLConnection c = url.openConnection();
		c.setReadTimeout(30000);
		Scanner s = new Scanner(c.getInputStream());
		while (s.hasNext()) {
			content.append(s.nextLine() + "\n");
		}
		s.close();
		return content.toString();
	}
	
	public static String getFirstOccurence(String prefix, String suffix,
			String content) {
		int pref = content.indexOf(prefix);
		if (pref < 0) {
			return null;
		}
		int suf = content.indexOf(suffix, pref + prefix.length());
		if (suf < 0) {
			return null;
		}
		return content.substring(pref + prefix.length(), suf).trim();
	}

	public static List<String> getAllOccurences(String prefix, String suffix, String content) {
		List<String> list = new LinkedList<String>();
		int suf = -1;
		int pref = content.indexOf(prefix);
		if (pref != -1) {
			suf = content.indexOf(suffix, pref + prefix.length());	
		}
		while (pref != -1 && suf != -1) {
			list.add(content.substring(pref + prefix.length(), suf).trim());
			int startFrom = suf + suffix.length();
			
			// check if start position is still inside content
			if (suf >= content.length()) {
				suf = - 1;
				pref = -1;
			} else {
				pref = content.indexOf(prefix, startFrom);
				if (pref != -1) {
					suf = content.indexOf(suffix, pref + prefix.length());	
				}
			}
		}
		return list;
	}
}