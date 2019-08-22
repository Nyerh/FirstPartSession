package Entity;

public class Goods {
    private Integer id;
    private String name;
    private String pic;
    private String price;
    private String description;
    private String stock;
    private Integer is_delete;

    public Goods() {
    }

    public Goods(String name, String pic, String price, String description, String stock) {
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", stock='" + stock + '\'' +
                ", is_delete=" + is_delete +
                '}';
    }

    public Goods(Integer id, String name, String pic, String price, String description, String stock, Integer is_delete) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.is_delete = is_delete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }
}
