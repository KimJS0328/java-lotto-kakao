package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMachineTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void 주어진_개수만큼_로또를_발행한다(int amount) {
        LottoMachine machine = new LottoMachine();

        List<Lotto> lottos = machine.issue(amount);

        assertThat(lottos).hasSize(amount);
    }
}
