//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.PriorityQueue;
//import java.util.Scanner;
//import java.util.Set;
//
//public class AlphaLabP {
//	private static Integer GLOBAL_TIMESTAMP = 0;
//	private static OrderBook orderBook = new OrderBook();
//	
//	AlphaLabP() {
//	}
//
//	public static void main(String[] args) {
//		
//		Scanner sc = new Scanner(System.in).useDelimiter("\\n");
//		
//		
//		while(sc.hasNext()) {
//			String[] input = sc.next().split(" ");
//			
//			String action = input[0];
//			
//			switch (action) {
//				case "SUB":
//					processSUB(input);
//					break;
//				case "CXL":
//					processCXL(input);
//					break;
//				case "END":
//					processEND();
//					break;
//				}
//			
//			if (input[0].equals("END")) break;
//		}
//		
//	}
//	
//	private static void processSUB(String[] input) {
//		OrderDetails details = prepareOrderDetails(input);
//		
//		try {
//			Order order = OrderFactory.createOrder(details);
//			orderBook.submitOrder(order);
//		} catch (InvalidOrderTypeException e) {
//			System.out.println(e.getMessage());
//		}
//		
//	}
//	
//	private static void processCXL(String[] input) {
//		String orderId = input[1];
//		
//		orderBook.cancelOrder(orderId);
//	}
//	
//	private static void processEND() {
//		orderBook.printBuyOrders();
//		orderBook.printSellOrders();
//	}
//	
//	private static OrderDetails prepareOrderDetails(String[] input) {
//		ORDERTYPE orderType = ORDERTYPE.valueOf(input[1]);
//		SIDE side = SIDE.valueOf(input[2]);
//		String orderId = input[3];
//		Integer quantity = Integer.parseInt(input[4]);
//		Integer price = input.length > 5 ? Integer.parseInt(input[5]) : -1 ;
//		GLOBAL_TIMESTAMP++;
//		
//		return new OrderDetails(orderId, side, orderType, quantity, price, GLOBAL_TIMESTAMP);
//		
//	}
//}
//
//
//class OrderFactory {
//	public static Order createOrder(OrderDetails details) throws InvalidOrderTypeException{
//		switch (details.getOrderType()) {
//		case LO: 
//			return new LimitOrder(details);
//		case MO:
//			return new MarketOrder(details);
//		default:
//			throw new InvalidOrderTypeException("Invalid Order Type");
//		}
//	}
//}
//
//
//// Order
//abstract class Order {
//	
//	abstract String getOrderId();
//	abstract ORDERTYPE getOrderType();
//	abstract SIDE getSide();
//	abstract Integer getQuantity();
//	abstract void setQuantity(Integer quantity);
//	abstract Integer getPrice();
//	abstract Integer getTimeStamp();
//	
//	abstract int buy(Order order, PriorityQueue<Order> sellOrders);
//	abstract int sell(Order order, PriorityQueue<Order> buyOrders);
//}
//
//
//class LimitOrder extends Order {
//	private String orderId;
//	private ORDERTYPE orderType;
//	private SIDE side;
//	private Integer quantity;
//	private Integer price;
//	private Integer timestamp;
//	
//	LimitOrder(OrderDetails details) {
//		this.orderId = details.getOrderId();
//		this.orderType = ORDERTYPE.LO;
//		this.side = details.getSide();
//		this.quantity = details.getQuantity();
//		this.price = details.getPrice();
//		this.timestamp = details.getTimeStamp();
//	}
//	
//	@Override
//	public String getOrderId() {
//		return this.orderId;
//	}
//
//	@Override
//	public Integer getQuantity() {
//		return this.quantity;
//	}
//
//	@Override
//	public Integer getPrice() {
//		return this.price;
//	}
//
//	@Override
//	public ORDERTYPE getOrderType() {
//		return this.orderType;
//	}
//
//	@Override
//	public SIDE getSide() {
//		return this.side;
//	}
//	
//	@Override
//	public Integer getTimeStamp() {
//		return this.timestamp;
//	}
//
//	@Override
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//
//	@Override
//	public int buy(Order order, PriorityQueue<Order> sellOrders) { 
//		int cost = 0;
//		
//		while(isBuyable(order, sellOrders)) {
//			System.out.println("yes in ----------- " + order.getOrderId());
//			Order sellOrder = sellOrders.poll();
//			boolean isSellOrderQtyGreater = sellOrder.getQuantity() >= order.getQuantity() ? true : false;
//			
//			if (isSellOrderQtyGreater) {
//				cost += order.getQuantity() * sellOrder.getPrice();
//				sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
//				order.setQuantity(0);
//				
//				if (sellOrder.getQuantity() > 0) sellOrders.add(sellOrder);
//			} else {
//				cost += sellOrder.getQuantity() * sellOrder.getPrice();
//				order.setQuantity(order.getQuantity() - sellOrder.getQuantity());
//			}
//		}
//		
//		return cost;
//	}
//	
//	private boolean isBuyable(Order order, PriorityQueue<Order> sellOrders) {
//		return !sellOrders.isEmpty() && order.getQuantity() > 0 && sellOrders.peek().getPrice() <= order.getPrice();
//	}
//
//	@Override
//	public int sell(Order order, PriorityQueue<Order> buyOrders) {
//		int cost = 0;
//		
//		while(isSellable(order, buyOrders)) {
//			Order buyOrder = buyOrders.poll();
//			boolean isBuyOrderQtyGreater = buyOrder.getQuantity() >= order.getQuantity() ? true : false;
//			
//			if (isBuyOrderQtyGreater) {
//				cost += order.getQuantity() * buyOrder.getPrice();
//				buyOrder.setQuantity(buyOrder.getQuantity() - order.getQuantity());
//				order.setQuantity(0);
//				
//				if (buyOrder.getQuantity() > 0) buyOrders.add(buyOrder);
//			} else {
//				cost += buyOrder.getQuantity() * buyOrder.getPrice();
//				order.setQuantity(order.getQuantity() - buyOrder.getQuantity());
//			}
//		}
//		
//		
//		return cost;
//	}
//	
//	private boolean isSellable(Order order, PriorityQueue<Order> buyOrders) {
//		return !buyOrders.isEmpty() && order.getQuantity() > 0 &&buyOrders.peek().getPrice() >= order.getPrice();
//	}
//
//	
//}
//
//class MarketOrder extends Order {
//	private String orderId;
//	private ORDERTYPE orderType;
//	private SIDE side;
//	private Integer quantity;
//	private Integer timestamp;
//	
//	MarketOrder(OrderDetails details) {
//		this.orderId = details.getOrderId();
//		this.orderType = ORDERTYPE.MO;
//		this.side = details.getSide();
//		this.quantity = details.getQuantity();
//		this.timestamp = details.getTimeStamp();
//	}
//	
//	@Override
//	public String getOrderId() {
//		return this.orderId;
//	}
//
//	@Override
//	public Integer getQuantity() {
//		return this.quantity;
//	}
//
//	@Override
//	public Integer getPrice() {
//		return -1;
//	}
//
//	@Override
//	public ORDERTYPE getOrderType() {
//		return this.orderType;
//	}
//
//	@Override
//	public SIDE getSide() {
//		return this.side;
//	}
//	
//	@Override
//	public Integer getTimeStamp() {
//		return this.timestamp;
//	}
//
//	@Override
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//	
//	public int buy(Order order, PriorityQueue<Order> sellOrders) {
//		int cost = 0;
//		
//		while(isBuyable(order, sellOrders)) {
//			Order sellOrder = sellOrders.poll();
//			boolean isSellOrderQtyGreater = sellOrder.getQuantity() >= order.getQuantity() ? true : false;
//			
//			if (isSellOrderQtyGreater) {
//				cost += order.getQuantity() * sellOrder.getPrice();
//				sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
//				order.setQuantity(0);
//				
//				if (sellOrder.getQuantity() > 0) sellOrders.add(sellOrder);
//			} else {
//				cost += sellOrder.getQuantity() * sellOrder.getPrice();
//				order.setQuantity(order.getQuantity() - sellOrder.getQuantity());
//			}
//		}
//		
//		return cost;
//	}
//	
//	private boolean isBuyable(Order order, PriorityQueue<Order> sellOrders) {
//		return !sellOrders.isEmpty() && order.getQuantity() > 0;
//	}
//	
//	public int sell(Order order, PriorityQueue<Order> buyOrders) {
//		int cost = 0;
//		
//		while(isSellable(order, buyOrders)) {
//			Order buyOrder = buyOrders.poll();
//			boolean isBuyOrderQtyGreater = buyOrder.getQuantity() >= order.getQuantity() ? true : false;
//			
//			if (isBuyOrderQtyGreater) {
//				cost += order.getQuantity() * buyOrder.getPrice();
//				buyOrder.setQuantity(buyOrder.getQuantity() - order.getQuantity());
//				order.setQuantity(0);
//				
//				if (buyOrder.getQuantity() > 0) buyOrders.add(buyOrder);
//			} else {
//				cost += buyOrder.getQuantity() * buyOrder.getPrice();
//				order.setQuantity(order.getQuantity() - buyOrder.getQuantity());
//			}
//		}
//		
//		return cost;
//	}
//	
//	private boolean isSellable(Order order, PriorityQueue<Order> buyOrders) {
//		return !buyOrders.isEmpty() && order.getQuantity() > 0;
//	}
//	
//	
//}
//
//enum SIDE {
//	B,
//	S
//}
//
//enum ORDERTYPE {
//	LO,
//	MO,
//	RO
//}
//
//class OrderDetails {
//	private String orderId;
//	private SIDE side;
//	private ORDERTYPE orderType;
//	private Integer quantity;
//	private Integer price;
//	private Integer timestamp;
//	
//	OrderDetails(String orderId, SIDE side, ORDERTYPE orderType, Integer quantity, Integer price, Integer timestamp) {
//		this.orderId = orderId;
//		this.side = side;
//		this.orderType = orderType;
//		this.quantity = quantity;
//		this.price = price;
//		this.timestamp = timestamp;
//	}
//	
//	public String getOrderId() {
//		return this.orderId;
//	}
//	
//	public SIDE getSide() {
//		return this.side;
//	}
//	
//	public ORDERTYPE getOrderType() {
//		return this.orderType;
//	}
//	
//	public Integer getQuantity() {
//		return this.quantity;
//	}
//	
//	public Integer getPrice() {
//		return this.price;
//	}
//	
//	public Integer getTimeStamp() {
//		return this.timestamp;
//	}
//	
//	
//}
//
//
//class InvalidOrderTypeException extends Exception {
//	public InvalidOrderTypeException(String errMsg) {
//		super(errMsg);
//	}
//}
//
//
//class OrderBook {
//	private static final String SPACE_DELIMITER = " ";
//	
//	private PriorityQueue<Order> buyOrders;
//	private PriorityQueue<Order> sellOrders;
//	
//	private Map<String, Order> orderTracker;
//	
//	private BuyComparator buyComparator;
//	private SellComparator sellComparator;
//	
//	
//	class BuyComparator implements Comparator<Order> {
//
//		@Override
//		public int compare(Order o1, Order o2) {
//			if (o1.getPrice() < o2.getPrice()) return 1;
//			else if (o1.getPrice() > o2.getPrice()) return -1;
//			else {
//				if (o1.getTimeStamp() < o2.getTimeStamp()) return 1;
//				else if (o1.getTimeStamp() > o2.getTimeStamp()) return -1;
//				else return 0;
//			}
//				
//		}
//		
//	}
//	
//	class SellComparator implements Comparator<Order> {
//
//		@Override
//		public int compare(Order o1, Order o2) {
//			if (o1.getPrice() > o2.getPrice()) return 1;
//			else if (o1.getPrice() < o2.getPrice()) return -1;
//			else {
//				if (o1.getTimeStamp() > o2.getTimeStamp()) return 1;
//				return -1;
//			}
//		}
//	}
//	
//	
//	OrderBook() {
//		this.buyComparator = new BuyComparator();
//		this.sellComparator = new SellComparator();
//		this.buyOrders = new PriorityQueue<>(this.buyComparator);
//		this.sellOrders = new PriorityQueue<>(this.sellComparator);
//		this.orderTracker = new HashMap<>();
//	}
//	
//	// Submit
//	public void submitOrder(Order order) {
//		orderTracker.put(order.getOrderId(), order); // Assuming that orderId is unique
//		
////		System.out.println("--------------------- start ");
////		Iterator it = buyOrders.iterator();
////		while (it.hasNext()) { 
////		    Order order1 = (Order) it.next();
////		    System.out.println(order1.getOrderId() + " " + order1.getQuantity() + " " + order1.getPrice() + " " + order1.getTimeStamp());
////		}
////		System.out.println("--------------------- end ");
//		
//		int totalCost = 0;
//		
//		boolean isBuyinyOrder = order.getSide() == SIDE.B ? true : false;
//		
//		if (isBuyinyOrder) {
//			totalCost += order.buy(order, sellOrders);
//			
//			if (order.getQuantity() > 0 && order.getOrderType() == ORDERTYPE.LO) buyOrders.add(order);
//		} else {
//			totalCost += order.sell(order, buyOrders);
//			
//			if (order.getQuantity() > 0 && order.getOrderType() == ORDERTYPE.LO) sellOrders.add(order);
//		}
//		
//		System.out.println(totalCost);
//		
//		System.out.println("--------------------- start ");
//		Iterator it1 = sellOrders.iterator();
//		while (it1.hasNext()) { 
//		    Order order1 = (Order) it1.next();
//		    System.out.println(order1.getOrderId() + " " + order1.getQuantity() + " " + order1.getPrice() + " " + order1.getTimeStamp());
//		}
//		System.out.println("--------------------- end ");
//	}
//	
//	
//	// Cancel
//	public void cancelOrder(String orderId) {
//		Order order = orderTracker.getOrDefault(orderId, null);
//		if (order == null) {
//			return;
//		}
//		
//		boolean isBuyOrder = order.getSide() == SIDE.B ? true : false;
//
//		if (isBuyOrder) {
//			buyOrders.remove(order); // O(n)
//			return;
//		}
//		
//		sellOrders.remove(order); // O(n)
//	}
//	
//    
//    
//    public void printBuyOrders() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("B:" + SPACE_DELIMITER);
//		
//		String encodedOrders = encodeOrders(sb, buyOrders);
//		System.out.println(encodedOrders);
//    }
//    
//    public void printSellOrders() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("S:" + SPACE_DELIMITER);
//		
//		String encodedOrders = encodeOrders(sb, sellOrders);
//		System.out.println(encodedOrders);
//    }
//    
//    private String encodeOrders(StringBuilder sb, PriorityQueue<Order> orders) {
//		while(!orders.isEmpty()) {
//			Order order = orders.poll();
//			String encodedOrder = encodeOrder(order);
//			sb.append(encodedOrder + SPACE_DELIMITER);
//		}
//		
//		return sb.toString();
//    }
//    
//	
//	private String[] decodeOrder(String order) {
//		return order.split("@|#");
//	}
//	
//	
//	private String encodeOrder(Order order) {
//		return order.getQuantity()+"@"+order.getPrice()+"#"+order.getOrderId();
//	}
//	
//	
//}
//
//// TODO 2번째 문제
//// Trader의 profit loss tracking 
//class Trader {
//	private Integer tradeId;
//	private Integer totalProfit;
//	private Integer totalLoss;
//	private Set<Order> tradeOrders;
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//}
//
//
//
//
//
//
//
//
//
//
//
///*
// * 	
//SUB LO B Ffuj 200 13
//SUB LO B Yy7P 150 11
//SUB LO B YuFU 100 13
//SUB LO S IpD8 150 14
//SUB LO S y93N 190 15
//SUB LO B Y5wb 230 14
//SUB MO B IZLO 250
//CXL Ffuj
//CXL 49Ze
//END
//
//SUB LO B c9Xt 200 10
//SUB MO B ESSq 300
//CXL i9Ze
//SUB LO S Zfjg 300 13
//SUB LO S p7kU 250 13
//SUB LO S rrjX 700 13
//SUB LO S W8DN 400 13
//CXL p7kU
//SUB MO B Q9DZ 1270
//END
// * 
// */
