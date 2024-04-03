package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

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
}
