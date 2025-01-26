package lk.ijse.assignment_javaee.bo;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.dto.ProductDTO;
import lk.ijse.assignment_javaee.entity.Product;

import java.util.List;

public interface ProductBO extends SuperBO{

    public boolean saveProduct(ProductDTO productDTO, HttpServletRequest request);

    public String getCurrentProductId(HttpServletRequest request);
    public List<ProductDTO> getProducts(HttpServletRequest request);
    public ProductDTO getProductByName(String productName,HttpServletRequest request);

    public boolean updateProduct(ProductDTO productDTO, HttpServletRequest request);
    public boolean deleteProduct(String productId, HttpServletRequest request);
//    public ProductDTO getProductById(String productId, HttpServletRequest request);

    public List<ProductDTO> getMenProducts(HttpServletRequest request);

    public List<ProductDTO> getWomenProducts(HttpServletRequest request);

    public List<ProductDTO> getKidsProducts(HttpServletRequest request);

    public ProductDTO getProductById(String productId,HttpServletRequest request);
}
