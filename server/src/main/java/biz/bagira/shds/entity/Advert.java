package biz.bagira.shds.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "adverts")
public class Advert {

    public Advert() {
    }

    public Advert(long id, String name, String text, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.price = price;
    }

    @Id
    private long id;

    @Indexed(name = "index_name",direction = IndexDirection.DESCENDING)
    private String name;

    private String text;

    private BigDecimal price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advert advert = (Advert) o;

        if (id != advert.id) return false;
        if (name != null ? !name.equals(advert.name) : advert.name != null) return false;
        if (text != null ? !text.equals(advert.text) : advert.text != null) return false;
        return price != null ? price.equals(advert.price) : advert.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", price=" + price +
                '}';
    }
}
