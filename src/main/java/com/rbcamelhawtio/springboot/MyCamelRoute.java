/**
 * 
 */
package com.rbcamelhawtio.springboot;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Sample Camel Route with Quartz example
 * 
 * @author Rakesh Bhat
 *
 */
@Component
public class MyCamelRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("timer:myTimer").to("log:hwatio with camel");

		from("quartz2://myGroupName/myTimerName?cron=0/5+*+*+*+*+?").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("I'm running every 5 sec...");
			}
		});
	}
}
