package dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceMechanism {

    public static void main(String[] args) {
        int finalAchievement = rollDiceMechanism(6, 5);
        System.out.println("Final Achievement: " + finalAchievement);
    }

    public static int rollDiceMechanism(int diceCount, int threshold) {
        Random random = new Random();
        int achievement = 0;
        List<Integer> diceResults = new ArrayList<>();
        List<Integer> rerollDice = new ArrayList<>();

        // 첫 번째 주사위 굴림
        for (int i = 0; i < diceCount; i++) {
            diceResults.add(random.nextInt(10) + 1);
        }

        int round = 1;

        while (!diceResults.isEmpty()) {
            System.out.println("Round " + round + ": Rolling dice...");

            rerollDice.clear();
            int highestResult = 0;
            boolean hasSuccessfulRolls = false;

            // 현재 라운드의 주사위 결과를 확인하고 출력
            for (int result : diceResults) {
                System.out.print(result + " ");
                if (result >= threshold) {
                    hasSuccessfulRolls = true;
                    rerollDice.add(random.nextInt(10) + 1);  // threshold 이상인 경우 새로 굴림
                }
                if (result > highestResult) {
                    highestResult = result;  // 가장 높은 결과 기록
                }
            }
            System.out.println();  // 주사위 결과를 출력한 후 줄바꿈

            // rerollDice로 교체
            diceResults = new ArrayList<>(rerollDice);

            // 성공적인 굴림이 있었는지 확인
            if (hasSuccessfulRolls) {
                achievement += 10;  // 성공적인 굴림이 있으면 달성치에 10 추가
                System.out.println("Successful rolls found. Adding 10 to achievement.");
            } else {
                // 성공적인 굴림이 없으면 최고값을 달성치에 추가하고 종료
                achievement += highestResult;
                System.out.println("No successful rolls. Adding highest result (" + highestResult + ") to achievement.");
                break;
            }

            round++;
        }

        return achievement;
    }
}
