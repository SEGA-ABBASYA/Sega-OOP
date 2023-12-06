package com.example.functionality;

public class MoneyExceptions extends  Exception{

    public MoneyExceptions(double value)
    {
        super("You can't do this operation your balance value is less than "+ value);
    }
    public MoneyExceptions(float fees_value)
    {
        super("You can't do this operation your fees amount is less than "+ fees_value);

    }

}
