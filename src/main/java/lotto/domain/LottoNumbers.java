package lotto.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {

    private final Set<LottoNumber> numbers;

    public LottoNumbers(Collection<Integer> numbers) {
        Set<LottoNumber> lottoNumbers = makeLottoNumbers(numbers);
        validate(numbers, lottoNumbers);
        this.numbers = lottoNumbers;
    }

    private Set<LottoNumber> makeLottoNumbers(Collection<Integer> lottoNumbers) {
        return lottoNumbers.stream()
            .map(LottoNumber::of)
            .collect(Collectors.toSet());
    }

    private void validate(Collection<Integer> numbers, Collection<LottoNumber> lottoNumbers) {
        validateNumbersUnique(numbers, lottoNumbers);
    }

    private void validateNumbersUnique(Collection<Integer> numbers, Collection<LottoNumber> lottoNumbers) {
        if (numbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 숫자는 중복되면 안됩니다");
        }
    }

    public int countMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.countMatch(numbers);
    }

    private int countMatch(Set<LottoNumber> lottoNumbers) {
        Set<LottoNumber> unionSet = new HashSet<>(lottoNumbers);
        unionSet.retainAll(numbers);
        return unionSet.size();
    }

    public boolean containsAll(LottoNumbers lottoNumbers) {
        return this.numbers.containsAll(lottoNumbers.numbers);
    }

    public List<LottoNumber> getAscendingNumbers() {
        return numbers.stream()
            .sorted()
            .collect(Collectors.toList());
    }
}
