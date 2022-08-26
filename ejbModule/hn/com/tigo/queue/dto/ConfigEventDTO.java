package hn.com.tigo.queue.dto;

import java.util.List;


/**
 * ConfigEventDTO.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-25-2022 09:52:16 PM 2022
 */
public class ConfigEventDTO {
	
	/** The retries. */
	private int retries;
	
	/** The events. */
	private List<DetailEventDTO> events;

	/**
	 * Gets the retries.
	 *
	 * @return the retries
	 */
	public final int getRetries() {
		return retries;
	}
	
	/**
	 * Sets the retries.
	 *
	 * @param retries the new retries
	 */
	public final void setRetries(int retries) {
		this.retries = retries;
	}
	
	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public final List<DetailEventDTO> getEvents() {
		return events;
	}

	/**
	 * Sets the events.
	 *
	 * @param events the new events
	 */
	public final void setEvents(List<DetailEventDTO> events) {
		this.events = events;
	}
	
}
