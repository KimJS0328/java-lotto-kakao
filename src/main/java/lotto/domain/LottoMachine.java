package lotto.domain;

import static lotto.domain.Lotto.*;
import static lotto.domain.LottoNumbers.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {

    private final Random random;
    private final int lottoPrice;

    public LottoMachine(int lottoPrice) {
        this.random = new Random();
        this.lottoPrice = lottoPrice;
    }

    public List<Lotto> issue(int money) {
        List<Lotto> lottos = generateAutoLottos(money);
        validateLottosNotEmpty(lottos);
        return generateAutoLottos(money);
    }

    private void validateLottosNotEmpty(Collection<Lotto> lottos) {
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException("로또는 최소 하나 구매해야 합니다");
        }
    }

    public List<Lotto> issue(int money, Collection<Lotto> lottos) {
        List<Lotto> autoLottos = generateAutoLottos(calculateBalance(money, lottos));
        autoLottos.addAll(0, lottos);
        validateLottosNotEmpty(autoLottos);
        return autoLottos;
    }

    private List<Lotto> generateAutoLottos(int balance) {
        return Stream.generate(this::issue)
            .limit(balance / lottoPrice)
            .collect(Collectors.toList());
    }

    private int calculateBalance(int money, Collection<Lotto> lottos) {
        int balance = money - lottos.size() * lottoPrice;
        if (balance < 0) {
            throw new IllegalArgumentException("주어진 금액으로 구매할 수 없습니다");
        }
        return balance;
    }

    private Lotto issue() {
        List<Integer> numbers = new ArrayList<>();

        for (int poolSize = MAX_LOTTO_NUMBER - LOTTO_SIZE + 1; poolSize <= MAX_LOTTO_NUMBER; ++poolSize) {
            Integer number = random.nextInt(poolSize) + MIN_LOTTO_NUMBER;
            numbers.add(nextNumber(numbers, number, poolSize));
        }

        return new Lotto(numbers);
    }

    private Integer nextNumber(List<Integer> numbers, Integer number, int poolSize) {
        if (numbers.contains(number)) {
            return poolSize;
        }
        return number;
    }

    public int getLottoPrice() {
        return lottoPrice;
    }
}
