package fr.amani;

import fr.amani.exception.NotAllowedTransactionException;
import fr.amani.exception.NotPositiveAmount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private List<StatementLine> statementLines = new ArrayList<>();


    public Amount getCurrentBalance() {
        if (statementLines.size() == 0) {
            return Amount.ZERO;
        }
        return statementLines.get(statementLines.size() - 1).balance();
    }

    public void deposit(Amount newAmount, LocalDate date) {

        try {
            executeTransaction(newAmount, date, Type.DEPOSIT);
        } catch (NotAllowedTransactionException e) {
            throw new RuntimeException(e);
        }
    }

    public void withdraw(Amount newAmount, LocalDate date) throws NotAllowedTransactionException {
        executeTransaction(newAmount, date, Type.WITHDRAWAL);
    }

    private void executeTransaction(Amount newAmount, LocalDate date, Type type) throws NotAllowedTransactionException {
        Transaction transaction = Transaction.createTransaction(type, newAmount, date);

        try {
            StatementLine statementLine = new StatementLine(transaction, type.calculateNewBalance(getCurrentBalance(), newAmount));
            statementLines.add(statementLine);
        } catch (NotPositiveAmount e) {
            throw new NotAllowedTransactionException();
        }
    }

    public List<StatementLine> getLines() {
        return statementLines;
    }
}
