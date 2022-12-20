package fr.amani.exception;

public class NotAllowedTransactionException extends Exception {
    public NotAllowedTransactionException() {
        super("Transaction not allowed");
    }

}
