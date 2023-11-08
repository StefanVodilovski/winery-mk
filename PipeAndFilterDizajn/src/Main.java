import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String currentDirectory = System.getProperty("user.dir");
        File file = new File(currentDirectory + File.separator + "src" + File.separator + "resources" + File.separator + "wine.csv");

        BufferedWriter writer = new BufferedWriter(new FileWriter(currentDirectory + File.separator + "src" + File.separator + "resources" + File.separator + "wine_reformat.csv"));
        Scanner scanner = new Scanner(file);

        ReformatPriceFilter reformatPriceFilter = new ReformatPriceFilter();
        ReformatWinaryNameFilter reformatWinaryNameFilter = new ReformatWinaryNameFilter();
        CapitalizeFilter capitalizeFilter = new CapitalizeFilter();

        Pipe<String> pipe = new Pipe<>();
        pipe.addFilter(reformatWinaryNameFilter);
        pipe.addFilter(reformatPriceFilter);
        pipe.addFilter(capitalizeFilter);

        pipe.addFilter(reformatPriceFilter);

        writer.write("Winary,Name,Literage,Price,Image_Url\n");
        boolean flag = true;
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(flag){
                flag = false;
                continue;
            }
            writer.write(pipe.runFilters(line));
            writer.newLine();
            writer.flush();
        }
    }
}