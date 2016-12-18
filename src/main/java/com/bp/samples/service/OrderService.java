package com.bp.samples.service;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

/**
 * Created by behzad.pirvali on 12/9/16.
 */
@Component
public class OrderService {
    public String xmlTransform(LinkedCaseInsensitiveMap<Object> row) {
        return "XML --- " + row.toString() + " --- XML";
    }
}
