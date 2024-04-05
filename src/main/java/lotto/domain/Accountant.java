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
        if (payment < price) {
            throw new IllegalArgumentException("적어도 하나의 로또는 구매할 수 있는 금액을 지급해야 합니다");
        }
        if (payment < manualCount * price) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수보다 지불금액이 적습니다");
        }
    }

    public int getPrice() {
        return price;
    }
}
