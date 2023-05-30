package cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;
public class Seat  {

    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean reserved;
    @JsonIgnore

    private UUID token;

    public Seat() {
    }

    ;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
        this.reserved = false;
        this.token = UUID.randomUUID();
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", column=" + column +
                ", price=" + price +
                ", reserved=" + reserved +
                ", token=" + token +
                '}';
    }
}
