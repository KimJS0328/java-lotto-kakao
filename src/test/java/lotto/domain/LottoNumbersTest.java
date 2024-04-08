package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumbersTest {
    @Test
    void 모든_숫자가_1이상_45이하가_아니면_예외를_던진다() {
        assertAll(
            () -> assertThatThrownBy(() -> new LottoNumbers(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(RuntimeException.class),
            () -> assertThatThrownBy(() -> new LottoNumbers(List.of(46, 2, 3, 4, 5, 6)))
                .isInstanceOf(RuntimeException.class)
        );
    }

    @Test
    void 중복_숫자가_있으면_예외를_던진다() {
        assertAll(
            () -> assertThatThrownBy(() -> new LottoNumbers(List.of(2, 2, 3, 4, 5, 6)))
                .isInstanceOf(RuntimeException.class),
            () -> assertThatThrownBy(() -> new LottoNumbers(List.of(6, 2, 3, 4, 5, 6)))
                .isInstanceOf(RuntimeException.class)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void 서로_같은_원소의_수를_반환한다(int size) {
        List<Integer> numbers = Stream.iterate(1, x -> x + 1).limit(6).collect(Collectors.toList());
        List<Integer> numbers2 = Stream.iterate(7 - size, x -> x + 1).limit(6).collect(Collectors.toList());
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        LottoNumbers lottoNumbers2 = new LottoNumbers(numbers2);

        int matchCount = lottoNumbers.countMatch(lottoNumbers2);

        assertThat(matchCount).isEqualTo(size);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void 숫자를_포함하면_참을_반환한다(int number) {
        LottoNumbers numbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        boolean contains = numbers.contains(LottoNumber.of(number));

        assertThat(contains).isTrue();
    }

    @Test
    void 숫자를_포함하지_않으면_거짓을_반환한다() {
        LottoNumbers numbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        boolean contains = numbers.contains(LottoNumber.of(7));

        assertThat(contains).isFalse();
    }
}
