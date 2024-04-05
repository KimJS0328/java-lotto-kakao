package lotto.domain;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final LottoNumber[] LOTTO_NUMBERS = new LottoNumber[MAX_LOTTO_NUMBER];
    Integer number;

    static {
        for (int i = 0; i < MAX_LOTTO_NUMBER; i++) {
            LOTTO_NUMBERS[i] = new LottoNumber(i + 1);
        }
    }

    public static LottoNumber of(Integer number) {
        validateNumberRange(number);
        return LOTTO_NUMBERS[number - 1];
    }

    private LottoNumber(Integer number) {
        validateNumberRange(number);
        this.number = number;
    }

    private static void validateNumberRange(Integer number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 숫자는 1에서 45 사이여야 합니다");
        }
    }

    @Override
    public String toString() {
        return number.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        LottoNumber that = (LottoNumber)o;

        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.number - o.number;
    }
}
