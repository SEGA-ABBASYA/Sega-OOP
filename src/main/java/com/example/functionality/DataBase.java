package com.example.functionality;
import com.example.segaoop.*;

import java.util.HashMap;

public class DataBase {
    private static DataBase instance;
    HashMap<String, Person> persons;
    private DataBase() {

    }

    public static DataBase getInstance()
    {
        if(instance == null)
        {
            instance = new DataBase();
        }
        return instance;
    }
}
