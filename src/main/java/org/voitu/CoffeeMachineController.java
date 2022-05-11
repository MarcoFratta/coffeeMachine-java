package org.voitu;

import java.util.function.Consumer;

public interface CoffeeMachineController extends Controller<Consumer<CoffeeMachine>> {

    CoffeeMachine getCoffeeMachine();
    void recover();
    void refill();
    void update();
}
