package dice;

import java.util.Random;
import java.util.Scanner;

public class Roc {

	public static void main(String[] args) {
		

		DiceRoll diceRoller = new DiceRoll();
        diceRoller.rollDice();
		
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

class DiceRoll {
	
	public void rollDice() {
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("주사위의 설정을 입력해주세요.");
			System.out.println("EX. 2D6, 1D10, 1D100….");
			
			System.out.print("ROC: ");
			
			String rollgame = scanner.nextLine();
			
			String dicePart;
			int bonus = 0;
			
			if (rollgame.contains("+") || rollgame.contains("-")) {
                String[] parts = rollgame.split("(?=[+-])");
                dicePart = parts[0];
                bonus = Integer.parseInt(parts[1]);
            } else {
                dicePart = rollgame;
            }
			
			// "(?i)d" => 대소문자를 구분하지 않도록 설정하는 플래그
			// d를 기준으로 입력된 다이스 값을 배열로 나눔
			String[] roll = dicePart.split("(?i)d");
			
			// 나눠진 배열 중 첫 번째는 [다이스를 몇 번 굴릴 건지]
			// 나눠진 배열 중 두 번째는 [몇 면체 다이스를 굴릴 건지]
			int numOfDice = Integer.parseInt(roll[0]);
			int sidesOfDice = Integer.parseInt(roll[1]);
			
			Dice rollDice = new Dice(sidesOfDice);
			int total = 0;
			
			StringBuilder rollResult = new StringBuilder();
			
			rollResult.append(dicePart + " => ");
			
			for (int i=0;i<numOfDice;i++) {
				int num = rollDice.roll();
				total = total + num;
				
				if (i>0) {
					rollResult.append("+");
				}
				rollResult.append(num);
					
			}
			
			if (bonus != 0) {
                total += bonus;
                if (bonus > 0) {
                    rollResult.append(" + ").append(bonus);
                } else if (bonus < 0) {
                    rollResult.append(" - ").append(Math.abs(bonus));
                }
            }
			
			rollResult.append("=" + total);
			System.out.println(rollResult);
			
			if (dicePart.equalsIgnoreCase("2d6") && total == 12) {
			    System.out.println("Critical!");
			} else if (dicePart.equalsIgnoreCase("2d6") && total == 2) {
			    System.out.println("Fumble!");
			}
			
			System.out.println();
			
		}

	}
		
}