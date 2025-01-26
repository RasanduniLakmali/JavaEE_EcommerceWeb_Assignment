package lk.ijse.assignment_javaee.bo.impl;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.bo.ProductBO;
import lk.ijse.assignment_javaee.dao.DAOFactory;
import lk.ijse.assignment_javaee.dao.ProductDAO;
import lk.ijse.assignment_javaee.dto.ProductDTO;
import lk.ijse.assignment_javaee.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);

    @Override
    public boolean saveProduct(ProductDTO productDTO, HttpServletRequest request) {
        return productDAO.save(new Product(productDTO.getProductId(),productDTO.getProductName(),productDTO.getProductPrice(),productDTO.getProductQty(),productDTO.getImage(),productDTO.getCategory()),request);
    }

    @Override
    public String getCurrentProductId(HttpServletRequest request) {
        return productDAO.getCurrentId(request);
    }

    @Override
    public List<ProductDTO> getProducts(HttpServletRequest request) {

        List<Product> products = productDAO.getAll(request);

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product.getProductId(),product.getProductName(),product.getProductPrice(),product.getProductQty(),product.getImage(),product.getCategory());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getProductByName(String productName, HttpServletRequest request) {
        Product product = productDAO.search(productName,request);
        return new ProductDTO(product.getProductId(),product.getProductName(),product.getProductPrice(),product.getProductQty(),product.getImage(),product.getCategory());
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO, HttpServletRequest request) {
        return productDAO.update(new Product(productDTO.getProductId(),productDTO.getProductName(),productDTO.getProductPrice(),productDTO.getProductQty(),productDTO.getImage(),productDTO.getCategory()),request);
    }

    @Override
    public boolean deleteProduct(String productId, HttpServletRequest request) {
        return productDAO.delete(productId,request);
    }

    @Override
    public List<ProductDTO> getMenProducts(HttpServletRequest request) {
        List<Product> products = productDAO.getAllMenProducts(request);

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product.getProductId(),product.getProductName(),product.getProductPrice(),product.getProductQty(),product.getImage(),product.getCategory());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> getWomenProducts(HttpServletRequest request) {
        List<Product> products = productDAO.getAllWomenProducts(request);

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product.getProductId(),product.getProductName(),product.getProductPrice(),product.getProductQty(),product.getImage(),product.getCategory());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> getKidsProducts(HttpServletRequest request) {
        List<Product> products = productDAO.getAllKidsProducts(request);

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products){
            ProductDTO productDTO = new ProductDTO(product.getProductId(),product.getProductName(),product.getProductPrice(),product.getProductQty(),product.getImage(),product.getCategory());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getProductById(String productId, HttpServletRequest request) {
        Product product = productDAO.searchById(productId,request);
        return new ProductDTO(product.getProductId(),product.getProductName(),product.getProductPrice(),product.getProductQty(),product.getImage(),product.getCategory());
    }

//    @Override
//    public ProductDTO getProductById(String productId, HttpServletRequest request) {
//        Product product = productDAO.getById(productId,request);
//        return new ProductDTO(product.getProductId(),product.getProductName(),product.getProductPrice(),product.getProductQty(),product.getImage(),product.getCategory());
//    }
}
