package lk.ijse.assignment_javaee.dao;

import lk.ijse.assignment_javaee.dao.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;
    public DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        USER,CATEGORY,PRODUCT,CART,ORDER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOImpl();
            case CATEGORY:
                return new CategoryDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
                case CART:
                    return new CartDAOImpl();
                    case ORDER:
                        return new OrderDAOImpl();
            default:
                return null;
        }
    }
}
