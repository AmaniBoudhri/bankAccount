package fr.amani;

import fr.amani.exception.NotPositiveAmount;
import lombok.SneakyThrows;
import lombok.Value;

import java.time.LocalDate;

@Value
public class Transaction {
    Type type;
    Amount amount;
    LocalDate date;

    private Transaction(Type type, Amount amount, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    @SneakyThrows
    public static Transaction createTransaction(Type type, Amount amount, LocalDate date) {
        if (amount.getValue() < 0.0) {
            throw new NotPositiveAmount();
        }
        return new Transaction(type, amount, date);
    }
}
