package choreography.with.deadline.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;
import javax.transaction.Transactional;

import choreography.with.deadline.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import choreography.with.deadline.domain.*;


@Service
@Transactional
public class PolicyHandler{
    @Autowired OrderRepository orderRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='StockDecreased'")
    public void wheneverStockDecreased_Approve(@Payload StockDecreased stockDecreased){

        StockDecreased event = stockDecreased;
        System.out.println("\n\n##### listener Approve : " + stockDecreased + "\n\n");


        

        // Sample Logic //
        Order.approve(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='StockDecreaseFailed'")
    public void wheneverStockDecreaseFailed_Reject(@Payload StockDecreaseFailed stockDecreaseFailed){

        StockDecreaseFailed event = stockDecreaseFailed;
        System.out.println("\n\n##### listener Reject : " + stockDecreaseFailed + "\n\n");


        

        // Sample Logic //
        Order.reject(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='DeliveryFailed'")
    public void wheneverDeliveryFailed_Reject(@Payload DeliveryFailed deliveryFailed){

        DeliveryFailed event = deliveryFailed;
        System.out.println("\n\n##### listener Reject : " + deliveryFailed + "\n\n");


        

        // Sample Logic //
        Order.reject(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='DeadlineReached'")
    public void wheneverDeadlineReached_Reject(@Payload DeadlineReached deadlineReached){

        DeadlineReached event = deadlineReached;
        System.out.println("\n\n##### listener Reject : " + deadlineReached + "\n\n");


        

        // Sample Logic //
        Order.reject(event);
        

        

    }

}


