package tmall.bean;
 
public class Property {
 
    private String name;
    private Category category;
    //id实际指向category的id，外部调用的时候一般命名为cid
    private int id;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
     
}