package ex3e;

import java.io.IOException;
import java.io.PrintWriter;

public class Person {
	
	private static PrintWriter outputFile;

	private String name;
	private String address;
	private int age;
	private String phone;
	
	public Person(String name, String address, int age, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.age = age;
		this.phone = phone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(this.name);
		str.append(", ");
		str.append(this.address);
		str.append(", Age: ");
		str.append(this.age);
		str.append(", ");
		str.append(this.phone);
		return str.toString();
	}	
	
	public void write (){
		/* Write to file (Part 2 of 3): Write the name to the file. See Notebook for notes. */
         outputFile.println(this.name);
         outputFile.println(this.address);
         outputFile.println(this.age);
         outputFile.println(this.phone);
	}
	
	/* This person class needs to know how to handle the output file now. 
	 * So we need to add static methods to open and close the file.
	 * openFile - this static method now creates the PrintWriter object.
	 * if it opens the file successfully, then sets the boolean value to true.
	 * If it errors, it will go to the catch (currently without code to handle the exception).
	 * 
	 * closeFile - this static checks to see if the outputFile is open.
	 * It checks this by checking to see if it is NOT = to null. != to null means it's open.
	 * so if it's not open, we don't want to try to close it. That would break the program.
	 * 
	 */
	
	public static boolean openFile(String fileName) {
		boolean fileOpened = false; // assume it didn't open, if it does, we update to true.
		try {
			outputFile = new PrintWriter(fileName);
			fileOpened = true;
		}
		catch (IOException e) {}
		return fileOpened;
	}
	
	public static void closeFile() {
		if (outputFile != null) outputFile.close();
		
	}
}
