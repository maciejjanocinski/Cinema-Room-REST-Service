package cinema.service;

import cinema.entity.PurchaseResponse;
import cinema.entity.Room;
import cinema.entity.Seat;
import cinema.entity.Stats;
import cinema.exceptionHandling.CinemaException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class RoomService {
    Room room;
    @JsonIgnore
    Stats stats;

    public RoomService(Room room, Stats stats) {
        this.room = room;
        this.stats = stats;
    }

    public Room getSeats() {
        room = new Room(9, 9);

        return room;
    }

    public ResponseEntity<Object> purchaseSeat(@RequestBody Seat ticket) {


        if (ticket.getColumn() > room.getTotal_columns() || ticket.getRow() > room.getTotal_rows()
                || ticket.getColumn() < 1 || ticket.getRow() < 1) {
            throw new CinemaException("The number of a row or a column is out of bounds!");
        }

        List<Seat> availableSeats = room.getAvailable_seats().stream()
                .filter(element -> !element.isReserved())
                .toList();


        for (Seat element : availableSeats
        ) {
            if (element.getRow() == ticket.getRow() && element.getColumn() == ticket.getColumn()) {
                element.setReserved(true);
                stats.addIncome(element.getPrice());
                return new ResponseEntity<>(new PurchaseResponse(element, element.getToken()), HttpStatus.OK);
            }
        }


        throw new CinemaException("The ticket has been already purchased!");

    }

    public ResponseEntity<Object> returnTicket(String token) {

        Optional<Seat> seatOptional = room.getTicketByToken(token);

            if(seatOptional.isPresent()){
                Seat seat = seatOptional.get();
                seat.setReserved(false);
                stats.substractIncome(seat.getPrice());
                return new ResponseEntity<>(Map.of("returned_ticket", seat), HttpStatus.OK);
            } else {
               throw new CinemaException("Wrong token!");
            }

    }

    public ResponseEntity<Object> showStats(Optional<String> password) {

        if(password.isEmpty()){
            return new ResponseEntity<>(new Error("The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }


        if(password.get().equals("super_secret")){
            stats.setNumberOfAvailableSeats(room.getNrOfAvailableSeats());
            stats.setNumberOfPurchasedTickets(room.getNrOfPurchasedSeats());
            return new ResponseEntity<>(stats, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Error("The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }

    }
}
