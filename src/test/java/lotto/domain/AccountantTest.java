package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AccountantTest {
    @ParameterizedTest
    @CsvSource(value = {"1110,1,0", "1110,0,1", "7900,3,4"})
    void 회계사는_로또가격을_바탕으로_지불금액과_수동로또_개수가_주어졌을_때_발급해야하는_자동로또_개수를_알아낸다(int payment, int manualCount, int expected) {
        // given
        Accountant accountant = new Accountant(1000);

        // when
        int autoCount = accountant.calculateAutoCount(payment, manualCount);

        // then
        assertThat(autoCount).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"900,1", "2888,3", "1000,3"})
    void 회계사는_지불금액이_수동로또_가격보다_적을_때_예외를_던진다(int payment, int manualCount) {
        // given
        Accountant accountant = new Accountant(1000);

        // when, then
        assertThatThrownBy(() -> accountant.calculateAutoCount(payment, manualCount))
            .isInstanceOf(RuntimeException.class);
    }
}
