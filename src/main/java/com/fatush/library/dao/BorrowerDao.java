package com.fatush.library.dao;

import com.fatush.library.model.Borrower;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BorrowerDao {

    private static Map<Integer, Borrower> borrowers;

    static {
        borrowers = new HashMap<>() {
            {
                put(1, new Borrower("Henry"));
                put(2, new Borrower("George"));
                put(3, new Borrower("Michael"));
            }
        };
    }

    public Collection<Borrower> getBorrowers() {
        return borrowers.values();
    }

    public void add(Borrower borrower) {
        borrowers.put(borrower.getId(), borrower);
    }

    public void remove(int id) {
        borrowers.remove(id);
    }
}
