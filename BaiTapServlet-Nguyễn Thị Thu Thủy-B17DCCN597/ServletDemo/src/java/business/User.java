/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class User {
 private String firstName;
 private String lastName;
 private String emailAddress;

 public User()
 {
 firstName = "";
 lastName = "";
 emailAddress = "";
}
 public User(String firstName, String lastName,
 String emailAddress)
 {
 this.firstName = firstName;
 this.lastName = lastName;
 this.emailAddress = emailAddress;
 }

 public void setFirstName(String firstName)
 {
 this.firstName = firstName;
 }
 public String getFirstName()
 {
 return firstName;
 }
 public void setLastName(String lastName)
 {
 this.lastName = lastName;
 }
 public String getLastName()
 {
 return lastName;
 }

 public void setEmailAddress(String emailAddress)
 {
 this.emailAddress = emailAddress;
 }
 public String getEmailAddress()
 {
 return emailAddress;
 }
}
