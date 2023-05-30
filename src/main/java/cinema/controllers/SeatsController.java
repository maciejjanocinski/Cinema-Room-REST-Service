package cinema.controllers;

import cinema.entity.Room;
import cinema.entity.Seat;
import cinema.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class SeatsController {

    RoomService roomService;

    public SeatsController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/seats")
    public Room getSeats() {
        return roomService.getSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeat(@RequestBody Seat seat)  {
        return roomService.purchaseSeat(seat);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody String token) {
        return roomService.returnTicket(token);
    }

    @PostMapping("/stats")
    public ResponseEntity<Object> showStats(@RequestParam(value ="password", required = false) Optional<String> password){
        return roomService.showStats(password);
    }


}




















