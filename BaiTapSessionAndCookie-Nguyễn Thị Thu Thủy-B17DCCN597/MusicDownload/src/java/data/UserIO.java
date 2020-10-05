package data;

import java.io.*;
import business.User;

public class UserIO
{
    public static void add(User user, String filepath) 
    throws IOException
    {
        File file = new File(filepath);
        PrintWriter out = new PrintWriter(
                new FileWriter(file, true));
        out.println(user.getEmailAddress()+ "|"
                + user.getFirstName() + "|"
                + user.getLastName());        
        out.close();
    }

    public static User getUser(String emailAddress, String path) {
         throw new UnsupportedOperationException("Not supported yet."); 
       
    }
}
