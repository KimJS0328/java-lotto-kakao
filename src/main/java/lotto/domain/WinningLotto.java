package lotto.domain;

import java.util.Collection;
import java.util.List;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Collection<Integer> winningNumbers, Integer bonusNumber) {
        Lotto lotto = new Lotto(winningNumbers);
        LottoNumber bonus = LottoNumber.of(bonusNumber);
        validateUnique(lotto, bonus);
        this.lotto = new Lotto(winningNumbers);
        this.bonusNumber = bonus;
    }

    private void validateUnique(Lotto lotto, LottoNumber bonus) {
        if (lotto.contains(bonus)) {
            throw new IllegalArgumentException("당첨로또의 보너스 숫자는 6개 숫자와 겹치면 안됩니다");
        }
    }

    public Prize match(Lotto lotto) {
        int matchCount = lotto.countMatch(this.lotto);
        boolean isBonusMatched = lotto.contains(bonusNumber);
        return Prize.of(matchCount, isBonusMatched);
    }
}
