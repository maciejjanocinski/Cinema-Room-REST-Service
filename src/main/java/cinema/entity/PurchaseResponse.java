package cinema.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class PurchaseResponse {

    private UUID token;
    @JsonProperty("ticket")
    private Seat ticket;

    public PurchaseResponse() {
    }

    ;

    public PurchaseResponse(Seat ticket, UUID token) {
        this.ticket = ticket;
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @JsonProperty("ticket")
    public Seat getSeat() {
        return ticket;
    }

    public void setSeat(Seat ticket) {
        this.ticket = ticket;
    }
}
