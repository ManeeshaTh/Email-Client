package EmailClient;

public abstract class Recipient {

    private static int RecipientCount = 0;
    private String name;

    public Recipient(String name){
        this.name = name;
        RecipientCount +=1;
    }

    public static int getRecipientCount() {return RecipientCount;}

    public String getName() {return name;}
}
