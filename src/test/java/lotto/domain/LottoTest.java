package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 7, 8, 9})
    void 로또_숫자는_6개가_아니면_에외를_던진다(int size) {
        List<Integer> numbers = Stream.iterate(1, x -> x + 1).limit(size).collect(Collectors.toList());

        assertThatThrownBy(() -> new Lotto(numbers))
            .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void 일치하는_수의_개수를_반환한다(int size) {
        List<Integer> numbers = Stream.iterate(1, x -> x + 1).limit(6).collect(Collectors.toList());
        List<Integer> numbers2 = Stream.iterate(7 - size, x -> x + 1).limit(6).collect(Collectors.toList());
        Lotto lotto = new Lotto(numbers);
        LottoNumbers lottoNumbers2 = new LottoNumbers(numbers2);

        int matchCount = lotto.countMatch(lottoNumbers2);

        assertThat(matchCount).isEqualTo(size);
    }

    @Test
    void 모든_숫자를_포함하는지_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers numbers = new LottoNumbers(List.of(1, 2, 3, 4, 5));

        boolean containsAll = lotto.containsAll(numbers);

        assertThat(containsAll).isTrue();
    }
}
