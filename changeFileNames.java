import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.File;

public class changeFileNames
{
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);

		String curFilePath;
		String curfile;

		// TODO Option to give directory, use current directory or a default
		String directory = getDirectory() + "\\";
		System.out.println("\n\n Using directory: " + directory);

		File[] files = new File(directory).listFiles();

		ArrayList<File> readingFiles = getReadingFiles(files);

		if(readingFiles.size() > 0) {
			// choose menu
			System.out.println("There are " + readingFiles.size() + " editable files.");
			boolean continueChoosing = true;
			while(continueChoosing) {
				readingFiles = chooseOptions(readingFiles);

				System.out.println("Continue Editing? y/n: ");
				String choice = scan.nextLine();
				if(!choice.toLowerCase().equals("y")) {
					continueChoosing = false;
				}
			}

		} else {
			System.out.println("There are no editable in the directory.");
			System.out.println("Program Aborted.");
		}
	}

	// perform a set of actions on the files
	public static ArrayList<File> chooseOptions(ArrayList<File> files) {
		ArrayList<File> editedFiles = files;
		Scanner scan = new Scanner(System.in);
		String choice;


		System.out.print("\nOptions: (y/n)\n1. Strip from Start: ");
		choice = scan.nextLine();
		if(choice.toLowerCase().equals("y")) {
			System.out.println("Enter an integer length or string to strip: ");
			String str = scan.nextLine();
			editedFiles = stripFromStart(editedFiles, str);
		}

		System.out.print("2. Strip from End: ");
		choice = scan.nextLine();
		if(choice.toLowerCase().equals("y")) {
			System.out.println("Enter an integer length or string to strip: ");
			String str = scan.nextLine();
			editedFiles = stripFromEnd(editedFiles, str);
		}

		System.out.print("3. Append to Start (will automatically add a space): ");
		choice = scan.nextLine();
		if(choice.toLowerCase().equals("y")) {
			System.out.println("Append String: ");
			String str = scan.nextLine();
			editedFiles = appendToStart(editedFiles, str);
		}

		System.out.print("4. Append to End (will automatically add a space): ");
		choice = scan.nextLine();
		if(choice.toLowerCase().equals("y")) {
			String str;
			System.out.println("Append String: ");
		    str = scan.nextLine();
			editedFiles = appendToEnd(editedFiles, str);
		}

		// show users what the files will become when renamed
		System.out.println("Changes: ");
		for(int i = 0; i < editedFiles.size() ; i++) {
			System.out.println(files.get(i) + "\n    --> " + editedFiles.get(i));
		}

		System.out.println("Accept changes? (y/n)");
		choice = scan.nextLine();
		// rename the files
		if(choice.toLowerCase().equals("y")) {
			makeChanges(files, editedFiles);
			return editedFiles;
		}
		return files;

	}

	/* *** File Info
		File file = new File("somePathAndFile.html");
	 	String curfile = file.getName(); 		// eg. fileName.html
		String parent = file.getParent();  		// eg. C:\Users\...currentDir
		String path = file.getPath(); 			// eg. C:\Users\...currentDir\fileName.html
	*/

	// Enter an integer or a string
	public static ArrayList<File> stripFromEnd(ArrayList<File> files, String str) {
		ArrayList<File> editedFiles = new ArrayList<File>();

	 	int length = checkInt(str);

	 	//strip length
	 	if(length > 0) {
	 		for (File file : files){
			 	String curFile = file.getName(); 		// eg. fileName.html
			 	String ending;
			 	if(curFile.endsWith(".html")) {
			 		ending = ".html";
			 		curFile = curFile.substring(0, curFile.length()-5);
			 	} else {
			 		ending = ".htm";
			 		curFile = curFile.substring(0, curFile.length()-4);

			 	}

			 	curFile = curFile.substring(0, curFile.length()-(length));
			 	String parent = file.getParent() + "\\";
			 	File newFile = new File(parent + curFile + ending);
			 	editedFiles.add(newFile);
			}
	 	} else {
	 	// strip string pattern
	 		for (File file : files){
			 	String curFile = file.getName(); 		// eg. fileName.html
			 	String ending;
			 	if(curFile.endsWith(".html")) {
			 		ending = ".html";
			 		curFile = curFile.substring(0, curFile.length()-5);
			 	} else {
			 		ending = ".htm";
			 		curFile = curFile.substring(0, curFile.length()-4);
			 	}

			 	if(curFile.endsWith(str)) {
			 		curFile = curFile.substring(0, curFile.length() - (str.length()));
			 	}
			 	String parent = file.getParent() + "\\";
			 	File newFile = new File(parent + curFile + ending);
			 	editedFiles.add(newFile);
			}
	 	}
		return editedFiles;
	}

	// Enter an integer or a string
	public static ArrayList<File> stripFromStart(ArrayList<File> files, String str) {
		ArrayList<File> editedFiles = new ArrayList<File>();

	 	int length = checkInt(str);

	 	//strip length
	 	if(length > 0) {
	 		for (File file : files){
			 	String curFile = file.getName(); 		// eg. fileName.html
			 	curFile = curFile.substring(length);
			 	String parent = file.getParent() + "\\";
			 	File newFile = new File(parent + curFile);
			 	editedFiles.add(newFile);
			}
	 	} else {
	 	// strip string pattern
	 		for (File file : files){
			 	String curFile = file.getName(); 		// eg. fileName.html

			 	if(curFile.startsWith(str)) {
			 		curFile = curFile.substring(str.length());
			 	}

			 	String parent = file.getParent() + "\\";
			 	File newFile = new File(parent + curFile);
			 	editedFiles.add(newFile);
			}
	 	}
		return editedFiles;
	}

	public static ArrayList<File> appendToStart(ArrayList<File> files, String str) {
		ArrayList<File> editedFiles = new ArrayList<File>();
		for (File file : files){
		 	String parent = file.getParent() + "\\";  		// eg. C:\Users\...currentDir
		 	File newFile = new File(parent + str + " " + file.getName());
		 	editedFiles.add(newFile);
		}
		return editedFiles;
	}


	public static ArrayList<File> appendToEnd(ArrayList<File> files, String str) {
		ArrayList<File> editedFiles = new ArrayList<File>();
		for (File file : files){
		 	String curFile = file.getName();
		 	String ending;
		 	if(curFile.endsWith(".html")) {
		 		ending = ".html";
		 		curFile = curFile.substring(0, curFile.length()-5);
		 	} else {
		 		ending = ".htm";
		 		curFile = curFile.substring(0, curFile.length()-4);
		 	}
		 	String parent = file.getParent() + "\\";
		 	File newFile = new File(parent + curFile + " " + str + ending);
		 	editedFiles.add(newFile);
		}
		return editedFiles;
	}

	// turns string to integer if valid
	public static int checkInt(String str) {
		try {
			int num = Integer.parseInt(str);
			return num;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	// rename the files
	public static void makeChanges(ArrayList<File> oldFiles, ArrayList<File> newFiles) {
		for(int i = 0; i < newFiles.size() ; i++) {
			boolean success = oldFiles.get(i).renameTo(newFiles.get(i));
			if (!success){
					System.out.println("ERROR while renaming: \n" + oldFiles.get(i) + " to \n" + newFiles.get(i));
			}
		}
	}

	// return list of files that end in appropriate endings
	public static ArrayList<File> getReadingFiles(File[] files) {
		ArrayList<File> readingFiles = new ArrayList<File>();

		for(File file: files) {
			String curFile = file.getName();
			if(curFile.endsWith(".htm") || curFile.endsWith(".html")) {
				readingFiles.add(file);
			}
		}

		return readingFiles;
	}

	public static String getDirectory() {
		System.out.println("Choose an option: \n 1. Current Directory \n 2. Enter a directory");

		Scanner scan = new Scanner(System.in);
		String choiceStr = scan.nextLine();
		int choice = checkInt(choiceStr);
		// user input until valid choice is chosen
		while(choice!= 1 && choice!=2) {
			System.out.println("Invalid option, please re-enter option: ");
			choiceStr = scan.nextLine();
			choice = checkInt(choiceStr);
		}
		// resolve the choices to directory path
		if(choice == 1) {
			System.out.println("Use " + Paths.get("").toAbsolutePath().toString() + " ? Y/N: ");
			String answer = scan.nextLine();
			if(answer.toLowerCase().equals("y")) {
				return Paths.get("").toAbsolutePath().toString();
			}
			else {
				System.out.println("Now turned to option 2");
				choice = 2;
			}
		}
		if(choice == 2) {
		// user inputted directory
			String directory;
			System.out.println("Enter directory: ");
			directory = scan.nextLine();

			File theDir = new File(directory);
			// user input until valid directory is given
			while(!theDir.exists()) {
				System.out.println("Invalid directory, please re-enter: ");
				directory = scan.nextLine();
				theDir = new File(directory);
			}

			return directory;
		}
	}

}