import java.sql.Date;

public class Order {
    /*
    订单是哪个客户的？
    订单包含哪些商品？
     */
    private User user;
    private Product products[];
    private int productAmmount;//购买数量
    private float totalPrice;
    private float finalPay;
    private Date orderDate;

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

    public int getProductAmmount() {
        return productAmmount;
    }

    public void setProductAmmount(int productAmmount) {
        this.productAmmount = productAmmount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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