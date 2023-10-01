package EmailClient;
// Index number: 200656T

//import libraries
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Email_Client {
    private static HashMap<BDayGreetable,String> recordsBirthdate;

    //Constructor to instantiate the hashmap recordsBirthday
    Email_Client(){
        recordsBirthdate = new HashMap<BDayGreetable,String>();
    }

    public static void main(String[] args) throws IOException {

        ////////////////  Create recipient objects from clientList.txt file at beginning, throws exception if empty
        new Email_Client();
        createRecipientsFromFile();
        ////////////////

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application");

            int option = scanner.nextInt();
            scanner.nextLine(); // This line consumes the \n character to use nextLine() later.

            switch (option) {
                case 1:
                    // input format - Official: nimal,nimal@gmail.com,ceo
                    System.out.println("Enter recipient details: \n");
                    // Use a single input to get all the details of a recipient
                    String entry1 = scanner.nextLine();
                    // code to add a new recipient
                    createRecipient(entry1);
                    // store details in clientList.txt file
                    addEntryToFile(entry1);
                    break;
                case 2:
                    // input format - email, subject, content
                    System.out.println("Enter recipient email address: \n");
                    String entry2email = scanner.nextLine();
                    System.out.println("Enter email subject: \n");
                    String entry2subject = scanner.nextLine();
                    System.out.println("Enter email content: \n");
                    String entry2content = scanner.nextLine();

                    //to send emails and store of serialized Email objects in HardDisk
                    SendEmailTLS.sendMail(entry2email,entry2subject,entry2content);
                    break;

                case 3:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print recipients who have birthdays on the given(current) date

                    String currentDate = Date.getDate();
                    //Iterate over hashmap of Email_Client to print names of recipients birthday set to current date
                    for (BDayGreetable i : recordsBirthdate.keySet()) {
                        if (recordsBirthdate.get(i).equals(DateFormatter.DateToMMdd(currentDate))){
                            System.out.println(i.getName());
                        }
                    }
                    System.out.println("Task completed");
                    break;

                case 4:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    System.out.println("Enter date to be checked for sent emails: \n");
                    String entry4Date = scanner.nextLine();

                    //make filename yyyyMMdd
                    String a = entry4Date;
                    a = a.replace("/","");
                    String filename = a + ".ser";

                    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))){
                        Object obj = null; //issue maybe EoFException

                        while ((obj=inputStream.readObject()) != null){
                            if (obj instanceof Email) {
                                Email retrievedEmail = (Email) obj;
                                System.out.println(retrievedEmail.getToEmail());
                                System.out.println(retrievedEmail.getSubject());
                            }
                        }
                    }
                    catch (FileNotFoundException ex){
                        System.out.println("No mails sent on that date");
                    }
                    catch (Exception e){
                        System.out.println("Exception thrown reading serialized object");
                    }

                    break;

                case 5:
                    // code to print the number of recipient objects in the application
                    int count = Recipient.getRecipientCount();
                    System.out.println(count);

                    break;

                default:
                    System.out.println("Incorrect Input");
            }

        scanner.close();

        // start email client
        //I chose to create the objects from list at beginning to avoid duplicate objects from case 1:add recipient
        //and to give correct count of recipients for case 5

        //Run the email_client to send birthday emails
        // Iterate through hashmap and send greetings for relevant recipients
        for (BDayGreetable i : recordsBirthdate.keySet()) {
            String currentDate = Date.getDate();
            if (recordsBirthdate.get(i).equals(DateFormatter.DateToMMdd(currentDate))){
                BirthdayGreeting.sendGreeting(i);
            }
        }
    }

    public static void createRecipientsFromFile(){

        try {
            FileReader reader = new FileReader("clientList.txt");
            BufferedReader reader1 = new BufferedReader(reader);
            String line = null;
            while ((line = reader1.readLine()) != null) {
                createRecipient(line);                 //create objects
            }
            reader.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void createRecipient(String str) {

        //parse the input accordingly
        String parts[] = str.split(":");
        String recipientType = parts[0];

        String data[] = parts[1].trim().split(",");

        Recipient object = RecipientCreator.create(recipientType,data);

        //Update to hashmap if object implements BDayGreetable
        if (object instanceof BDayGreetable){
            BDayGreetable bg =(BDayGreetable)object;
            String BirthDate = bg.getBDAY();
            recordsBirthdate.put(bg,BirthDate);
        }
    }

    public static void addEntryToFile(String entry1){
        try {
            FileWriter writer = new FileWriter("clientList.txt", true);
            writer.write(entry1 + "\n");
            writer.close();
            System.out.println("successfully written to file");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}



// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)