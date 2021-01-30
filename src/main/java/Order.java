import java.sql.Date;
import java.util.Map;

public class Order {
    /*
    订单是哪个客户的？
    订单包含哪些商品？
     */
    private User user;
    private Product products[];
    private Map<Integer, Integer> productAmmount;//购买数量:商品，个数
    //使用Map结构可以解决这个问题
    private Map<Integer, Float> totalAmountPerProduct;//每个商品的总价
    private float finalPay;//实付款
    private Date orderDate;

    public Map<Integer, Float> getTotalAmountPerProduct() {
        return totalAmountPerProduct;
    }

    public void setTotalAmountPerProduct(Map<Integer, Float> totalAmountPerProduct) {
        this.totalAmountPerProduct = totalAmountPerProduct;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {//订单与用户进行关联
        this.user = user;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Map<Integer, Integer> getProductAmmount() {
        return productAmmount;
    }

    public void setProductAmmount(Map<Integer, Integer> productAmmount) {
        this.productAmmount = productAmmount;
    }

    public float getFinalPay() {
        return finalPay;
    }

    public void setFinalPay(float finalPay) {
        this.finalPay = finalPay;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
