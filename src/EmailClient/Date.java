package EmailClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public  class  Date {
    private static LocalDate localDate;
    public static String getDate(){
        localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return localDate.format(formatter);
    }
}
