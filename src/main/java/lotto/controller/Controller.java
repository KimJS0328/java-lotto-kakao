package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoPrice;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.Prize;
import lotto.domain.WinningLotto;
import lotto.view.View;

public class Controller {
    private final View view;
    private final LottoMachine lottoMachine;
    private final LottoPrice lottoPrice;

    public Controller(View view) {
        this.view = view;
        this.lottoMachine = new LottoMachine();
        this.lottoPrice = new LottoPrice(1000);
    }

    public void run() {
        List<Lotto> lotto = buyLotto();
        WinningLotto winningLotto = issueWinningLotto();

        LottoResult lottoResult = new LottoResult(lotto, winningLotto, lottoPrice.getPrice());

        printReward(lottoResult);
    }

    private List<Lotto> buyLotto() {
        int expense = view.promptExpense();
        int manualCount = view.promptManualCount();
        int autoCount = lottoPrice.calculateAutoCount(expense, manualCount);
        List<Lotto> lottos = getManualLottos(manualCount);
        lottos.addAll(lottoMachine.issue(autoCount));
        view.printLotto(lottos, manualCount);
        return lottos;
    }

    private List<Lotto> getManualLottos(int manualCount) {
        return view.promptManualLottos(manualCount)
            .stream()
            .map(lotto -> new Lotto(lotto))
            .collect(Collectors.toList());
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
