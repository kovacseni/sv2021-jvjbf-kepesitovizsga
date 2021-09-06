package training360.guinessapp;

public class WorldRecordNotFoundException extends RuntimeException {

    public WorldRecordNotFoundException() {
    }

    public WorldRecordNotFoundException(String message) {
        super(message);
    }

    public WorldRecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
