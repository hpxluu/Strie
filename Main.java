import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		Strie myStrie = new Strie();
		Scanner in = new Scanner(System.in);

		while(true) {
			int choice = doMenu(in);
			try {
				switch(choice) {
					case 1:
						System.out.print("Enter a word: ");
						String word = in.nextLine().trim();
												
						myStrie.insert(word);
						
						break;
					case 2:
						System.out.print("Enter a word: ");
						word = in.nextLine().trim();

					 	boolean isFound = myStrie.contains(word);
						if(isFound == true) System.out.println("Your Strie contains this word!");
						else System.out.println("Your strie doesn't contain this word!");
						
						break;
					case 3:
						System.out.print("Enter a word: ");
						word = in.nextLine().trim();
					
						boolean removed = myStrie.remove(word);
						if(removed  == true) System.out.println("Word removed from Strie!");
						else 
							System.out.println("No such word to remove!");
						
						break;
					case 4:
						String ret = myStrie.levelOrderTraversal().trim();
						System.out.println(ret);
						
						break;
					case 5:
						SimpleList<String> allStrings = myStrie.getStrieWords();
						
						if (allStrings==null)
							System.out.println("Empty strie with zero words.");
						else
							for(String str : allStrings)
								System.out.println(str);
						
						break;
					case 6: in.close(); return;
				}
			}
			catch(RuntimeException e) {
				System.out.println("\n"+e.getMessage());
			}
		}
	}

	public static int doMenu(Scanner in) {
		while(true) {
			try {
				System.out.println("\n===============================================");
				System.out.println("Options with Strie:");
				System.out.println(" 1) Insert a word");
				System.out.println(" 2) Look up a word");
				System.out.println(" 3) Remove a word");
				System.out.println(" 4) Do a level order traversal of your Strie");
				System.out.println(" 5) Display all existing words from your Strie");
				System.out.println(" 6) Quit");
				System.out.println("===============================================");
				System.out.print("\nWhat's your choice? ");
				int choice = in.nextInt();
				in.nextLine();

				if(choice < 1 || choice > 6) {
					System.out.println("Invalid selection");
					continue;
				}

				return choice;
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid selection");
				in.nextLine();
			}
		}
	}
}
