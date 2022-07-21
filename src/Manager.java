import java.util.Scanner;

public class Manager {
	//Managers Attributes
	Scanner input = new Scanner(System.in);
	int projectNum;
	String name;
	String email;
	String address;
	String tel;
	
	//Method to get manager details
	public void getManagerDetails(int projectID) {
		System.out.println("--------PROJECT MANAGER DETAILS--------");
		System.out.print("Name : ");
		name = input.nextLine();
		System.out.print("Email : ");
		email = input.nextLine();
		System.out.print("Addesse : ");
		address = input.nextLine();
		System.out.print("Tel : ");
		tel = input.next();
		input.nextLine();		
		projectNum = projectID;

	}
	
}
