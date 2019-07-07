package com.timbuchalka;

import java.util.Map;

public class MainChallenge {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        Basket timsBasket = new Basket("Tim's basket");
        Basket tomsBasket = new Basket("Tom's basket");

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        putInBasket(timsBasket,"bread",80);
        putInBasket(timsBasket,"cake",5);
        putInBasket(timsBasket,"car",1);

        putInBasket(tomsBasket,"car",1);
        putInBasket(tomsBasket,"chair",8);
        putInBasket(tomsBasket,"car",1);           //teszteli mi történik ha túllépjük a készletet

        System.out.println(stockList);
        System.out.println(timsBasket);
        System.out.println(tomsBasket);

        reduceQuantityFromBasket(timsBasket,"car",8);       //teszteli mi történik amikor többet akarunk visszavonni
        reduceQuantityFromBasket(tomsBasket,"chair",7);     // sima reduce a kereteken belül

        System.out.println(timsBasket);
        System.out.println(tomsBasket);

        checkOut(timsBasket);
        checkOut(tomsBasket);

        System.out.println(stockList);              //a raktári készletből levonja a várásolt termékeket
        System.out.println(timsBasket);             //üresnek kell lennie
        System.out.println(tomsBasket);             //üresnek kell lennie

    }

    public static void putInBasket(Basket basket, String name, int quantity){
        if(stockList.get(name) == null){
            System.out.println("Sorry, we dont sell " + name);
        }

        StockItem item = stockList.get(name);
        basket.addToBasket(item,quantity);
    }

    public static void reduceQuantityFromBasket(Basket basket, String name, int quantity){
        //do we have such product?
        StockItem product = stockList.get(name);
        //if yes
        if((product != null) && (basket.getInBasket().containsKey(product))){
            basket.removeFromBasket(product,quantity);
        } else {
            System.out.println("Sorry, your request cant be done.");
        }
    }

    public static void checkOut(Basket basket){
        for(StockItem item : basket.getInBasket().keySet()){
            item.adjustStock(-item.getReserved());
        }

        basket.clearBasket();
        System.out.println("\nThank you for your purchase.");
    }
}


    /*

    Modify the program so that adding items to the shopping basket doesn't
    actually reduce the stock count but, instead, reserves the requested
    number of items.

    You will need to add a "reserved" field to the StockItem class to store the
    number of items reserved.

    Items can continue to be added to the basket, but it should not be possible to
    reserve more than the available stock of any item. An item's available stock
    is the stock count less the reserved amount.

    The stock count for each item is reduced when a basket is checked out, at which
    point all reserved items in the basket have their actual stock count reduced.

    Once checkout is complete, the contents of the basket are cleared.

    It should also be possible to "unreserve" items that were added to the basket
    by mistake.

    The program should prevent any attempt to unreserve more items than were
    reserved for a basket.

    Add code to Main that will unreserve items after they have been added to the basket,
    as well as unreserving items that have not been added to make sure that the code
    can cope with invalid requests like that.

    After checking out the baskets, display the full stock list and the contents of each
    basket that you created.

     */