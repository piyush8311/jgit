package objects.commit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class KeyValueListAndMessage {
    private static final String MESSAGE_KEY = "";

    public static Map<String, List<String>> parse(String data) {
        return parse(data, 0, new LinkedHashMap<>());
    }

  private static Map<String, List<String>> parse(
      String data, int start, Map<String, List<String>> result) {
      int spaceCharIndex = data.indexOf(' ', start);
      int newLineCharIndex = data.indexOf('\n', start);

      if (spaceCharIndex < 0 || newLineCharIndex < spaceCharIndex) {
          assert(newLineCharIndex == start);
          result.put(MESSAGE_KEY, Collections.singletonList(data.substring(start + 1)));
          return result;
      }

      int end = start;
      do {
          end = data.indexOf('\n', end + 1);
      } while (data.charAt(end + 1) == ' ');

      String key = data.substring(start, spaceCharIndex);

      String value = data.substring(spaceCharIndex + 1, end).replace("\n ", "\n");

      if (result.containsKey(key)) {
        result.get(key).add(value);
      } else {
          result.put(key, new ArrayList<>(Collections.singletonList(value)));
      }

      return parse(data, end+1, result);
    }

    public static String serialize(Map<String, List<String>> kvlm) {
        StringBuilder result = new StringBuilder();
        for (Entry<String, List<String>> entry: kvlm.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();

            if (MESSAGE_KEY.equals(key))
                continue;
            for (String val: value) {
                result.append(key)
                       .append(" ")
                       .append(val.replace("\n", "\n "))
                       .append("\n");
            }
        }

        result.append("\n").append(kvlm.get(MESSAGE_KEY).get(0));

        return result.toString();
    }
}
