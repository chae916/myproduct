package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

// 서비스 형식으로 선언
@Service
public class KafkalListener {
    @Autowired
    ProductRepository productRepository;
    @StreamListener(Processor.INPUT)
    public void onEventByString(@Payload OrderPlaced orderPlaced){
        if( orderPlaced.getEventType().equals("OrderPlaced")){
            System.out.println("======================");
            System.out.println("재고량 수정");
            Product p = new Product();
            p.setName(orderPlaced.getProductName());
            p.setStock(orderPlaced.getQty());
            productRepository.save(p);
            System.out.println("======================");
        }

    }
}
