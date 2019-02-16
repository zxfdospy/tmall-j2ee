package tmall.bean;
 
import java.util.Date;
 
import java.util.List;
 
public class Product {
    private String name;
    private String subTitle;
    private long originalPrice;
    private long promotePrice;
    private int stock;
    private Date createDate;
    private Category category;
    private int id;
    private ProductImage firstProductImage;
    private List<ProductImage> productSingleImages;
    private List<ProductImage> productDetailImages;
    private int reviewCount;
    private int saleCount;
 
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSubTitle() {
        return subTitle;
    }
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    public long getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(long originalPrice) {
        this.originalPrice = originalPrice;
    }
    public long getPromotePrice() {
        return promotePrice;
    }
    public void setPromotePrice(long promotePrice) {
        this.promotePrice = promotePrice;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
     
    public String toString(){
        return name;
    }
    public ProductImage getFirstProductImage() {
        return firstProductImage;
    }
    public void setFirstProductImage(ProductImage firstProductImage) {
        this.firstProductImage = firstProductImage;
    }

    public List<ProductImage> getProductSingleImages() {
        return productSingleImages;
    }
    public void setProductSingleImages(List<ProductImage> productSingleImages) {
        this.productSingleImages = productSingleImages;
    }
    public List<ProductImage> getProductDetailImages() {
        return productDetailImages;
    }
    public void setProductDetailImages(List<ProductImage> productDetailImages) {
        this.productDetailImages = productDetailImages;
    }
    public int getReviewCount() {
        return reviewCount;
    }
    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
    public int getSaleCount() {
        return saleCount;
    }
    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }
     
}