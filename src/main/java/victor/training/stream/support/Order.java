package victor.training.stream.support;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Order {

	public boolean hasSpecialOffer() {
		return orderLines().stream().anyMatch(OrderLine::isSpecialOffer);
	}

	public enum Status {
		PLACED, COMPLETED, CANCELLED
	}
	
	public enum PaymentMethod {
		CARD,
		CASH_ON_SITE,
		CASH_ON_DELIVERY
	}
	
	private Integer id;
	private Status status;
	private final List<OrderLine> orderLines = new ArrayList<>();
	private LocalDate createdOn;
	private double total;
	private PaymentMethod paymentMethod;
	private String returnReason;

	public Order() {
	}
	
	public Order(Status status) {
		this.status = status;
	}

	public Order(Integer id) {
		this.id = id;
	}


	public List<OrderLine> orderLines() {
		return orderLines;
	}
	
	public Integer id() {
		return id;
	}

	public Status status() {
		return status;
	}

	public LocalDate createdOn() {
		return createdOn;
	}

	public Order createdOn(LocalDate creationDate) {
		this.createdOn = creationDate;
		return this;
	}

	public double total() {
		return total;
	}

	public Order total(double total) {
		this.total = total;
		return this;
	}

	public PaymentMethod paymentMethod() {
		return paymentMethod;
	}

	public Order paymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
		return this;
	}
	public Order add(OrderLine orderLine) {
		orderLines.add(orderLine);
		return this;
	}

	public Order returnReason(String returnReason) {
		this.returnReason = returnReason;
		return this;
	}
	public Optional<String> returnReason() {
		return ofNullable(returnReason);
	}

	@Override
	public String toString() {
		return "Order{" +
					 "id=" + id +
					 ", status=" + status +
					 ", orderLines=" + orderLines +
					 ", createdOn=" + createdOn +
					 ", total=" + total +
					 ", paymentMethod=" + paymentMethod +
					 ", returnReason='" + returnReason + '\'' +
					 '}';
	}
}
