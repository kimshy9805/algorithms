import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class AlphaLab2 {
    private static Integer GLOBAL_TIMESTAMP = 0;
    private static OrderBook orderBook = new OrderBook();

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in).useDelimiter("\\n");
        
        int totalTradersNum = sc.nextInt();
        while(totalTradersNum-- > 0) {
            orderBook.createTrader(sc.next());
        }
        
        while(sc.hasNext()) {
            String[] input = sc.next().split(" ");
            
            String action = input[0];
            
            switch (action) {
                case "SUB":
                    processSUB(input);
                    break;
                case "CXL":
                    processCXL(input);
                    break;
                case "END":
                    processEND();
                    break;
                }
            
            if (input[0].equals("END")) break;
        }
        
    }
    
    // SUB operation
    private static void processSUB(String[] input) {
        OrderDetails details = prepareOrderDetails(input);
        
        try {
            Order order = OrderFactory.createOrder(details);
            orderBook.submitOrder(order);
        } catch (InvalidOrderTypeException e) {
//            System.out.println(e.getMessage());
            return;
        }
        
    }
    
    // CXL operation
    private static void processCXL(String[] input) {
        String traderId = input[1];
        String orderId = input[2];
        
        orderBook.cancelOrder(traderId, orderId);
    }
    
    // END operation
    private static void processEND() {
        orderBook.printBuyOrders();
        orderBook.printSellOrders();
        orderBook.printAllTradersInfo();
    }
    
    // helper method to generate OrderDetails object
    private static OrderDetails prepareOrderDetails(String[] input) {
        ORDERTYPE orderType = ORDERTYPE.valueOf(input[1]);
        String traderId = input[2];
        SIDE side = SIDE.valueOf(input[3]);
        String orderId = input[4];
        Integer quantity = Integer.parseInt(input[5]);
        Integer price = input.length > 6 ? Integer.parseInt(input[6]) : -1 ;
        GLOBAL_TIMESTAMP++;
        
        return new OrderDetails(traderId, orderId, side, orderType, quantity, price, GLOBAL_TIMESTAMP);
        
    }
}

// Order Factory to generate an order with different type
class OrderFactory {
    public static Order createOrder(OrderDetails details) throws InvalidOrderTypeException{
        switch (details.getOrderType()) {
        case LO: 
            return new LimitOrder(details);
        case MO:
            return new MarketOrder(details);
        default:
            throw new InvalidOrderTypeException("Invalid Order Type");
        }
    }
}


// Order
abstract class Order {
    abstract String getTraderId();
    abstract String getOrderId();
    abstract ORDERTYPE getOrderType();
    abstract SIDE getSide();
    abstract Integer getQuantity();
    abstract void setQuantity(Integer quantity);
    abstract Integer getPrice();
    abstract Integer getTimeStamp();
    
    abstract int buy(Order order, PriorityQueue<Order> sellOrders, TraderDatabase traderDatabase);
    abstract int sell(Order order, PriorityQueue<Order> buyOrders, TraderDatabase traderDatabase);
}


class LimitOrder extends Order {
    private String traderId;
    private String orderId;
    private ORDERTYPE orderType;
    private SIDE side;
    private Integer quantity;
    private Integer price;
    private Integer timestamp;
    
    LimitOrder(OrderDetails details) {
        this.traderId = details.getTraderId();
        this.orderId = details.getOrderId();
        this.orderType = ORDERTYPE.LO;
        this.side = details.getSide();
        this.quantity = details.getQuantity();
        this.price = details.getPrice();
        this.timestamp = details.getTimeStamp();
    }
    

    @Override
    public String getTraderId() {
        return this.traderId;
    }
    
    @Override
    public String getOrderId() {
        return this.orderId;
    }

    @Override
    public Integer getQuantity() {
        return this.quantity;
    }

    @Override
    public Integer getPrice() {
        return this.price;
    }

    @Override
    public ORDERTYPE getOrderType() {
        return this.orderType;
    }

    @Override
    public SIDE getSide() {
        return this.side;
    }
    
    @Override
    public Integer getTimeStamp() {
        return this.timestamp;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int buy(Order order, PriorityQueue<Order> sellOrders, TraderDatabase traderDatabase) { 
        int totalCost = 0;
        
        while(isBuyable(order, sellOrders)) {
            int cost = 0;
            Order sellOrder = sellOrders.poll();
            boolean isSellOrderQtyGreater = sellOrder.getQuantity() >= order.getQuantity() ? true : false;
            
            if (isSellOrderQtyGreater) {
                cost = order.getQuantity() * sellOrder.getPrice();
                totalCost += cost;
                
                sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
                order.setQuantity(0);
                
                if (sellOrder.getQuantity() > 0) sellOrders.add(sellOrder);
            } else {
                cost = sellOrder.getQuantity() * sellOrder.getPrice();
                totalCost += cost;
                
                order.setQuantity(order.getQuantity() - sellOrder.getQuantity());
            }
            
            // update maker
            traderDatabase.updateTraderInfo(sellOrder.getTraderId(), cost, 0);
            
            // update taker
            traderDatabase.updateTraderInfo(order.getTraderId(), 0, cost);
        }
        
        return totalCost;
    }
    
    private boolean isBuyable(Order order, PriorityQueue<Order> sellOrders) {
        return !sellOrders.isEmpty() && order.getQuantity() > 0 && sellOrders.peek().getPrice() <= order.getPrice();
    }

    @Override
    public int sell(Order order, PriorityQueue<Order> buyOrders, TraderDatabase traderDatabase) {
        int totalCost = 0;
        
        while(isSellable(order, buyOrders)) {
            int cost = 0;
            Order buyOrder = buyOrders.poll();
            boolean isBuyOrderQtyGreater = buyOrder.getQuantity() >= order.getQuantity() ? true : false;
            
            if (isBuyOrderQtyGreater) {
                cost = order.getQuantity() * buyOrder.getPrice();
                totalCost += cost;
                
                buyOrder.setQuantity(buyOrder.getQuantity() - order.getQuantity());
                order.setQuantity(0);
                
                if (buyOrder.getQuantity() > 0) buyOrders.add(buyOrder);
            } else {
                cost = buyOrder.getQuantity() * buyOrder.getPrice();
                totalCost += cost;
                
                order.setQuantity(order.getQuantity() - buyOrder.getQuantity());
            }
            
            // update maker
            traderDatabase.updateTraderInfo(buyOrder.getTraderId(), cost, 0);
            
            // update taker
            traderDatabase.updateTraderInfo(order.getTraderId(), 0, cost);
        }
        
        
        return totalCost;
    }
    
    private boolean isSellable(Order order, PriorityQueue<Order> buyOrders) {
        return !buyOrders.isEmpty() && order.getQuantity() > 0 &&buyOrders.peek().getPrice() >= order.getPrice();
    }

    
}

class MarketOrder extends Order {
    private String traderId;
    private String orderId;
    private ORDERTYPE orderType;
    private SIDE side;
    private Integer quantity;
    private Integer timestamp;
    
    MarketOrder(OrderDetails details) {
        this.traderId = details.getTraderId();
        this.orderId = details.getOrderId();
        this.orderType = ORDERTYPE.MO;
        this.side = details.getSide();
        this.quantity = details.getQuantity();
        this.timestamp = details.getTimeStamp();
    }
    
    @Override
    public String getTraderId() {
        return this.traderId;
    }
    
    @Override
    public String getOrderId() {
        return this.orderId;
    }

    @Override
    public Integer getQuantity() {
        return this.quantity;
    }

    @Override
    public Integer getPrice() {
        return -1;
    }

    @Override
    public ORDERTYPE getOrderType() {
        return this.orderType;
    }

    @Override
    public SIDE getSide() {
        return this.side;
    }
    
    @Override
    public Integer getTimeStamp() {
        return this.timestamp;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public int buy(Order order, PriorityQueue<Order> sellOrders, TraderDatabase traderDatabase) {
        int totalCost = 0;
        
        while(isBuyable(order, sellOrders)) {
            int cost = 0;
            Order sellOrder = sellOrders.poll();
            boolean isSellOrderQtyGreater = sellOrder.getQuantity() >= order.getQuantity() ? true : false;
            
            if (isSellOrderQtyGreater) {
                cost = order.getQuantity() * sellOrder.getPrice();
                totalCost += cost;
                
                sellOrder.setQuantity(sellOrder.getQuantity() - order.getQuantity());
                order.setQuantity(0);
                
                if (sellOrder.getQuantity() > 0) sellOrders.add(sellOrder);
            } else {
                cost = sellOrder.getQuantity() * sellOrder.getPrice();
                totalCost += cost;
                order.setQuantity(order.getQuantity() - sellOrder.getQuantity());
            }
            
            // update maker
            traderDatabase.updateTraderInfo(sellOrder.getTraderId(), cost, 0);
            
            // update taker
            traderDatabase.updateTraderInfo(order.getTraderId(), 0, cost);
        }
        
        return totalCost;
    }
    
    private boolean isBuyable(Order order, PriorityQueue<Order> sellOrders) {
        return !sellOrders.isEmpty() && order.getQuantity() > 0;
    }
    
    @Override
    public int sell(Order order, PriorityQueue<Order> buyOrders, TraderDatabase traderDatabase) {
        int totalCost = 0;
        
        while(isSellable(order, buyOrders)) {
            int cost = 0;
            Order buyOrder = buyOrders.poll();
            boolean isBuyOrderQtyGreater = buyOrder.getQuantity() >= order.getQuantity() ? true : false;
            
            if (isBuyOrderQtyGreater) {
                cost = order.getQuantity() * buyOrder.getPrice();
                totalCost += cost;
                
                buyOrder.setQuantity(buyOrder.getQuantity() - order.getQuantity());
                order.setQuantity(0);
                
                if (buyOrder.getQuantity() > 0) buyOrders.add(buyOrder);
            } else {
                cost = buyOrder.getQuantity() * buyOrder.getPrice();
                totalCost += cost;
                
                order.setQuantity(order.getQuantity() - buyOrder.getQuantity());
            }
            
            // update maker
            traderDatabase.updateTraderInfo(buyOrder.getTraderId(), cost, 0);
            
            // update taker
            traderDatabase.updateTraderInfo(order.getTraderId(), 0, cost);
        }
        
        return totalCost;
    }
    
    private boolean isSellable(Order order, PriorityQueue<Order> buyOrders) {
        return !buyOrders.isEmpty() && order.getQuantity() > 0;
    }
    
    
}

// enum to represent side
enum SIDE {
    B,
    S
}

// enum to represent order type
enum ORDERTYPE {
    LO,
    MO
}

// Act as a DTO to store order details 
class OrderDetails {
    private String traderId;
    private String orderId;
    private SIDE side;
    private ORDERTYPE orderType;
    private Integer quantity;
    private Integer price;
    private Integer timestamp;
    
    OrderDetails(String traderId, String orderId, SIDE side, ORDERTYPE orderType, Integer quantity, Integer price, Integer timestamp) {
        this.traderId = traderId;
        this.orderId = orderId;
        this.side = side;
        this.orderType = orderType;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = timestamp;
    }
    
    public String getTraderId() {
        return this.traderId;
    }
    
    public String getOrderId() {
        return this.orderId;
    }
    
    public SIDE getSide() {
        return this.side;
    }
    
    public ORDERTYPE getOrderType() {
        return this.orderType;
    }
    
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public Integer getPrice() {
        return this.price;
    }
    
    public Integer getTimeStamp() {
        return this.timestamp;
    }
    
    
}


class InvalidOrderTypeException extends Exception {
    public InvalidOrderTypeException(String errMsg) {
        super(errMsg);
    }
}


class OrderBook {
    private static final String SPACE_DELIMITER = " ";
    
    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;
    
    private Map<String, Order> orderTracker;
    
    private TraderDatabase traderDatabase;
    
    private BuyComparator buyComparator;
    private SellComparator sellComparator;
    
    // Custom comparator for buy orders
    class BuyComparator implements Comparator<Order> {

        @Override
        public int compare(Order o1, Order o2) {
            if (o1.getPrice() < o2.getPrice()) return 1;
            else if (o1.getPrice() > o2.getPrice()) return -1;
            else {
                if (o1.getTimeStamp() > o2.getTimeStamp()) return 1;
                return -1;
            }    
        }
        
    }
    
    // Custom comparator for sell orders
    class SellComparator implements Comparator<Order> {

        @Override
        public int compare(Order o1, Order o2) {
            if (o1.getPrice() > o2.getPrice()) return 1;
            else if (o1.getPrice() < o2.getPrice()) return -1;
            else {
                if (o1.getTimeStamp() > o2.getTimeStamp()) return 1;
                return -1;
            }
        }
    }
    
    // Default Constructor
    OrderBook() {
        this.buyComparator = new BuyComparator();
        this.sellComparator = new SellComparator();
        this.buyOrders = new PriorityQueue<>(this.buyComparator);
        this.sellOrders = new PriorityQueue<>(this.sellComparator);
        this.orderTracker = new HashMap<>();
        this.traderDatabase = new TraderDatabase();
    }
    
    // create Trader
    public void createTrader(String traderId) {
        this.traderDatabase.createTrader(traderId);
    }
    
    
    
    // Submit
    public void submitOrder(Order order) {
        if (!traderDatabase.doesTraderExist(order.getTraderId())) return; // verify order 
        
        orderTracker.put(order.getOrderId(), order); // Assuming that orderId is unique
        
        int totalCost = 0;
        
        boolean isBuyinyOrder = order.getSide() == SIDE.B ? true : false;
        
        if (isBuyinyOrder) {
            totalCost += order.buy(order, sellOrders, traderDatabase);
            
            if (order.getQuantity() > 0 && order.getOrderType() == ORDERTYPE.LO) {
                buyOrders.add(order);
                traderDatabase.getTrader(order.getTraderId()).addOrder(order);
            }
        } else {
            totalCost += order.sell(order, buyOrders, traderDatabase);
            
            if (order.getQuantity() > 0 && order.getOrderType() == ORDERTYPE.LO) {
                sellOrders.add(order);
                traderDatabase.getTrader(order.getTraderId()).addOrder(order);
            }
        }
        
        System.out.println(totalCost);

    }
    
    
    // Cancel
    public void cancelOrder(String traderId, String orderId) {
        Order order = orderTracker.getOrDefault(orderId, null);
        if (order == null) {
            return;
        }
        
        Trader trader = traderDatabase.getTrader(traderId);
        if (trader == null) {
            return;
        }
        
        if (!trader.hasOrder(order)) return;
        
        boolean isBuyOrder = order.getSide() == SIDE.B ? true : false;

        if (isBuyOrder) {
            buyOrders.remove(order); // O(n)
            trader.cancelOrder(order); // O(1)
            return;
        }
        
        sellOrders.remove(order); // O(n)
        trader.cancelOrder(order); // O(1)
    }
    
    
    // print buy orders in Order Book
    public void printBuyOrders() {
        StringBuilder sb = new StringBuilder();
        sb.append("B:" + SPACE_DELIMITER);
        
        String encodedOrders = encodeOrders(sb, buyOrders);
        System.out.println(encodedOrders);
    }
    
    // print sell orders in Order Book
    public void printSellOrders() {
        StringBuilder sb = new StringBuilder();
        sb.append("S:" + SPACE_DELIMITER);
        
        String encodedOrders = encodeOrders(sb, sellOrders);
        System.out.println(encodedOrders);
    }
    
    // iterate entire pq and poll to maintain order
    private String encodeOrders(StringBuilder sb, PriorityQueue<Order> orders) {
       while(!orders.isEmpty()) {
           Order order = orders.poll();
           String encodedOrder = encodeOrder(order);
           sb.append(encodedOrder + SPACE_DELIMITER);
       }
        
        return sb.toString();
    }
    
    
    private String[] decodeOrder(String order) {
        return order.split("@|#");
    }
    
    
    private String encodeOrder(Order order) {
        return order.getQuantity()+"@"+order.getPrice()+"#"+order.getOrderId();
    }
    
    public void printAllTradersInfo() {
        this.traderDatabase.printTraderInfo();
    }
    
    
}

class Trader {
    private String traderId;
    private Set<Order> orders; // tracking orders been made by a trader
    private Integer totalMakerCost;
    private Integer totalTakerCost;
    
    Trader(String traderId) {
        this.traderId = traderId;
        this.orders = new HashSet<>();
        this.totalMakerCost = 0;
        this.totalTakerCost = 0;
    }

    public String getTraderId() {
        return traderId;
    }
    
    public boolean hasOrder(Order order) {
        return this.orders.contains(order);
    }
    
    public void addOrder(Order order) {
        this.orders.add(order);
    }
    
    public void cancelOrder(Order order) {
        this.orders.remove(order);
    }

    public Integer getTotalMakerCost() {
        return totalMakerCost;
    }

    public void setTotalMakerCost(Integer totalMakerCost) {
        this.totalMakerCost = totalMakerCost;
    }

    public Integer getTotalTakerCost() {
        return totalTakerCost;
    }

    public void setTotalTakerCost(Integer totalTakerCost) {
        this.totalTakerCost = totalTakerCost;
    }
    
}

class TraderDatabase {
    private Map<String, Trader> traders;
    private TraderComparator traderComparator;
    
    // custom comparator to sort trader by its traderId
    class TraderComparator implements Comparator<Trader> {

        @Override
        public int compare(Trader t1, Trader t2) {
            return t1.getTraderId().compareTo(t2.getTraderId());
        }
        
    }
    
    // Default Constructor
    TraderDatabase() {
        this.traders = new HashMap<>();
        this.traderComparator = new TraderComparator();
    }
    
    // create trader
    public void createTrader(String traderId) {
        if (traders.containsKey(traderId)) return;
        
        traders.put(traderId, new Trader(traderId));
    }
    
    // check trader exists
    public boolean doesTraderExist(String traderId) {
        return traders.containsKey(traderId);
    }
    
    // get trader
    public Trader getTrader(String traderId) {
        return this.traders.getOrDefault(traderId, null);
    }
    
    // update trader info
    public void updateTraderInfo(String traderId, Integer makerCost, Integer takerCost) {
        if (!traders.containsKey(traderId)) return;
        
        Trader trader = traders.get(traderId);
        
        trader.setTotalMakerCost(trader.getTotalMakerCost() + makerCost);
        trader.setTotalTakerCost(trader.getTotalTakerCost() + takerCost);
        
    }
    
    
    // print all traders info 
    public void printTraderInfo() {
        List<Trader> traderList = new ArrayList<>();
        
        for (Trader trader: traders.values()) {
            traderList.add(trader);
        }
        
        Collections.sort(traderList, this.traderComparator); // O(nlogn)
        
        for (Trader trader: traderList) {
            System.out.println(trader.getTraderId() + "-" + trader.getTotalMakerCost() + "-" + trader.getTotalTakerCost());
        }
    }
    
    
}
