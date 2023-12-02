package com.example.functionality;
import com.example.segaoop.*;

import java.util.HashMap;

public class DataBase {
    private static DataBase instance;
    private DataBase() {
    }

    HashMap<String, Person> persons;

    public static DataBase getInstance()
    {
        if(instance == null)
        {
            instance = new DataBase();
        }
        return instance;
    }

}
