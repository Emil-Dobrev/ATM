package ATM;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;


	public class OptionsMenu extends Account {
		static Scanner userInput = new Scanner(System.in);
		public static void  getLogin() {
			HashMap<String, Integer> dataOfUsers = new HashMap<>();
		
			DecimalFormat moneyFormat = new DecimalFormat("'€'###,##0.00");
			
			dataOfUsers.put("Emo", 54321);
			dataOfUsers.put("Rumen", 45789);
			HashMap<String , Account> dataOfAccounts = new HashMap<>();
			
			System.out.printf("Welcome to ATM!\nPlease enter your user name\n");
			String name = checkUserName(userInput.nextLine() ,dataOfUsers ) ;
			if(!dataOfAccounts.containsKey(name)) {
				dataOfAccounts.put(name, new Account());
			}
	
			
			System.out.println("Please enter your pasword");
			int pw = checkPasword(userInput.nextLine(), name , dataOfUsers);
			if(pw == -1) {
				return;
			}else {
				dataOfAccounts.get(name).setUserPw(pw);
				
			}
	
		
			while(true) {
				System.out.printf("Checking account:\nType 1: View Balance\nType 2: Withdraw Funds\nType 3: Deposit Funds\nType 4: Exit\nChoice: ");
				int selection = Integer.parseInt(userInput.nextLine());
			switch(selection) {
			case 1:
			System.out.println(dataOfAccounts.get(name).getBalances()); 
				break;
			case 2:
				WinthDrawFunds( dataOfAccounts.get(name));
				break;
			case 3:
				DepositFunds(dataOfAccounts.get(name));
				break;
			case 4:
				System.out.println("Thank you for using our ATM!");
				return;
			default:
			break;	
			}
			
			}
			
}
		
		private static void DepositFunds(Account account) {
			System.out.println("Please enter the sum, that you want to deposit");
			double deposit = Double.parseDouble(userInput.nextLine());
			account.setBalances(account.getBalances() + deposit);
			System.out.println("Your balance is : " +   account.getBalances());
			
		}

		private static void WinthDrawFunds( Account account) {
			System.out.println("Please enter the sum, that you want to Winthdraw");
			double sumToWinthdraw = Double.parseDouble(userInput.nextLine());
			if(account.getBalances() >= sumToWinthdraw) {
				account.setBalances(account.getBalances() - sumToWinthdraw);
				System.out.println("Your balance is: " +  account.getBalances());
			}else {
				System.out.println("Not enough balance in your account!");
				
			}
		}

		private static int checkPasword(String pw,String key , HashMap<String, Integer> dataOfUsers) {
			int x = 3 ;
			while(x>1) {
				if(dataOfUsers.get(key) == Integer.parseInt(pw)) {
					break;
				}else {
					x--;
					System.out.printf("Incorect pasword , please try again! %d try left\n" , x);
					pw=userInput.nextLine() ;
				
				}	
			}
			if(x==0 ) {
				System.out.println("Account is blocked");
				dataOfUsers.remove(dataOfUsers);
				return -1;
			}
			return Integer.parseInt(pw);
			
		}

		private static String checkUserName(String userName ,HashMap<String , Integer> dataOfUsers ) {
			
			while(true) {
			if(dataOfUsers.containsKey(userName)) {
				
				break;
			}else {
				System.out.println("Incorrect user name , please enter valid user name!");
				userName= userInput.nextLine();
			}
			}
			return userName; 
		}
		
	}