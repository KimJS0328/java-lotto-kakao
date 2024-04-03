package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class LottoResult {

    private final List<Lotto> lottos;
    private final Map<Prize, Integer> prizes;
    private final int lottoPrice;

    public LottoResult(List<Lotto> lottos, WinningLotto winning, int lottoPrice) {
        this.lottos = lottos;
        this.prizes = calculatePrizes(lottos, winning);
        this.lottoPrice = lottoPrice;
    }

    private EnumMap<Prize, Integer> calculatePrizes(List<Lotto> lottos, WinningLotto winning) {
        EnumMap<Prize, Integer> prizes = new EnumMap<>(Prize.class);

        lottos.forEach(lotto -> {
            Prize prize = winning.match(lotto);
            Integer count = prizes.getOrDefault(prize, 0);
            prizes.put(prize, count + 1);
        });

        return prizes;
    }

    public Integer getPrizeCount(Prize prize) {
        return prizes.getOrDefault(prize, 0);
    }

    public double getRewardRate() {
        AtomicLong totalReward = new AtomicLong();
        prizes.forEach((prize, count) -> {
            totalReward.addAndGet((long)prize.getReward() * count);
        });
        return (double)totalReward.getPlain() / (lottos.size() * lottoPrice);
    }
}
