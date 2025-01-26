package lk.ijse.assignment_javaee.bo;

import lk.ijse.assignment_javaee.bo.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    public BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOTypes{
        USER,CATEGORY,PRODUCT,CART,ORDER
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
                case CATEGORY:
                    return new CategoryBOImpl();
                case PRODUCT:
                    return new ProductBOImpl();
                    case CART:
                        return new CartBOImpl();
            case ORDER:
                return new OrderBOImpl();
            default:
                return null;
        }
    }
}
