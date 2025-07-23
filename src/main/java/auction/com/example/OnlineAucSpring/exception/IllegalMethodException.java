package auction.com.example.OnlineAucSpring.exception;

import java.time.LocalDateTime;

public class IllegalMethodException extends RuntimeException {
        private String message;
        private int status;
        private LocalDateTime timestamp;
        private double amount;

    public IllegalMethodException() {
    }

    public IllegalMethodException(String message, int status, LocalDateTime timestamp) {
            super(String.format("%s not found with %s : %s",message,status,timestamp));
            this.message = message;
            this.status = status;
            this.timestamp = timestamp;
    }

    public IllegalMethodException(String message, int status, double amount) {
        super(String.format("%s not found with %s : %s",message,status,amount));
        this.message = message;
        this.status = status;
        this.amount = amount;
    }

        public String getMessage() { return message; }
        public int getStatus() { return status; }
        public LocalDateTime getTimestamp() { return timestamp; }
}
