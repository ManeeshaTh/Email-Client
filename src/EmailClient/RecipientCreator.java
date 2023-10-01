package EmailClient;

public class RecipientCreator {
    public static Recipient create(String recipientType, String data[]) {
        String name = data[0];

        Recipient rc = null;

        switch (recipientType) {
            case "Official":
                String email = data[1];
                String designation = data[2];

                rc = new Official(name, email, designation);
                break;

            case "Office_friend":
                String emailOF = data[1];
                String designationOF = data[2];
                String DOB = data[3];

                rc = new OfficeFriend(name, emailOF, designationOF, DOB);
                break;

            case "Personal":
                String nickname = data[1];
                String emailAddress = data[2];
                String DOBP = data[3];

                rc = new Personal(name, nickname, emailAddress, DOBP);
                break;

            default:
                System.out.println("Incorrect Input Format");
                break;
        }
        return rc;
    }
}










