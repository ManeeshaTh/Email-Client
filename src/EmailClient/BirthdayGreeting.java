package EmailClient;

public class BirthdayGreeting {
    public static void sendGreeting(BDayGreetable BG){

        if (BG instanceof Personal){
            sendRelevantGreeting((Personal)BG);
        }
        else if (BG instanceof OfficeFriend){
            sendRelevantGreeting((OfficeFriend) BG);
        }

        //can append if-else statement as new classes implement BDayGreetable
        //to send relevant birthday greeting for class type
    }

    public static void sendRelevantGreeting(OfficeFriend OF){
        String email = OF.getEmail();
        String subject = "Birthday Greeting";
        String content ="Wish you a Happy Birthday. M";

        SendEmailTLS.sendMail(email,subject,content);
    }

    public static void sendRelevantGreeting(Personal P){
        String email = P.getEmail();
        String subject = "Birthday Greeting";
        String content ="Hugs and love on your birthday. M";

        SendEmailTLS.sendMail(email,subject,content);
    }

}
