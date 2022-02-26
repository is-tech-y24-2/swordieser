package ru.itmo.banks.test;

import org.junit.Assert;
import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import org.junit.jupiter.api.Test;
import ru.itmo.banks.client.Person;

import java.util.HashMap;
import java.util.Map;
//import org.testng.Assert;

public class BankTest {
    private CentralBank centralBank = CentralBank.getInstance();
    private Bank bank = centralBank.CreateBank("meme",
            0.15,
            0.1,
            0.1,
            10000,
            10000,
            Map.ofEntries(
                    Map.entry(0.15, 10000.0),
                    Map.entry(0.25, 20000.0)
            ));


    @Test
    public void CreateBankTest(){
        Assert.assertEquals("meme", bank.getName());
    }

    @Test
    public void ReplenishmentTest(){
        Person person = new Person("Name", "Surname", "Address", 1000000000);
        bank.CreateDebitAccount(person, 10000);
        bank.Replenishment(person.GetAccounts().get(0), 5000);
        Assert.assertEquals(15000.0, person.GetAccounts().get(0).getBalance(), 10);
    }

    @Test
    public void WithdrawTest(){
        Person person = new Person("Name", "Surname", "Address", 1000000000);
        bank.CreateDebitAccount(person, 10000);
        bank.Withdraw(person.GetAccounts().get(0), 5000);
        Assert.assertEquals(5000.0, person.GetAccounts().get(0).getBalance(), 10);
    }

}
