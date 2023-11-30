public class CapitalizeFilter implements Filter<String>{
    public static String capitalizeEveryWord(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s+");

        for (String word : words) {
            if (word.length() > 1) {
                result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
            } else {
                result.append(word).append(" ");
            }
        }
        return result.toString().trim();
    }

    @Override
    public String execute(String input) {
        String[] columns = input.split(",");
        columns[0] = capitalizeEveryWord(columns[0]);
        columns[1] = capitalizeEveryWord(columns[1]);
        return String.join(",", columns);
    }
}
