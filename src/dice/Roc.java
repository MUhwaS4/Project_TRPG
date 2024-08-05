package dice;

import java.util.Random;
import java.util.Scanner;

public class Roc {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("주사위의 설정을 입력해주세요.");
			System.out.println("EX. 2D6, 1D10, 1D100….");			
			
			String sides = scanner.nextLine();
			
			// "(?i)d" => 대소문자를 구분하지 않도록 설정하는 플래그
			String[] roll = sides.split("(?i)d");
			
			int numOfDice = Integer.parseInt(roll[0]);
			int sidesOfDice = Integer.parseInt(roll[1]);
			
			Dice rollDice = new Dice(sidesOfDice);
			int total = 0;
			
			for (int i=0;i<numOfDice;i++) {
				int num = rollDice.roll();
				System.out.print(num + ", ");
				total = total + num;
			}
			System.out.println("총합: " + total);
			
			System.out.println();
			
		}

	}

}

class Dice {
	
	Random random;
	int sides; // 몇 면체인지 지정할 것
	
	// 다이스를 생성할 경우 몇 면체인지 초기화
	
	public Dice(int sides) {
		
		this.sides = sides;
		this.random = new Random();
		
	}
	
	// 주사위를 굴려서 결과를 반환하는 메소드
	public int roll() {
		return random.nextInt(sides) + 1; // +1을 하지 않으면 0부터 sides-1까지의 값을 반환함
	}
	
}