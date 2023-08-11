//import java.util.Comparator;
//import java.util.HashSet;
//import java.util.PriorityQueue;
//import java.util.Scanner;
//import java.util.Set;
//
//
//public class Alpha {
//
//	static OrderBook ob = new OrderBook();
//	public Alpha() {
//	}
//	
//	public static void main(String args[]) throws Exception {
//		
//		Scanner sc = new Scanner(System.in).useDelimiter("\\n");
//		
//		
//		while(sc.hasNext()) {
//			String cmd = sc.next();
//			
//			String[] input = cmd.split(" ");
//			
//			String action = input[0];
//			
//			
//			switch (action) {
//				case "SUB":
//					ob.processSUB(input);
//					break;
//				case "CXL":
//					ob.processCXL(input);
//					break;
//				case "END":
//					ob.processEND();
//					break;
//				default:
//					break;
//			}
//
//		}
//		
//	}
//}
//
//
//
//abstract class OrderFactory {
//	static Order createOrder(OrderDto orderDTO) throws InvalidOrderType {
//		switch(orderDTO.getOrderType()) {
//			case LO:
//				return new LimitOrder(orderDTO);
//			case MO:
//				return new MarketOrder(orderDTO);
//			default:
//				throw new InvalidOrderType("Invalid order type: " + orderDTO.getOrderType());
//		}
//		
//	}
//}
//
//abstract class Order {
//	private String orderId;
//	private ORDER_TYPE orderType;
//	private SIDE side;
//	private Integer quantity;
//	private Integer timestamp;
//	
//	abstract String getOrderId();
//	abstract ORDER_TYPE getOrderType();
//	abstract SIDE getSide();
//	abstract Integer getPrice();
//	abstract Integer getQuantity();
//	abstract void setQuantity(Integer quantity);
//	abstract Integer getTimeStamp();
//	
//}
//
//
// /*
// 	Entity 
// */
//
//class LimitOrder extends Order{
//	private String orderId;
//	private ORDER_TYPE orderType;
//	private SIDE side;
//	private Integer price;
//	private Integer quantity;
//	private Integer timestamp;
//	
//	// default constructor
//	public LimitOrder(OrderDto orderDto) {
//		this.orderId = orderDto.getOrderId();
//		this.orderType = orderDto.getOrderType();
//		this.side = orderDto.getSide();
//		this.price = orderDto.getPrice();
//		this.quantity = orderDto.getQuantity();
//		this.timestamp = orderDto.getTimeStamp();
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
//	public Integer getPrice() {
//		return this.price;
//	}
//	
//	public Integer getQuantity() {
//		return this.quantity;
//	}
//	
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//	
//	public Integer getTimeStamp() {
//		return this.timestamp;
//	}
//
//	public ORDER_TYPE getOrderType() {
//		return this.getOrderType();
//	}
//	
//}
//
//class MarketOrder extends Order{
//	private String orderId;
//	private ORDER_TYPE orderType;
//	private SIDE side;
//	private Integer quantity;
//	private Integer timestamp;
//	
//	// default constructor
//	public MarketOrder(OrderDto orderDto) {
//		this.orderId = orderDto.getOrderId();
//		this.orderType = orderDto.getOrderType();
//		this.side = orderDto.getSide();
//		this.quantity = orderDto.getQuantity();
//		this.timestamp = orderDto.getTimeStamp();
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
//	// MarketOrder will have 0 price
//	public Integer getPrice() {
//		return 0;
//	}
//	
//	
//	public Integer getQuantity() {
//		return this.quantity;
//	}
//	
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//	
//	public Integer getTimeStamp() {
//		return this.timestamp;
//	}
//	
//	public ORDER_TYPE getOrderType() {
//		return this.getOrderType();
//	}
//	
//}
//
//
//class OrderBook {	
//	private static final String SPACE_DELIMITER = " ";
//
//	private PriorityQueue<Order> buyOrders;
//	private PriorityQueue<Order> sellOrders;
//    private BuyOrderComparator buyOrderComparator;
//    private SellOrderComparator sellOrderComparator;
//    private Integer timestamp = 0;
//
//	OrderBook() {
//		this.buyOrderComparator = new BuyOrderComparator();
//		this.sellOrderComparator = new SellOrderComparator();
//		this.buyOrders = new PriorityQueue<>(this.buyOrderComparator);
//		this.sellOrders = new PriorityQueue<>(this.sellOrderComparator);
//	}
//
//	
//    class BuyOrderComparator implements Comparator<Order> {
//        @Override
//        public int compare(Order o1, Order o2) {
//        	  if (o1.getPrice() > o2.getPrice()) {
//      	    	return 1;
//      	    } else if (o1.getPrice() < o2.getPrice()) {
//      	    	return -1;
//      	    } else {
//      	    	if (o1.getTimeStamp() < o2.getTimeStamp()) {
//      	    		return 1;
//      	    	} else {
//      	    		return -1;
//      	    	}
//      	    }
//        }
//    }
//	
//    class SellOrderComparator implements Comparator<Order> {
//        @Override
//        public int compare(Order o1, Order o2) {
//		    if (o1.getPrice() < o2.getPrice()) {
//		    	return 1;
//		    } else if (o1.getPrice() > o2.getPrice()) {
//		    	return -1;
//		    } else {
//		    	if (o1.getTimeStamp() < o2.getTimeStamp()) {
//		    		return 1;
//		    	} else {
//		    		return -1;
//		    	}
//		   }
//        }
//    }
//    
//	public void processSUB(String[] input) {
//		ORDER_TYPE orderType = ORDER_TYPE.valueOf(input[1]);
//		SIDE side = SIDE.valueOf(input[2]);
//		String orderId = input[3];
//		Integer quantity = Integer.parseInt(input[4]);
//		Integer price = 0;
//		if (input.length > 5) {
//			price = Integer.parseInt(input[5]);
//		}
//
//		OrderDto orderDto = new OrderDto(orderId, side, orderType, price, quantity, timestamp);
//		
//		try {
//			Order order = OrderFactory.createOrder(orderDTO);
//			submitOrder(order);
//		} catch (InvalidOrderType e) {
//			System.out.println(e.getMessage());
//		}
//		
//	}
//    
//    
//	public void processCXL(String[] input) {
//		String orderId = input[3];
//		
//		cancelOrder(orderId);
//		
//	}
//    
//	public void processEND() {
//		printBuyOrders();
//		printSellOrders();
//	}
//    
//    
//    private void submitOrder(Order order) {
//    	
//    	switch (order.getOrderType()) {
//    		case LO:
//    	    	switch (order.getSide()) {
//    		    	case B: 
//    		    		processLOBuyingOrder(order);
//    		    		break;
//    		    	case S:
//    		    		processLOSellingOrder(order);
//    		    		break;
//    		    	default:
//    		    		return;
//    	    	}
//    		case MO: 
//    	    	switch (order.getSide()) {
//    		    	case B: 
//    		    		processMOBuyingOrder(order);
//    		    		break;
//    		    	case S:
//    		    		processMOSellingOrder(order);
//    		    		break;
//    		    	default:
//    		    		return;
//    	    	}
//    	}
//
//    	
//    }
//    
//    private void processLOBuyingOrder(Order order) {
//		int totalCost = 0;
//		
//		while(!sellOrders.isEmpty() && isMatch(order, sellOrders.peek())) {
//			Order sellOrder = sellOrders.poll();
//			
//			if (order.getQuantity() > sellOrder.getQuantity()) {
//				totalCost += sellOrder.getQuantity() * sellOrder.getQuantity();
//				order.setQuantity(order.getQuantity() - sellOrder.getQuantity());
//			} else {
//				totalCost += sellOrder.getPrice() * order.getQuantity();
//				sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
//				
//				if (sellOrder.getQuantity() > 0) {
//					sellOrders.add(sellOrder);
//				}
//				break;
//			}
//		}
//		
//		
//		if (order.getQuantity() > 0) {
//			buyOrders.add(order);
//		}
//		
//		if (totalCost > 0) System.out.println(totalCost);
//    }
//    
//    private void processLOSellingOrder(Order order) {
//    	int totalCost = 0;
//    	
//		while(!buyOrders.isEmpty() && isMatch(buyOrders.peek(), order)) {
//			Order buyOrder = buyOrders.poll();
//			
//			
//			if (order.getQuantity() > buyOrders.peek().getQuantity()) {
//				totalCost += buyOrder.getPrice() * buyOrder.getQuantity();
//				order.setQuantity(order.getQuantity() - buyOrder.getQuantity());
//			} else {
//				totalCost += buyOrder.getPrice() * order.getQuantity();
//				buyOrder.setQuantity(buyOrder.getQuantity() - order.getQuantity());
//				
//				if (buyOrder.getQuantity() > 0) {
//					buyOrders.add(buyOrder);
//				}
//				break;
//			}
//		}
//		
//		if (order.getQuantity() > 0) {
//			sellOrders.add(order);
//		}
//		if (totalCost > 0) System.out.println(totalCost);
//    }
//    
//    
//    private void processMOBuyingOrder(Order order) {
//		int totalCost = 0;
//		
//		while(!sellOrders.isEmpty() && order.getQuantity() > 0) {
//			Order sellOrder = sellOrders.poll();
//			
//			if (order.getQuantity() > sellOrder.getQuantity()) {
//				totalCost += sellOrder.getQuantity() * sellOrder.getQuantity();
//				order.setQuantity(order.getQuantity() - sellOrder.getQuantity());
//			} else {
//				totalCost += sellOrder.getPrice() * order.getQuantity();
//				sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
//				
//				if (sellOrder.getQuantity() > 0) {
//					sellOrders.add(sellOrder);
//				}
//				break;
//			}
//		}
//		
//		
//		if (order.getQuantity() > 0) {
//			buyOrders.add(order);
//		}
//		
//		if (totalCost > 0) System.out.println(totalCost);
//    }
//    
//    private void processMOSellingOrder(Order order) {
//    	int totalCost = 0;
//    	
//		while(!buyOrders.isEmpty() && order.getQuantity() > 0) {
//			Order buyOrder = buyOrders.poll();
//			
//			
//			if (order.getQuantity() > buyOrders.peek().getQuantity()) {
//				totalCost += buyOrder.getPrice() * buyOrder.getQuantity();
//				order.setQuantity(order.getQuantity() - buyOrder.getQuantity());
//			} else {
//				totalCost += buyOrder.getPrice() * order.getQuantity();
//				buyOrder.setQuantity(buyOrder.getQuantity() - order.getQuantity());
//				
//				if (buyOrder.getQuantity() > 0) {
//					buyOrders.add(buyOrder);
//				}
//				break;
//			}
//		}
//		
//		if (order.getQuantity() > 0) {
//			sellOrders.add(order);
//		}
//		if (totalCost > 0) System.out.println(totalCost);
//    }
//        
//    private void cancelOrder(String orderId) {
//    	// Iterate both buyOrders and sellOrders to remove a certain orderId
//    	
//    	// Could have been improved using Set and HashMap DS
//    	Set<Order> orderSet = new HashSet<>();
//    	
//    	while(!buyOrders.isEmpty()) {
//    		Order order = buyOrders.poll();
//    		if (!order.getOrderId().equals(orderId)) {
//    			orderSet.add(order);
//    		}
//    	}
//    	
//    	buyOrders.addAll(orderSet);
//    		
//    	
//    	orderSet = new HashSet<>();
//    	
//    	while(!sellOrders.isEmpty()) {
//    		Order order = sellOrders.poll();
//    		if (!order.getOrderId().equals(orderId)) {
//    			orderSet.add(order);
//    		}
//    	}	
//    	
//    	
//    	sellOrders.addAll(orderSet);
//    	
//    }
//    
//    
//    
//    
//    
//    private boolean isMatch(Order buyOrder, Order sellOrder) {
//		return buyOrder.getPrice() >= sellOrder.getPrice();
//    }
//    
//    
//    
//    private String printBuyOrders() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("B:" + SPACE_DELIMITER);
//		
//		while(!buyOrders.isEmpty()) {
//			Order order = buyOrders.poll();
//			String encodedOrder = encodeOrder(order.getOrderId(), order.getPrice(), order.getQuantity());
//			sb.append(encodedOrder + SPACE_DELIMITER);
//		}
//		
//		return sb.toString();
//    }
//    
//    private String printSellOrders() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("B:" + SPACE_DELIMITER);
//		
//		while(!sellOrders.isEmpty()) {
//			Order order = buyOrders.poll();
//			String encodedOrder = encodeOrder(order.getOrderId(), order.getPrice(), order.getQuantity());
//			sb.append(encodedOrder + SPACE_DELIMITER);
//		}
//		
//		return sb.toString();
//    	
//    }
//    
//	
//	private String[] decodeOrder(String order) {
//		return order.split("@|#");
//	}
//	
//	
//	private String encodeOrder(String orderId, Integer price, Integer quantity) {
//		return quantity+"@"+price+"#"+orderId;
//	}
//}
//
//
//
//
///*
// 	Enum
// */
//
//
//enum SIDE {
//	B,
//	S
//}
//
//enum ORDER_TYPE {
//	LO,
//	MO
//}
//
//
///*
// 	Dto
// */
//
//class OrderDto {
//	private String orderId;
//	private SIDE side;
//	private ORDER_TYPE orderType;
//	private Integer price;
//	private Integer quantity;
//	private Integer timestamp;
//	
//	public OrderDto(String orderId, SIDE side, ORDER_TYPE orderType, Integer price, Integer quantity, Integer timestamp) {
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
//	public ORDER_TYPE getOrderType() {
//		return this.orderType;
//	}
//	
//	public SIDE getSide() {
//		return this.side;
//	}
//	
//	public Integer getPrice() {
//		return this.price;
//	}
//	
//	public Integer getQuantity() {
//		return this.quantity;
//	} 
//	
//	public Integer getTimeStamp() {
//		return this.timestamp;
//	}
//}
//
//
//
///*
// 	Exception
// */
//class InvalidOrderType extends Exception {
//	public InvalidOrderType(String errMsg) {
//		super(errMsg);
//	}
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
//	
//	
//	
//}
