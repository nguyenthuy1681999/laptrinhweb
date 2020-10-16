package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import business.User;

public class UserIO {
	public static void add(User user, String filepath) throws IOException {
		File file = new File(filepath);
		PrintWriter out = new PrintWriter(new FileWriter(file, true));
		out.println(user.getEmailAddress() + "|" + user.getFirstName() + "|" + user.getLastName());
		out.close();
	}
	
	public static List<User> getAll(String path){
		List<User> users = new ArrayList<>();
		try {
			File file = new File(path);
			if (! file.exists()) {
				return null;
			}
			
			Scanner in = new Scanner(new FileReader(file));
			while(in.hasNext()) {
				String userData = in.nextLine();
				String[] fields = userData.split("\\|");
				if (fields.length == 3) {
					String fName = fields[1];
					String lName = fields[2];
					String email = fields[0];
					User u = new User(fName, lName, email);
					users.add(u);
				}
			}
			return users;
		} catch (FileNotFoundException e) {
			System.err.println(UserIO.class.getName() +"\nDetail: "+ e.getMessage());
		}
		return null;
	}
	
	public static User getByEmail(String email, String path) {
		List<User> userlist = getAll(path);
		if (userlist != null) {
			for (User u : userlist) {
				if ((u.getEmailAddress()).equals(email)) {
					return u;
				}
			}
		}
		return null;
	}
}
