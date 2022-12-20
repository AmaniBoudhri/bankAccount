package fr.amani.exception;

public class NotPositiveAmount extends Exception {
    public NotPositiveAmount() {
        super("Amount cannot be negative");
    }

}
