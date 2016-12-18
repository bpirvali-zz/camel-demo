package com.bp.samples.order;

import com.bp.samples.service.OrderService;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

/**
 * Created by behzad.pirvali on 12/9/16.
 */
@Component
public class OrderItemXmlTransformer {
    private static final Logger logger = LoggerFactory.getLogger(OrderItemXmlTransformer.class);

    @Autowired
    private OrderService orderService;

    public String transform(Message msg) {
        String output = null;
        try {
            if (msg==null)
                throw new Exception("Msg is null!");

            Object o = msg.getBody();
            if (o instanceof LinkedCaseInsensitiveMap)
                output = orderService.xmlTransform((LinkedCaseInsensitiveMap<Object>)o);

            else
                throw new Exception("msg not of type 'LinkedCaseInsensitiveMap'!");

        } catch (Exception e) {
            logger.error("Failed transform(...)  ", e.getMessage(), e);
        }
        return output;
    }

    private LinkedCaseInsensitiveMap getMap(Message msg) {
        Object o = null;
        try {
            assert msg==null : "msg is null!";

            o = msg.getBody();
            assert o==null : "Body is null!";

            if (!(o instanceof LinkedCaseInsensitiveMap))
                throw new Exception("msg not of type 'LinkedCaseInsensitiveMap'!");
        } catch (Exception e) {
            logger.error("Failed transform(...)  ", e.getMessage(), e);
        }
        return (LinkedCaseInsensitiveMap<Object>)o;
    }
}
