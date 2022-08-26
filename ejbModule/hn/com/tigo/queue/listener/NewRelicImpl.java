
package hn.com.tigo.queue.listener;

import com.newrelic.api.agent.NewRelic;

/**
 * This class contains methods referring to new relic, which maps the metrics of the project.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-25-2022 10:16:53 PM 2022
 */
public class NewRelicImpl {

	
    /**
     * Adds the new relic metric.
     *
     * @param appId the app id
     * @param time the time
     */
    public static void addNewRelicMetric(String appId, float time) {
        try {
        	NewRelic.recordMetric(appId, time);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

    /**
     * Adds the new relic error.
     *
     * @param message the message
     */
    public static void addNewRelicError(Throwable message) {
        try {
			NewRelic.noticeError(message);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
    
  
    /**
     * Adds the new relic error message.
     *
     * @param message the message
     */
    public static void addNewRelicErrorMessage(String message) {
        try {
			NewRelic.noticeError(message);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
}
