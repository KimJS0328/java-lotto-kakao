package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 47})
    void 로또_숫자는_1에서_45_사이가_아니면_예외를_던진다(int number) {
        assertThatThrownBy(() -> LottoNumber.of(number))
            .isInstanceOf(RuntimeException.class);
    }
}
