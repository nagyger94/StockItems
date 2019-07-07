package com.timbuchalka;

import java.util.*;

/**
 * Created by dev on 17/02/2016.
 */
public class Basket {
    private final String name;
    private final Map<StockItem, Integer> inBasket;


    public Basket(String name) {
        this.name = name;
        this.inBasket = new LinkedHashMap<>();
    }

    public void removeFromBasket(StockItem item, int quantity){
        if ((item != null) && (quantity > 0)){
            if(item.getReserved() <= quantity){
                inBasket.remove(item);
                System.out.println("\n" + item.getName() + " has been removed from the basket.");
            } else {
                System.out.println("\n" + item.getName() + "'s quantity has benn reduced by " + quantity);
                inBasket.put(item, item.unreservedStock(quantity));
            }
        }
    }


    public int addToBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0) && (item.reserveStock(quantity) != 0)) {
            int inBasket = this.inBasket.getOrDefault(item, 0);
            this.inBasket.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public void clearBasket(){
        inBasket.clear();
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(inBasket);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + inBasket.size() + ((inBasket.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : inBasket.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }

    public Map<StockItem, Integer> getInBasket() {
        return this.inBasket;
    }
}
