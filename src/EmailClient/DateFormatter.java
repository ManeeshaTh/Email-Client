package EmailClient;

public class DateFormatter {
    public static String DateToMMdd(String date){
        //date is in yyyy/MM/dd format
        String data[] = date.split("/");
        //dateMMdd is in MM/dd format
        String dateMMdd = data[1]+"/"+data[2];
        return dateMMdd;
    }
}
