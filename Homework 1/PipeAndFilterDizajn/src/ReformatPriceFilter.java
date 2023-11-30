public class ReformatPriceFilter implements Filter<String>{
    @Override
    public String execute(String input) {
        String[] columns = input.split(",");
        columns[3] = columns[3].split(" ")[0];
        return String.join(",", columns);
    }
}
