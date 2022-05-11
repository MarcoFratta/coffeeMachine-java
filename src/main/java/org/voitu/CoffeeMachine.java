package org.voitu;

import java.util.Map;

public interface CoffeeMachine {
    MachineStatus getStatus();
    int getPerformedSelfTestNumber();
    void setPerformedSelf(int num);
    Map<ProductType, Integer> getProductNumber();
    void setProductNumber(ProductType p, int productNumber);
    void setStatus(MachineStatus status);
    void setController(CoffeeMachineController controller);
}
