package cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Room {

    private int total_rows;
    private int total_columns;
    private List<Seat> available_seats = new ArrayList<>();

    public Room() {
    }

    ;

    public Room(int rows, int colums) {
        total_rows = rows;
        total_columns = colums;

        for (int i = 1; i < this.getTotal_columns() + 1; i++) {
            for (int j = 1; j < this.getTotal_rows() + 1; j++) {
                Seat seat = new Seat(i, j);
                this.addSeat(seat);
            }
        }
    }


    public Optional<Seat> getTicketByToken(String token) {
        List<Seat> tickets = this.getAvailable_seats();
        Optional<Seat> ticket = tickets.stream().filter(t -> token.contains(t.getToken().toString())).findFirst();
        return ticket;
    }

    @JsonIgnore
    public int getNrOfPurchasedSeats() {
        List<Seat> purchased = this.getAvailable_seats().stream().filter(Seat::isReserved).toList();
        return purchased.size();
    }

    @JsonIgnore
    public int getNrOfAvailableSeats() {
        List<Seat> available = this.getAvailable_seats().stream().filter(seat -> !seat.isReserved()).toList();
        return available.size();
    }


    public void addSeat(Seat seat) {
        available_seats.add(seat);
    }

    public void deleteSeat(Seat seat) {
        available_seats.remove(seat);
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }
}
