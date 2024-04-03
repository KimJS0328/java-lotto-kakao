package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningLottoTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 7, 8, 9})
    void 당첨로또는_로또숫자가_6개가_아니면_예외를_던진다(int size) {
        List<Integer> numbers = Stream.iterate(1, x -> x + 1).limit(size).collect(Collectors.toList());

        assertThatThrownBy(() -> new WinningLotto(numbers, 45))
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 당첨로또는_중복_숫자가_있으면_예외를_던진다() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 6))
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 숫자를_6개_맞추면_1등이다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Prize prize = winning.match(lotto);

        assertThat(prize).isEqualTo(Prize.FIRST);
    }

    @Test
    void 숫자를_5개_맞추고_보너스_숫자를_맞추면_2등이다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Prize prize = winning.match(lotto);

        assertThat(prize).isEqualTo(Prize.SECOND);
    }

    @Test
    void 숫자를_5개_맞추고_보너스_숫자를_틀리면_3등이다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Prize prize = winning.match(lotto);

        assertThat(prize).isEqualTo(Prize.THIRD);
    }

    @Test
    void 숫자를_4개를_맞추면_4등이다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Prize prize = winning.match(lotto);

        assertThat(prize).isEqualTo(Prize.FOURTH);
    }

    @Test
    void 숫자를_3개를_맞추면_5등이다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 10, 8, 9));
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Prize prize = winning.match(lotto);

        assertThat(prize).isEqualTo(Prize.FIFTH);
    }

    @Test
    void 숫자를_2개_이하_맞추면_꽝이다() {
        Lotto lotto = new Lotto(List.of(1, 2, 11, 10, 8, 9));
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Prize prize = winning.match(lotto);

        assertThat(prize).isEqualTo(Prize.NONE);
    }
}
