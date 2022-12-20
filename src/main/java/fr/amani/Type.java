package fr.amani;

import fr.amani.exception.NotPositiveAmount;

public enum Type {
    DEPOSIT(Amount::addTo), WITHDRAWAL(Amount::retrieve);

    private CalculateAmount calculate;

    Type(CalculateAmount calculate) {

        this.calculate = calculate;
    }

    public Amount calculateNewBalance(Amount currentAmount, Amount newAmount) throws NotPositiveAmount {
        return calculate.compute(currentAmount, newAmount);
    }
}

interface CalculateAmount {
    Amount compute(Amount currentAmount, Amount newAmount) throws NotPositiveAmount;
}
