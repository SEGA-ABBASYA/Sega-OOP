package com.example.functionality;
import com.example.segaoop.*;

import java.io.Serializable;
import java.util.*;

public abstract class Person implements Serializable
{
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String telephone;
    protected String address;
    public abstract void showContent();
}
