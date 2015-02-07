package util;

import java.util.List;

public class StringUtil {

	public static String joinArray(List<String> list, char splitChar) {
		if (list == null || list.size() <= 0) {
			return "";
		}

		StringBuffer result = new StringBuffer(45 * list.size());
		for (String item: list) {
			if (item == null) {
				continue;
			}

			result.append(item).append(splitChar);
		}
		return result.substring(0, result.length() -1);
	}
}
