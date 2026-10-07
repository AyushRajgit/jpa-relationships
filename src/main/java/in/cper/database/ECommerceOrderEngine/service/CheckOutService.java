package in.cper.database.ECommerceOrderEngine.service;

import in.cper.database.ECommerceOrderEngine.dto.CheckOutDTO;
import in.cper.database.ECommerceOrderEngine.entity.*;
import in.cper.database.ECommerceOrderEngine.exceptions.NotFoundException;
import in.cper.database.ECommerceOrderEngine.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckOutService {

    private CheckOutRepository checkOutRepository;
    private OrderItemRepository orderItemRepository;
    private CartItemRepository cartItemRepository;
    private ShopItemRepository shopItemRepository;
    private RegisterCustomerRepository registerCustomerRepository;
    private PaymentRepository paymentRepository;

    public CheckOutService(CheckOutRepository checkOutRepository, CartItemRepository cartItemRepository, ShopItemRepository shopItemRepository, OrderItemRepository orderItemRepository, RegisterCustomerRepository registerCustomerRepository, PaymentRepository paymentRepository) {
        this.checkOutRepository = checkOutRepository;
        this.cartItemRepository = cartItemRepository;
        this.shopItemRepository = shopItemRepository;
        this.orderItemRepository = orderItemRepository;
        this.registerCustomerRepository = registerCustomerRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public CheckOutDTO performCheckOut(int customerId) {
        ShoppingCart shoppingCart = shopItemRepository.findByCustomer_CustomerId(customerId).orElseThrow(
                () -> new NotFoundException("ShoppingCart not found with associated to customerId: " + customerId)
        );

        Customer customer = shoppingCart.getCustomer();
        if (customer == null) {
            throw new NotFoundException("Customer not found with associated to customerId: " + customerId + " while checkout");
        }

        Set<CartItem> cartItems = shoppingCart.getCartItems();

        Orders newOrder = stageOrderState(customer, cartItems);
        Payment payment = executePayment(newOrder);
        CheckOutDTO checkOutDTO = dtoMapper(newOrder, payment);

        // releasing resources
        // used Cascase.ALL so deleting parent entity would automatically delete related one's.
        // cartItemRepository.deleteAll(cartItems);
        customer.setShoppingCart(null);
        shoppingCart.setCustomer(null);
        shopItemRepository.delete(shoppingCart);
        logOrder(checkOutDTO);

        return checkOutDTO;
    }

    public Orders stageOrderState(Customer customer, Set<CartItem> cartItems) {
        Orders orders = new Orders();
        orders.setCustomer(customer);

        for (CartItem cartItem : cartItems) {
            OrderItems finalOrderItems = new OrderItems(
                    cartItem.getProduct(),
                    orders,
                    cartItem.getQuantity(),
                    cartItem.getPriceAtCheckout()
            );

            orderItemRepository.save(finalOrderItems);
            orders.getOrderItems().add(finalOrderItems);
        }

        performPaymentMaths(orders);
        return orders;
    }

    public void performPaymentMaths(Orders order) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItems orderItems : order.getOrderItems()) {
            totalAmount = totalAmount.add(orderItems.getPriceAtCheckout());
        }

        order.setTotalAmount(totalAmount);
    }

    private Payment executePayment(Orders newOrder) {
        Payment payment = new Payment(
                UUID.randomUUID().toString(),
                "AmazonPay",
                LocalDate.now(),
                LocalTime.now(),
                "Amazon.ecommerce.in",
                Status.SUCCESS,
                newOrder
        );

        // paymentRepository.save(payment);

        newOrder.getPayment().add(payment);
        newOrder.setDate(payment.getDate());
        newOrder.setTime(payment.getTime());
        newOrder.setTransactionId(payment.getTransactionId());

        checkOutRepository.save(newOrder);
        return payment;
    }

    public CheckOutDTO dtoMapper(Orders order, Payment payment) {
        return new CheckOutDTO(
                order.getOrderId(),
                order.getTransactionId(),
                order.getDate(),
                order.getTime(),
                order.getCustomer(),
                order.getOrderItems(),
                payment.getPaymentMethod(),
                payment.getPaidAt(),
                payment.getStatus()
        );
    }

    private void logOrder(CheckOutDTO checkOutDTO) {
        System.out.println(
                "\n" +
                        "==================================================\n" +
                        "                    ORDER\n" +
                        "==================================================\n" +
                        " Order ID        : " + checkOutDTO.getOrderId() + "\n" +
                        " Transaction ID  : " + checkOutDTO.getTransactionId() + "\n" +
                        " Customer ID     : " + checkOutDTO.getCustomer().getCustomerId() + "\n" +
                        " Date            : " + checkOutDTO.getDate() + "\n" +
                        " Time            : " + checkOutDTO.getTime() + "\n" +
                        " Payment Method  : " + checkOutDTO.getPaymentMethod() + "\n" +
                        " Paid At         : " + checkOutDTO.getPaidAt() + "\n" +
                        " Status          : " + checkOutDTO.getStatus() + "\n" +
                        " Items           : " + checkOutDTO.getOrderItems().size() + "\n" +
                        "==================================================\n" +
                        "          Thank you for shopping with us!\n" +
                        "                 Visit us again :)\n" +
                        "==================================================\n"
        );
    }
}
