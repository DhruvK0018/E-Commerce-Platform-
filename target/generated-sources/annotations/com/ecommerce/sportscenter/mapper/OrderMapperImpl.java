package com.ecommerce.sportscenter.mapper;

import com.ecommerce.sportscenter.entity.OrderAggregate.Order;
import com.ecommerce.sportscenter.entity.OrderAggregate.Order.OrderBuilder;
import com.ecommerce.sportscenter.entity.OrderAggregate.OrderStatus;
import com.ecommerce.sportscenter.model.OrderDto;
import com.ecommerce.sportscenter.model.OrderDto.OrderDtoBuilder;
import com.ecommerce.sportscenter.model.OrderResponse;
import com.ecommerce.sportscenter.model.OrderResponse.OrderResponseBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-15T12:14:26+0530",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240620-1855, environment: Java 17.0.11 (Eclipse Adoptium)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse OrderToOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.id( order.getId() );
        orderResponse.basketId( order.getBasketId() );
        orderResponse.shippingAddress( order.getShippingAddress() );
        if ( order.getSubTotal() != null ) {
            orderResponse.subTotal( order.getSubTotal().longValue() );
        }
        orderResponse.deliveryFee( order.getDeliveryFee() );

        orderResponse.total( order.getSubTotal() + order.getDeliveryFee() );
        orderResponse.orderDate( java.time.LocalDateTime.now() );
        orderResponse.orderStatus( OrderStatus.Pending );

        return orderResponse.build();
    }

    @Override
    public Order orderResponseToOrder(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        OrderBuilder order = Order.builder();

        order.basketId( orderDto.getBasketId() );
        order.deliveryFee( orderDto.getDeliveryFee() );
        order.shippingAddress( orderDto.getShippingAddress() );
        if ( orderDto.getSubTotal() != null ) {
            order.subTotal( orderDto.getSubTotal().doubleValue() );
        }

        order.orderDate( orderDto.getOrderDate() );
        order.orderStatus( OrderStatus.Pending );

        return order.build();
    }

    @Override
    public List<OrderDto> ordersToOrderResponses(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( orderToOrderDto( order ) );
        }

        return list;
    }

    @Override
    public void updateOrderFromOrderResponse(OrderDto orderDto, Order order) {
        if ( orderDto == null ) {
            return;
        }

        order.setBasketId( orderDto.getBasketId() );
        order.setDeliveryFee( orderDto.getDeliveryFee() );
        order.setOrderDate( orderDto.getOrderDate() );
        order.setShippingAddress( orderDto.getShippingAddress() );
        if ( orderDto.getSubTotal() != null ) {
            order.setSubTotal( orderDto.getSubTotal().doubleValue() );
        }
        else {
            order.setSubTotal( null );
        }
    }

    protected OrderDto orderToOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDtoBuilder orderDto = OrderDto.builder();

        orderDto.basketId( order.getBasketId() );
        orderDto.deliveryFee( order.getDeliveryFee() );
        orderDto.orderDate( order.getOrderDate() );
        orderDto.shippingAddress( order.getShippingAddress() );
        if ( order.getSubTotal() != null ) {
            orderDto.subTotal( order.getSubTotal().longValue() );
        }

        return orderDto.build();
    }
}
