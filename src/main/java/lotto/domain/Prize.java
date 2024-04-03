package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Prize {
    FIRST(2000000000, 6, (matchCount, bonusMatched) -> matchCount == 6),
    SECOND(30000000, 5, (matchCount, bonusMatched) -> matchCount == 5 && bonusMatched),
    THIRD(1500000, 5, (matchCount, bonusMatched) -> matchCount == 5 && !bonusMatched),
    FOURTH(50000, 4, (matchCount, bonusMatched) -> matchCount == 4),
    FIFTH(5000, 3, (matchCount, bonusMatched) -> matchCount == 3),
    NONE(0, 0, (matchCount, bonusMatched) -> matchCount < 3);

    private final int reward;
    private final int matchCount;
    private final Condition condition;

    Prize(int reward, int matchCount, Condition condition) {
        this.reward = reward;
        this.matchCount = matchCount;
        this.condition = condition;
    }

    public static Prize of(int matchCount, boolean bonusMatched) {
        return Arrays.stream(values())
            .filter(prize -> prize.condition.meet(matchCount, bonusMatched))
            .findFirst()
            .orElse(Prize.NONE);
    }

    public int getReward() {
        return this.reward;
    }

    public boolean isBonusMatched() {
        return this == Prize.SECOND;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static List<Prize> reversedValuesForReward() {
        List<Prize> prizes = Arrays.asList(Prize.values());
        Collections.reverse(prizes);
        return prizes.stream()
            .filter(Prize::isNotNone)
            .collect(Collectors.toList());
    }

    private boolean isNotNone() {
        return this != NONE;
    }

    interface Condition {
        boolean meet(int matchCount, boolean bonusMatched);
    }
}
