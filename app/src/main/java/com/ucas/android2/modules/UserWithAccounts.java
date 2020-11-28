package com.ucas.android2.modules;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithAccounts {
    @Embedded
    private User user;
    @Relation(parentColumn = "userId", entityColumn = "userId", entity = Account.class)
    private List<Account> accountsList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Account> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(List<Account> accountsList) {
        this.accountsList = accountsList;
    }
}