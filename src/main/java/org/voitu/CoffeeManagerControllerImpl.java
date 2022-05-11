package org.voitu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CoffeeManagerControllerImpl implements CoffeeMachineController{

    private final CoffeeMachine machine;
    private final List<Consumer<CoffeeMachine>> listeners;
    private  CommChannel channel;

    public CoffeeManagerControllerImpl(final CoffeeMachine machine,
                                       final String port, final int rate) throws Exception {
        this.machine = machine;
        this.listeners = new ArrayList<>();
        this.channel = new SerialCommChannel(port,rate);
        new MonitoringAgent((SerialCommChannel) this.channel,this).start();
    }

    @Override
    public CoffeeMachine getCoffeeMachine() {
        return this.machine;
    }

    @Override
    public void recover() {
        this.channel.sendMsg(Command.RECOVER.execute("Recover",this.machine));
        this.update();
    }


    @Override
    public void refill() {
        this.channel.sendMsg(Command.REFILL.execute("Refill",this.machine));
        this.update();
    }

    @Override
    public void registerListener(final Consumer<CoffeeMachine> listener) {
        this.listeners.add(listener);
    }
    @Override
    public void update() {
        this.listeners.forEach(l -> l.accept(this.machine));
    }

}
