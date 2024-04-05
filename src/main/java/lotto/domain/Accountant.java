package lotto.domain;

public class Accountant {
    private final int price;

    public Accountant(int price) {
        this.price = price;
    }

    public int calculateAutoCount(int payment, int manualCount) {
        validate(payment, manualCount);
        return (payment - manualCount * price) / price;
    }

    private void validate(int payment, int manualCount) {
        if (payment < manualCount * price) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수보다 지불금액이 적습니다.");
        }
    }
}
