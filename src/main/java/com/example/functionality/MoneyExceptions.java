package com.example.functionality;

public class MoneyExceptions extends  Exception{

    public MoneyExceptions(double value, double balance)
    {
        super("You can't do this operation your balance value is less than "+ value);
    }
    public MoneyExceptions(float fees_value)
    {
        super("You can't do this operation your fees amount is less than "+ fees_value);
    }
    public MoneyExceptions(float transaction,double balance)
    {
        super("You can't do this operation your transaction amount is more than your balance which is:"+balance);
    }
    public MoneyExceptions(double transaction)
    {
        super("You can't do this operation your transaction is negative:"+ transaction);
    }

}
