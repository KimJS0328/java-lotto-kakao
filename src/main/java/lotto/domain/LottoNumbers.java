package lotto.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final Set<Integer> numbers;

    public LottoNumbers(Collection<Integer> lottoNumbers) {
        Set<Integer> numbers = Set.copyOf(lottoNumbers);
        validate(lottoNumbers, numbers);
        this.numbers = numbers;
    }

    private void validate(Collection<Integer> lottoNumbers, Collection<Integer> numbers) {
        validateNumbersUnique(numbers, lottoNumbers);
        validateNumbersRange(lottoNumbers);
    }

    private void validateNumbersRange(Collection<Integer> numbers) {
        for (Integer number : numbers) {
            validateNumberRange(number);
        }
    }

    private void validateNumberRange(Integer number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 숫자는 1에서 45 사이여야 합니다");
        }
    }

    private void validateNumbersUnique(Collection<Integer> numbers, Collection<Integer> lottoNumbers) {
        if (numbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 숫자는 중복되면 안됩니다");
        }
    }

    public int countMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.countMatch(numbers);
    }

    private int countMatch(Set<Integer> lottoNumbers) {
        Set<Integer> unionSet = new HashSet<>(lottoNumbers);
        unionSet.retainAll(numbers);
        return unionSet.size();
    }

    public boolean containsAll(LottoNumbers lottoNumbers) {
        return this.numbers.containsAll(lottoNumbers.numbers);
    }

    public List<Integer> getAscendingNumbers() {
        return numbers.stream()
            .sorted()
            .collect(Collectors.toList());
    }
}
