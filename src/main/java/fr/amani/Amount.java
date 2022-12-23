package fr.amani;

import fr.amani.exception.NotPositiveAmount;
import lombok.Value;

@Value
public class Amount {
    Double value;

    private Amount(Double value) {
        this.value = value;
    }

    public static Amount ZERO = new Amount(0.0);

    public static Amount amountOf(Double value) throws NotPositiveAmount {
        if (value <= 0.0) {
            throw new NotPositiveAmount();
        }
        return new Amount(value);
    }

    public Amount addTo(Amount newAmount) {
        try {
            return amountOf(this.value + newAmount.value);
        } catch (NotPositiveAmount e) {
            throw new RuntimeException(e);
        }
    }

    public Amount retrieve(Amount newAmount) throws NotPositiveAmount {
        return amountOf(this.value - newAmount.value);
    }

}
