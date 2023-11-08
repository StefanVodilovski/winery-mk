public class ReformatWinaryNameFilter implements Filter<String>{


    @Override
    public String execute(String input) {
        input = input.replace("\"","");
        String[] columns = input.split(",");
        for(int i=0;i<columns.length - 1; i++){
            columns[i] = columns[i].toLowerCase().trim();
        }
        switch (columns[0]){
            case "bovin": columns[0] = "Bovin Winary";
                break;
            case "chateau sopot": columns[0] = "Chateau Sopot Winary";
                break;
            case "chateau kamnik": columns[0] = "Kamnik Winary";
                break;
            case "old sschool winery": columns[0] = "Old School Winary";
                break;
            case "skovin": columns[0] = "Skovin Winary";
                break;
            case "imako vino": columns[0] = "Imako Vino Winary";
                break;
            case "château de gourdon": columns[0] = "Tikveš Châteaux & Domaines";
                break;
            case "domaine lepovo": columns[0] = "Tikveš Châteaux & Domaines";
                break;
            case "domaine bela voda": columns[0] = "Tikveš Châteaux & Domaines";
                break;
            case "domaine barovo": columns[0] = "Tikveš Châteaux & Domaines";
                break;
            case "domaine babuna": columns[0] = "Tikveš Châteaux & Domaines";
                break;
            default: break;
        }
        return String.join(",", columns);
    }
}
