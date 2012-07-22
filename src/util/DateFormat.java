package util;

public class DateFormat {

    final static String[] meses = new String[]{"ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"};

    public static String format(String date) {
        String[]split=date.split("-");
        return (split[2].length()==1?"0"+split[2]:split[2])+"-"+meses[Integer.valueOf(split[1])-1]+"-"+split[0];
    }
}
