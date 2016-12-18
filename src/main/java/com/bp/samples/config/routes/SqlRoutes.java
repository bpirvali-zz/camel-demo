package com.bp.samples.config.routes;

import com.bp.samples.order.OrderItemXmlTransformer;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlRoutes extends RouteBuilder {

	@Autowired
	private OrderItemXmlTransformer orderItemXmlTransformer;

//	@Bean
//	public SqlComp

	@Value("${kafka.server}")
	private String kafkaServer;

	@Value("${kafka.topic}")
	private String kafkaTopic;


	@Override
	public void configure() throws Exception {
		from(
				"sql:"  
				+ "SELECT id, ordernumber FROM orders.orders WHERE status = 'N'"
			    + "?"
			    + "consumer.onConsume=UPDATE orders.orders SET status='P'"
			    + " WHERE id = :#id"
			    + "&" 
			    + "dataSource=dataSource"
			).
            bean(orderItemXmlTransformer, "transform").
			to("kafka:" + kafkaServer + ":9092?topic="+kafkaTopic).
			to(
				"log:" +
				"com.pluralsight.orderfulfillment" +
				"?" +		
				"level=INFO"
			);
	}
}
