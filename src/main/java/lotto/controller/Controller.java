package lotto.controller;

import java.util.List;

import lotto.domain.LottoResult;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Prize;
import lotto.domain.WinningLotto;
import lotto.view.View;

public class Controller {
    private final View view;
    private final LottoMachine lottoMachine;

    public Controller(View view) {
        this.view = view;
        this.lottoMachine = new LottoMachine(1000);
    }

    public void run() {
        List<Lotto> lotto = buyLotto();
        WinningLotto winningLotto = issueWinningLotto();

        LottoResult lottoResult = new LottoResult(lotto, winningLotto, lottoMachine.getLottoPrice());

        printReward(lottoResult);
    }

    private List<Lotto> buyLotto() {
        int expense = view.promptExpense();
        List<Lotto> lotto = lottoMachine.issue(expense);
        view.printLotto(lotto);
        return lotto;
    }

    private WinningLotto issueWinningLotto() {
        List<Integer> numbers = view.promptWinningNumbers();
        Integer bonus = view.promptBonusNumber();
        return new WinningLotto(numbers, bonus);
    }

    private void printReward(LottoResult lottoResult) {
        view.printPrizeHeader();
        Prize.reversedValuesForReward().forEach(prize -> printPrize(prize, lottoResult));
        view.printRewardRate(lottoResult.getRewardRate());
    }

    private void printPrize(Prize prize, LottoResult lottoResult) {
        int prizeCount = lottoResult.getPrizeCount(prize);
        view.printPrizeInfo(prize, prizeCount);
    }
}
