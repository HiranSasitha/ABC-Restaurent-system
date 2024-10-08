package com.example.abc_restaurant_system.service.impl;

import com.example.abc_restaurant_system.dto.OrderDto;
import com.example.abc_restaurant_system.dto.OrderItemDto;
import com.example.abc_restaurant_system.entity.*;
import com.example.abc_restaurant_system.repository.*;
import com.example.abc_restaurant_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    @Transactional
    public String save(OrderDto orderDto) throws JRException, IOException {

        User user = userRepository.findByUserName(orderDto.getUserName());
        Branch branch = branchRepository.findById(orderDto.getBranchId()).get();

        List<OrderItemDto> orderItemDtos = orderDto.getOrderItemDtos();

        if( user != null && branch != null){

            Order order = new Order();
            order.setBranch(branch);
            order.setCreatedDate(new Date());
            order.setUserEntity(user);
            order.setPayOption(orderDto.getPayOption());
            order.setOrderOption(orderDto.getOrderOption());
            if(orderDto.getBookingSeat() != -1){
                order.setBookingSeat(orderDto.getBookingSeat());
            }else {
                order.setBookingSeat(null);
            }
            order.setTotal(0.0);

            for (OrderItemDto orderItemDto:orderItemDtos){
                Item item = itemRepo.findById(orderItemDto.getItemId()).get();

                if(item != null && orderItemDto.getQty()>0){
                    order.setTotal(order.getTotal()+(item.getSellingPrice()*orderItemDto.getQty()));
                }
            }

            orderRepository.save(order);

            for (OrderItemDto orderItemDto:orderItemDtos){
                Item item = itemRepo.findById(orderItemDto.getItemId()).get();
                if(item!= null){
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setOrder(order);
                    orderDetails.setItem(item);
                    orderDetails.setQty(orderItemDto.getQty());
                    orderDetailsRepository.save(orderDetails);
                }

            }

            OrderStatus orderStatus = new OrderStatus();

            orderStatus.setOrder(order);
            orderStatus.setIsStatus(false);
             orderStatusRepository.save(orderStatus);

             this.exportReport(order);

            return "Successfully";


        }else {
            return "User name or Branch id Invalid";
        }

    }

    @Override
    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
    }

    @Override
    public String updateOrderStatus(Integer id, Boolean status) {

        OrderStatus orderStatus = orderStatusRepository.findById(id).get();
        orderStatus.setIsStatus(status);
        orderStatusRepository.save(orderStatus);

        return "Success Update Order";

    }

    @Override
    public Integer getAvailableSeat(Integer branchId) {

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        Date startDate = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println(startDate);
        System.out.println(endDate);


        Branch branch = branchRepository.findById(branchId).get();

        Integer totalSeat = branch.getSeat();

        Integer booking = 0;

        List<Order> orders = orderRepository.findAllByBranchIdAndCreatedDate(branchId, startDate, endDate);

        for (Order order:orders){
            if(order.getBookingSeat() != null){
                booking+=order.getBookingSeat();
            }

        }

        Integer availableSeats = totalSeat-booking;
        return availableSeats;
    }

    @Override
    public List<OrderStatus> getOrdersByUser(String userName) {

        User user = userRepository.findByUserName(userName);
        if(user != null){

            return orderStatusRepository.findAllByOrder_UserEntity_Id(user.getId());


        }else {
            return null;
        }

    }

    @Override
    public void exportReport(Order order) throws IOException, JRException {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        Resource resource = resourceLoader.getResource("classpath:reports/order.jrxml");
        InputStream inputStream = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("create By","Hiran");


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource );

        JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\hiran\\OneDrive\\Desktop"+"\\abc_restaurant.pdf");
    }
}
