/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author stoja
 */
public abstract class Command implements Serializable {
    
    public enum Type{
        CREATE_CITY,
        CREATE_USER,
        ADD_MONEY_TO_USER,
        CHANGE_ADDRESS_AND_CITY_FOR_USER,
        CREATE_CATEGORY,
        CREATE_ARTICLE,
        CHANGE_ARTICLE_PRICE,
        CHANGE_DISCOUNT_FOR_ARTICLE,
        ADD_ARTICLE_AMOUNT_IN_CART,
        REMOVE_ARTICLE_AMOUNT_IN_CART,
        PAYMENT_PROCESS,
        GET_CITIES,
        GET_USERS,
        GET_CATEGORIES,
        GET_ALL_ARTICLES_THAT_USER_SELLS,
        GET_ALL_ITEMS_IN_CART_FOR_USER,
        GET_ALL_ORDERS_FOR_USER,
        GET_ALL_ORDERS,
        GET_ALL_TRANSACTIONS
    }
    
    public enum Destination{
        SUBSYSTEM1,
        SUBSYSTEM2,
        SUBSYSTEM3        
    }
 
    protected final UUID uuid;

    public Command() {
        this.uuid = UUID.randomUUID();
    }
    
    public String getId() {
        return uuid.toString();
    }
    @Override
    public String toString() {
        return "Command[type=" + getType() + ", id=" + uuid + ", dest=" + getDestination() + "]";
    }
    
    abstract public Destination getDestination();
    abstract public Type getType();
}
