package ru.itmo.banks.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import ru.itmo.banks.client.Person;

import java.util.Map;

public class BankTest {
    private CentralBank centralBank = CentralBank.getInstance();
    private Bank bank = centralBank.createBank("meme",
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
    public void createBankTest() {
        Assert.assertEquals("meme", bank.getName());
    }

    @Test
    public void replenishmentTest() {
        Person person = new Person("Name", "Surname", "Address", 1000000000);
        bank.createDebitAccount(person, 10000);
        bank.replenishment(person.getAccounts().get(0), 5000);
        Assert.assertEquals(15000.0, person.getAccounts().get(0).getBalance(), 10);
    }

    @Test
    public void withdrawTest() {
        Person person = new Person("Name", "Surname", "Address", 1000000000);
        bank.createDebitAccount(person, 10000);
        bank.withdraw(person.getAccounts().get(0), 5000);
        Assert.assertEquals(5000.0, person.getAccounts().get(0).getBalance(), 10);
    }

}
