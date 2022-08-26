/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.com.tigo.queue.context;

/**
 * Context This class contains the necessary attributes for handling the context for reading jms queues.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-25-2022 09:51:38 PM 2022
 */
public class Context {
    
    /** The command. */
    private static AppCommand COMMAND = AppCommand.START;
    
    /** The time sleep. */
    private static int TIME_SLEEP = 1000;

    /**
     * Sets the command.
     *
     * @param command the new command
     */
    public static void setCommand(AppCommand command) {
        COMMAND = command;
    }

    /**
     * Gets the command.
     *
     * @return the command
     */
    public static AppCommand getCommand() {
        return COMMAND;
    }


	/**
	 * Gets the time sleep.
	 *
	 * @return the time sleep
	 */
	public static final int getTIME_SLEEP() {
		return TIME_SLEEP;
	}


	/**
	 * Sets the time sleep.
	 *
	 * @param tIME_SLEEP the new time sleep
	 */
	public static final void setTIME_SLEEP(int tIME_SLEEP) {
		TIME_SLEEP = tIME_SLEEP;
	}
}