import java.util.Scanner;

public class code_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyword = new Scanner(System.in);
		String keyString = keyword.nextLine();
		keyword.close();
		System.out.println(keyString.replace(" ", "+"));
	}

}
