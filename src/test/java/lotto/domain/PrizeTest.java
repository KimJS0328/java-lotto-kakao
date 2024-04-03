package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PrizeTest {

    @Test
    void 숫자_6개를_맞추면_1등이다() {
        assertAll(
            () -> assertThat(Prize.of(6, false)).isEqualTo(Prize.FIRST),
            () -> assertThat(Prize.of(6, true)).isEqualTo(Prize.FIRST)
        );
    }

    @Test
    void 숫자_5개와_보너스_숫자를_맞추면_2등이다() {
        assertThat(Prize.of(5, true)).isEqualTo(Prize.SECOND);
    }

    @Test
    void 숫자_5개를_맞추고_보너스_숫자를_틀리면_3등이다() {
        assertThat(Prize.of(5, false)).isEqualTo(Prize.THIRD);
    }

    @Test
    void 숫자_4개를_맞추면_4등이다() {
        assertAll(
            () -> assertThat(Prize.of(4, false)).isEqualTo(Prize.FOURTH),
            () -> assertThat(Prize.of(4, true)).isEqualTo(Prize.FOURTH)
        );
    }

    @Test
    void 숫자_3개를_맞추면_5등이다() {
        assertAll(
            () -> assertThat(Prize.of(3, false)).isEqualTo(Prize.FIFTH),
            () -> assertThat(Prize.of(3, true)).isEqualTo(Prize.FIFTH)
        );
    }

    @Test
    void 숫자를_2개_이하로_맞추면_꽝이다() {
        assertAll(
            () -> assertThat(Prize.of(2, true)).isEqualTo(Prize.NONE),
            () -> assertThat(Prize.of(2, false)).isEqualTo(Prize.NONE),
            () -> assertThat(Prize.of(1, true)).isEqualTo(Prize.NONE),
            () -> assertThat(Prize.of(1, false)).isEqualTo(Prize.NONE),
            () -> assertThat(Prize.of(0, true)).isEqualTo(Prize.NONE),
            () -> assertThat(Prize.of(0, false)).isEqualTo(Prize.NONE)
        );
    }
}
