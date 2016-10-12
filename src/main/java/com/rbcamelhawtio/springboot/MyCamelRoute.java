/**
 * 
 */
package com.rbcamelhawtio.springboot;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
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
		
		from("direct:newMessage").to("log:${body}");

		from("quartz2://myGroupName/myTimerName?cron=0/5+*+*+*+*+?").process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("I'm running every 5 sec...");
			}
		});
		
		System.out.println("Starting Camel Restlet Route....");
		restConfiguration().component("restlet")
		.bindingMode(RestBindingMode.auto)
		.skipBindingOnErrorCode(false);
		
		//getContext().setStreamCaching(true);
		getContext().setTracing(true);
		//getContext().setTypeConverterStatisticsEnabled(true);
		
		rest("/hello")
		.produces("application/json")
		.get()
		.to("direct:hello");
		
		from("direct:hello")
		.process(exchange->{
			exchange.getIn().setBody("Hello");
		});
	}
}
