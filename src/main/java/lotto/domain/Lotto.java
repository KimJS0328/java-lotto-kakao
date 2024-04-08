package lotto.domain;

import java.util.Collection;
import java.util.List;

public class Lotto {

    public static final int LOTTO_SIZE = 6;

    private final LottoNumbers numbers;

    public Lotto(Collection<Integer> numbers) {
        validateNumbersLength(numbers);
        this.numbers = new LottoNumbers(numbers);
    }

    private void validateNumbersLength(Collection<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다");
        }
    }

    public int countMatch(Lotto lotto) {
        return lotto.countMatch(this.numbers);
    }

    public int countMatch(LottoNumbers otherNumbers) {
        return numbers.countMatch(otherNumbers);
    }

    public boolean contains(LottoNumber otherNumber) {
        return numbers.contains(otherNumber);
    }

    public List<LottoNumber> getAscendingNumbers() {
        return numbers.getAscendingNumbers();
    }
}
