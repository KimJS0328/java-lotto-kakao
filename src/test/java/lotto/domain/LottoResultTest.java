package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @Test
    void 구매자는_로또들의_순위_목록을_구할_수_있다() {
        List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 7, 8))
        );
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoResult lottoResult = new LottoResult(lottos, winning, 1000);

        assertAll(
            () -> assertThat(lottoResult.getPrizeCount(Prize.FIRST)).isOne(),
            () -> assertThat(lottoResult.getPrizeCount(Prize.SECOND)).isEqualTo(2),
            () -> assertThat(lottoResult.getPrizeCount(Prize.THIRD)).isZero(),
            () -> assertThat(lottoResult.getPrizeCount(Prize.FOURTH)).isOne(),
            () -> assertThat(lottoResult.getPrizeCount(Prize.FIFTH)).isZero(),
            () -> assertThat(lottoResult.getPrizeCount(Prize.NONE)).isZero()
        );
    }

    @Test
    void 구매자는_로또_수익률을_구할_수_있다() {
        List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 7, 8))
        );
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoResult lottoResult = new LottoResult(lottos, winning, 1000);

        assertThat(lottoResult.getRewardRate()).isEqualTo(515012.5, within(0.1D));
    }
}
