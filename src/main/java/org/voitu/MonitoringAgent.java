package org.voitu;


public class MonitoringAgent extends Thread {

	private static final String COMMAND_TYPE_STR = "Executed";
	private  SerialCommChannel channel;
	private final CoffeeMachineController controller;
	
	public MonitoringAgent(final SerialCommChannel channel, final CoffeeMachineController controller)
			throws Exception {
		this.controller = controller;
		this.channel = channel;
	}
	
	public void run(){
		while (true){
			try {
				//LoggerImpl.getLogger().log("Info", "Reading...");
				//System.out.println("receiving...: ");
				final String msg = this.channel.receiveMsg();
				//System.out.println("received: " + msg);
				LoggerImpl.getLogger().log("Input", msg);
				for (final Command command: Command.values()) {
					if(msg.startsWith(command.getCommandPrefix())){
						final String output = command.execute(msg, this.controller.getCoffeeMachine());
						LoggerImpl.getLogger().log(COMMAND_TYPE_STR, output);

					}
				}
			} catch (final Exception ex){
				LoggerImpl.getLogger().log("Error", "Command error");
				ex.printStackTrace();
			}
		}
	}

}
