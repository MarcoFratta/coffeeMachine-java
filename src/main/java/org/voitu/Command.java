package org.voitu;

import java.util.function.BiFunction;

public enum Command {

    REFILL("Refill", (m, i) -> {
        if (m.getStatus() == MachineStatus.REFILL) {
            return "RL";
        }
        return "";
    }),
    RECOVER("Recover", (m, i) -> {
        if (m.getStatus() == MachineStatus.RECOVER) {
            return "RR";
        }
        return "";
    }
    ),
    UPDATE_PRODUCT("NP-", (m, i) -> {
        String[] s = i.substring(3).split("-");
        if (!(s.length == 2)) {
            try {
                ProductType type = ProductType.valueOf(s[0]);
                int value = Integer.parseInt(s[1]);
                m.setProductNumber(type, value);
                return "Product updated";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return "";
    }),
    UPDATE_STATE("ST-", (m, i) -> {
        String s = i.substring(3);
        if (!(s.isEmpty())) {
            try {
                MachineStatus status = MachineStatus.valueOf(s);
                m.setStatus(status);
                return "State changed";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return "";
    });


    private final String prefix;
    private final BiFunction<CoffeeMachine, String, String> action;


    Command(final String prefix, final BiFunction<CoffeeMachine, String, String> action) {
        this.prefix = prefix;
        this.action = action;
    }

    public String execute(final String input, final CoffeeMachine machine) {
        return this.action.apply(machine, input);
    }

    public String getCommandPrefix() {
        return this.prefix;
    }
}
