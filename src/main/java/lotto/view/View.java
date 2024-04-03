package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.Prize;

public class View {

    private final Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public int promptExpense() {
        System.out.println("구입금액을 입력해주세요.");
        return scanner.nextInt();
    }

    public void printLotto(List<Lotto> lotto) {
        System.out.printf("%d개를 구매했습니다.\n", lotto.size());
        lotto.forEach(this::printLotto);
        System.out.println();
    }

    private void printLotto(Lotto lotto) {
        System.out.print("[");
        System.out.print(lotto);
        System.out.println("]");
    }

    public List<Integer> promptWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        return Arrays.stream(nextNotBlankLine().split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private String nextNotBlankLine() {
        String line = scanner.nextLine();
        while (line.isBlank()) {
            line = scanner.nextLine();
        }
        return line;
    }

    public int promptBonusNumber() {
        System.out.println("보너스 볼을 입력해주세요.");
        return scanner.nextInt();
    }

    public void printPrizeHeader() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printPrizeInfo(Prize prize, int prizeCount) {
        System.out.printf("%d개 일치", prize.getMatchCount());
        if (prize.isBonusMatched()) {
            System.out.print(", 보너스 볼 일치");
        }
        System.out.printf(" (%d원) - %d개\n", prize.getReward(), prizeCount);
    }

    public void printRewardRate(double rate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", rate);
    }
}

