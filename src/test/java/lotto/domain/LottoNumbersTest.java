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

    @Test
    void 모든_숫자를_포함하면_true를_반환한다() {
        LottoNumbers numbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers numbers2 = new LottoNumbers(List.of(1, 2, 3, 4, 5));

        boolean containsAll = numbers.containsAll(numbers2);

        assertThat(containsAll).isTrue();
    }

    @Test
    void 모든_숫자를_포함하면_false를_반환한다() {
        LottoNumbers numbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers numbers2 = new LottoNumbers(List.of(1, 2, 3, 4, 7));

        boolean containsAll = numbers.containsAll(numbers2);

        assertThat(containsAll).isFalse();
    }
}
