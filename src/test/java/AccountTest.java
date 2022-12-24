import fr.amani.*;
import fr.amani.exception.NotAllowedTransactionException;
import fr.amani.exception.NotPositiveAmount;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    @Test
    public void should_deposit_money() throws NotPositiveAmount {

        Account account = new Account();

        //SUT
        account.deposit(Amount.amountOf(500.00), LocalDate.of(2022, 12, 23));

        Transaction transaction = Transaction.createTransaction(Type.DEPOSIT, Amount.amountOf(500.00), LocalDate.of(2022, 12, 23));
        StatementLine statementLine = new StatementLine(transaction, Amount.amountOf(500.00));

        assertThat(account.getCurrentBalance()).isEqualTo(statementLine.balance());
        assertThat(account.getLines().size()).isEqualTo(1);
        assertThat(account.getLines()).containsExactly(statementLine);
    }

    @Test
    public void should_retrieve_money() throws NotAllowedTransactionException, NotPositiveAmount {


        Account account = new Account();
        account.deposit(Amount.amountOf(2000.00), LocalDate.of(2022, 12, 23));

        //SUT
        account.withdraw(Amount.amountOf(1500.00), LocalDate.of(2022, 12, 23));

        Transaction withdrawTransaction = Transaction.createTransaction(Type.WITHDRAWAL, Amount.amountOf(1500.00), LocalDate.of(2022, 12, 23));
        StatementLine withdrawStatementLine = new StatementLine(withdrawTransaction, Amount.amountOf(500.00));

        assertThat(account.getLines().size()).isEqualTo(2);
        assertThat(account.getLines()).contains(withdrawStatementLine);
        assertThat(account.getCurrentBalance()).isEqualTo(withdrawStatementLine.balance());
    }

    @Test
    public void should_throw_exception_when_retrieve_money() {
        Account statement = new Account();

        assertThrows(NotAllowedTransactionException.class, () -> statement.withdraw(Amount.amountOf(500.0), LocalDate.of(2022, 12, 23)));

    }
}
