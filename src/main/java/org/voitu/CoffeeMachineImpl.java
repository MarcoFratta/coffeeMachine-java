package org.voitu;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachineImpl implements CoffeeMachine{

    private MachineStatus status;
    private int selfTestNumber;
    private final Map<ProductType, Integer> productsNumber;
    private CoffeeMachineController controller;

    public CoffeeMachineImpl() {
        this.status = MachineStatus.IDLE;
        this.selfTestNumber = 0;
        this.productsNumber = new HashMap<>();
    }

    @Override
    public MachineStatus getStatus() {
        return this.status;
    }

    @Override
    public int getPerformedSelfTestNumber() {
        return this.selfTestNumber;
    }

    @Override
    public void setPerformedSelf(final int num) {
        this.selfTestNumber = num;
        this.controller.update();
    }

    @Override
    public Map<ProductType, Integer> getProductNumber() {
        return Map.copyOf(this.productsNumber);
    }

    @Override
    public void setProductNumber(final ProductType p, final int productNumber) {
        this.productsNumber.put(p,productNumber);
        this.controller.update();
    }

    @Override
    public void setStatus(final MachineStatus status) {
        this.status = status;
        this.controller.update();
    }

    @Override
    public void setController(final CoffeeMachineController controller) {
        this.controller = controller;
    }

}
