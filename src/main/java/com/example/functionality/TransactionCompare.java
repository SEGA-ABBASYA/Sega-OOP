package com.example.functionality;

import java.util.Comparator;

public class TransactionCompare implements Comparator<Transaction> {

    @Override
    public int compare(Transaction o1, Transaction o2) {
        if (o1.transactionDate.compareTo(o2.transactionDate) > 0)
        {
            return -1;
        }
        else if (o1.transactionDate.compareTo(o2.transactionDate) < 0)
        {
            return 1;
        }
        else {return 0;}

    }
}
