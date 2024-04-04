package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMachineTest {

    @Test
    void 로또_한장은_천원으로_여러_장을_구매할_수_있다() {
        LottoMachine machine = new LottoMachine(1000);

        List<Lotto> lottos = machine.issue(5000);

        assertThat(lottos).hasSize(5);
    }

    @Test
    void 잔금은_무시한다() {
        LottoMachine machine = new LottoMachine(1000);

        List<Lotto> lottos = machine.issue(5500);

        assertThat(lottos).hasSize(5);
    }

    @Test
    void 로또를_한장도_구매할_수_없다면_예외를_던진다() {
        LottoMachine machine = new LottoMachine(1000);

        assertThatThrownBy(() -> machine.issue(500))
            .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6})
    void 수동_로또를_입력하면_남은_금액만큼_자동로또로_채워_반환한다(int size) {
        List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 5, 8))
        );
        LottoMachine lottoMachine = new LottoMachine(1000);

        List<Lotto> issuedLottos = lottoMachine.issue(size * 1000 + 999, lottos);

        assertThat(issuedLottos).hasSize(size);
    }

    @Test
    void 수동_로또를_금액보다_많이_주면_에러를_던진다() {
        List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 5, 8))
        );
        LottoMachine lottoMachine = new LottoMachine(1000);

        assertThatThrownBy(() -> lottoMachine.issue(2999, lottos))
            .isInstanceOf(RuntimeException.class);
    }
}
