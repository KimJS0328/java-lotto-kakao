package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.domain.Lotto;
import lotto.domain.Prize;

public class View {

    private final Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public int promptExpense() {
        System.out.println("구입금액을 입력해주세요.");
        int expense = scanner.nextInt();
        if (expense <= 0) {
            throw new IllegalArgumentException("구입금액은 양수여야 합니다");
        }
        return expense;
    }

    public int promptManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int count = scanner.nextInt();
        if (count < 0) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수는 음수일 수 없습니다");
        }
        return count;
    }

    public List<List<Integer>> promptManualLottos(int count) {
        if (count <= 0) {
            return List.of();
        }
        System.out.println("수동으로 구매할 번호를 입력해주세요.");
        return Stream.generate(this::scanCsvIntegerList)
            .limit(count)
            .collect(Collectors.toList());
    }

    public void printLotto(List<Lotto> lotto, int manualCount) {
        System.out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.\n", manualCount, lotto.size() - manualCount);
        lotto.forEach(this::printLotto);
        System.out.println();
    }

    private void printLotto(Lotto lotto) {
        System.out.print("[");
        System.out.print(makeLottoCsvString(lotto));
        System.out.println("]");
    }

    private String makeLottoCsvString(Lotto lotto) {
        return lotto.getAscendingNumbers()
            .stream()
            .map(String::valueOf)
            .collect(Collectors.joining(","));
    }


    public List<Integer> promptWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        return scanCsvIntegerList();
    }

    private List<Integer> scanCsvIntegerList() {
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
        System.out.printf("총 수익률은 %.2f입니다.", rate);
        if (rate < 1.0) {
            System.out.print("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
        System.out.println();
    }
}

