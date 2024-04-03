package lotto;

import lotto.controller.Controller;
import lotto.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);

        controller.run();
    }
}
