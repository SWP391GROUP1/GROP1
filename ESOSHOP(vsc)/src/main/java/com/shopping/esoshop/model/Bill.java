package com.shopping.esoshop.model;

import java.sql.Date;
import java.util.List;

public class Bill {
    private String orderId;
    private Customer customer;
    private List<OrderDelail> orderdetails;
    private Date orderDate;
    private Staff staff;
    private int status;
    private String address;
    private double totalAmount;

    public Bill(String orderId,Customer customer, List<OrderDelail> orderdetails, Date orderDate, Staff staff, int status,
            String address, double totalAmount) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderdetails = orderdetails;
        this.orderDate = orderDate;
        this.staff = staff;
        this.status = status;
        this.address = address;
        this.totalAmount = totalAmount;
    }

    public Bill() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDelail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<OrderDelail> orderdetails) {
        this.orderdetails = orderdetails;
        sumPrice();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Bill [customer=" + customer + ", orderdetails=" + orderdetails + ", orderDate=" + orderDate + ", staff="
                + staff + ", status=" + status + ", address=" + address + ", totalAmount=" + totalAmount + "]";
    }

    private void sumPrice() {
        for (OrderDelail orderDelail : this.orderdetails) {
            double price = orderDelail.getProduct().getPrice() * orderDelail.getQuantity();
            this.totalAmount += price;
        }
    }


}
