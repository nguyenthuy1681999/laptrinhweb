package business;

import java.io.Serializable;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String emailAddress;

    public User() {
        firstName = "";
        lastName = "";
        emailAddress = "";
    }

    public User(String firstName, String lastName,
            String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
