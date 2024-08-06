package dice;

import java.util.Random;
import java.util.Scanner;

public class Roc {

	public static void main(String[] args) {
//		
//		Scanner scanner = new Scanner(System.in);
//		int option = scanner.nextInt();
		
		DiceRoll diceRoller = new DiceRoll();
		diceRoller.rollStart();
		
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
	
	// 주사위를 굴려서 일정 값 이상이면 다시 판정해서 결과를 반환하는 메소드
//	public int rollDX(int criticalNum) {
//		int result = random.nextInt(10) + 1;
//		while (result >= criticalNum) {
//			result = random.nextInt(10) + 1;
//		}
//		return result;
//	}
	
}

class DiceRoll {
	
	public void rollStart() {

        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("주사위를 시작하려면 'Start'를 입력하세요.");
            System.out.println("주사위를 종료하려면 'End'를 입력하세요.");

            System.out.print("명령어: ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("end")) {
                System.out.println("주사위를 종료합니다.\n");
                break; // 메인 루프를 종료합니다.
            } else if (command.equalsIgnoreCase("start")) {
            	System.out.println();
                rollDice(); // 주사위를 굴리는 메서드를 호출합니다.
            } else {
                System.out.println("잘못된 명령어입니다. 'start' 또는 'end'를 입력하세요.");
            }
        }
    }
	
	public void rollDice() {
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("주사위의 설정을 입력해주세요.");
			System.out.println("EX. 2D6, 1D10, 1D100….");
			
			System.out.print("ROC: ");
			
			// 입력한 텍스트가 다이스로 연결
			String rollgame = scanner.nextLine();
			
			if (rollgame.equalsIgnoreCase("end")) {
				System.out.println("주사위를 종료합니다.\n");
				return;
			};
			
			String dicePart; // 다이스 ndm
			int bonus = 0; // 수정치 +-
			
			// 입력한 텍스트에 +- 수정치가 있다면 if문 진행			
			if (rollgame.contains("+") || rollgame.contains("-")) {
				// +나 -를 기준으로 설정을 배열로 나누기
                String[] parts = rollgame.split("(?=[+-])");
                // [0]은 주사위 값, [1]은 수정치(문자열-> 정수 형 변환)
                dicePart = parts[0];
                bonus = Integer.parseInt(parts[1]);
            } else {
            	// 수정치가 없을 경우 그대로 진행
                dicePart = rollgame;
            }
			
			// "(?i)d" => 대소문자를 구분하지 않도록 설정하는 플래그
			// d를 기준으로 입력된 다이스 값을 배열로 나눔
			String[] roll = dicePart.split("(?i)d");
			
			// 나눠진 배열 중 첫 번째는 [다이스를 몇 번 굴릴 건지]
			// 나눠진 배열 중 두 번째는 [몇 면체 다이스를 굴릴 건지]
			int numOfDice = Integer.parseInt(roll[0]);
			int sidesOfDice = Integer.parseInt(roll[1]);
			
			// n면체 다이스 생성
			Dice rollDice = new Dice(sidesOfDice);
			int total = 0; // 총합 저장할 다이스
			
			// 결과값을 그냥 출력하지 않고 StringBuilder에 저장해서 양식대로 출력
			StringBuilder rollResult = new StringBuilder();
			
			// 결과 출력 기본: nDm = >
			rollResult.append(dicePart + " => ");
			
			// 다이스 굴리는 횟수에 도달할 때까지 판정 진행함
			for (int i=0;i<numOfDice;i++) {
				int num = rollDice.roll();
				// 한 번 굴릴 때마다 판정을 total(총합)에 저장
				total = total + num;
				// 만약 2회 이상 판정을 굴리게 된다면 각 값 뒤에 +를 붙임
				if (i>0) {
					rollResult.append("+");
				}
				// 결과값에 판정 결과 출력
				rollResult.append(num);
			}
			
			// 만약 보너스 값이 0이 아닐 경우 (있을 경우)
			if (bonus != 0) {
				// 총합에 보너스 값 추가
                total = total + bonus;
                // 결과 출력에 양수라면 +수정치, 음수라면 -수정치 추가
                if (bonus > 0) {
                    rollResult.append("+").append(bonus);
                } else if (bonus < 0) {
                    rollResult.append("-").append(Math.abs(bonus));
                }
            }
			
			rollResult.append("=" + total);
			System.out.println(rollResult);
			
			if (dicePart.equalsIgnoreCase("2d6")) {
                if (total >= 12) {
                    System.out.println("Critical!");
                } else if (total <= 2) {
                    System.out.println("Fumble!");
                }
            }
			
			System.out.println();
			
		}

	}
		
	public void rollDiceDX() {
		
		
		
	}
	
//	public void rollDiceDX() {
//		
//		Scanner scanner = new Scanner(System.in);
//		
//		while (true) {
//			System.out.println("주사위의 설정을 입력해주세요.");
//			System.out.println("EX. 1DX10, 3DX7, 10DX2….");
//			
//			System.out.print("ROC: ");
//			
//			// 입력한 텍스트가 다이스로 연결
//			String rollgame = scanner.nextLine();
//			
//			if (rollgame.equalsIgnoreCase("end"));
//			
//			String dicePart;
//			int criticalNum = 10;
//			int bonus = 0;
//			
//			// 입력한 텍스트에 +- 수정치가 있다면 if문 진행			
//			if (rollgame.contains("+") || rollgame.contains("-")) {
//				// +나 -를 기준으로 설정을 배열로 나누기
//                String[] parts = rollgame.split("(?=[+-])");
//                // [0]은 주사위 값, [1]은 수정치(문자열-> 정수 형 변환)
//                dicePart = parts[0];
//                bonus = Integer.parseInt(parts[1]);
//            } else {
//            	// 수정치가 없을 경우 그대로 진행
//                dicePart = rollgame;
//            }
//			
//			// "(?i)d" => 대소문자를 구분하지 않도록 설정하는 플래그
//			// d를 기준으로 입력된 다이스 값을 배열로 나눔
//			String[] roll = dicePart.split("(?i)dx");
//			
//			// 나눠진 배열 중 첫 번째는 [다이스를 몇 번 굴릴 건지]
//			// 나눠진 배열 중 두 번째는 [크리티컬치는 얼마인지]
//			int numOfDice = Integer.parseInt(roll[0]);
//			criticalNum = Integer.parseInt(roll[1]);
//			
//			// 10면체 다이스 생성
//			Dice rollDice = new Dice(10);
//			// 결과를 저장할 리스트 (결과를 정리할 것)
//			ArrayList<Integer> results = new ArrayList<>();
//			ArrayList<Integer> critical = new ArrayList<>();
//			int total = 0; // 총합 저장할 다이스
////			boolean exceededNum = false; // criticalNum를 넘었는지 체크
//			
//			// 결과값을 그냥 출력하지 않고 StringBuilder에 저장해서 양식대로 출력
//			StringBuilder rollResult = new StringBuilder();
//			
//			// 결과 출력 기본: nDm = >
//			rollResult.append(dicePart + " => ");
//			
//			// 다이스 굴리는 횟수에 도달할 때까지 판정 진행함
//			for (int i=0;i<numOfDice;i++) {
//				int num = rollDice.roll();
//				// 판정 결과를 리스트에 저장
//				results.add(num);
//				// 그 결과가 크리티컬치를 넘었다면
//				if (num >= criticalNum) {
//					total = total + 10;
//					critical.add(num);
//				}
//			}
//			
//			// 크리티컬치에 도달한 수만큼 판정
//			for (int j=0;j<critical.size();j++) {
//				
//			}
//			
//			// 만약 보너스 값이 0이 아닐 경우 (있을 경우)
//			if (bonus != 0) {
//				// 총합에 보너스 값 추가
//                total = total + bonus;
//                // 결과 출력에 양수라면 +수정치, 음수라면 -수정치 추가
//                if (bonus > 0) {
//                    rollResult.append("+").append(bonus);
//                } else if (bonus < 0) {
//                    rollResult.append("-").append(Math.abs(bonus));
//                }
//            }
//			
//			rollResult.append("=" + total);
//			System.out.println(rollResult);
//			
//			if (dicePart.equalsIgnoreCase("2d6")) {
//                if (total >= 12) {
//                    System.out.println("Critical!");
//                } else if (total <= 2) {
//                    System.out.println("Fumble!");
//                }
//            }
//			
//			System.out.println();
//			
//		}
//		
//	}

	
}