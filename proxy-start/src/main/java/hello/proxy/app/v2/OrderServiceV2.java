package hello.proxy.app.v2;

public class OrderServiceV2{

    private final OrderRepositoryV2 orderRepository;

    public OrderServiceV2(OrderRepositoryV2 orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void OrderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
