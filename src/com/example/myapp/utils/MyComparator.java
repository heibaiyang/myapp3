package com.example.myapp.utils;

import com.example.myapp.models.Contact;

import java.util.Comparator;

/**
 * Created by 李钊颖 on 2016/4/13.
 */
public class MyComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact c1, Contact c2) {
        return c1.getLetter().compareTo(c2.getLetter());
    }
}
