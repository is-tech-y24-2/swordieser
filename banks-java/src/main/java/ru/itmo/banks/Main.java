package ru.itmo.banks;

import ru.itmo.banks.account.BaseAccount;
import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import ru.itmo.banks.client.Person;
import ru.itmo.banks.exception.*;
import ru.itmo.banks.transaction.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String interfaceType;
    private static final CentralBank centralBank = CentralBank.getInstance();
    private static final List<Person> clients = new ArrayList<>();

    public static void main(String[] args) throws InvalidTransactionAmountException, WithdrawException, NotEndedDepositAccountException, ReplenishmentException, AlreadyCanceledTransactionException {
        ChooseInterfaceType();
    }

    static void ChooseInterfaceType() throws NotEndedDepositAccountException, WithdrawException, InvalidTransactionAmountException, ReplenishmentException, AlreadyCanceledTransactionException {
        System.out.println("Choose your fighter\n1.manager\n2.client\n3.stop");
        interfaceType = new Scanner(System.in).nextLine();
        InterfaceType();
    }

    static void Manager() {
        while (true) {
            System.out.println("You can choose:" +
                    "\n1. Create bank" +
                    "\n2. Get list of banks" +
                    "\n3. Rewind time" +
                    "\n4. Go to main menu");
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();
            switch (command) {
                case "1":
                case "Create bank":
                    System.out.println("Type parameters of the bank:");
                    System.out.println("Bank name");
                    String name = scan.nextLine();
                    System.out.println("Percent");
                    double percent = Double.parseDouble(scan.nextLine());
                    System.out.println("Commission");
                    double commission = Double.parseDouble(scan.nextLine());
                    System.out.println("Credit limit");
                    double creditLimit = Double.parseDouble(scan.nextLine());
                    System.out.println("Maximal withdraw");
                    double maxWithdraw = Double.parseDouble(scan.nextLine());
                    System.out.println("Maximal transfer");
                    double maxTransfer = Double.parseDouble(scan.nextLine());
                    System.out.println("Percents for deposit account (amount, percent, upper bound)");
                    int amount = Integer.parseInt(scan.nextLine());
                    var borders = new HashMap<Double, Double>();
                    for (int i = 0; i < amount; i++) {
                        double depositPercent = Double.parseDouble(scan.nextLine());
                        double upperBound = Double.parseDouble(scan.nextLine());
                        borders.put(depositPercent, upperBound);
                    }

                    centralBank.CreateBank(
                            name,
                            percent,
                            commission,
                            creditLimit,
                            maxWithdraw,
                            maxTransfer,
                            borders);
                    break;
                case "2":
                case "Get list of banks":
                    for (Bank bank : centralBank.GetBanks()) {
                        System.out.printf("%d - %s\n", bank.getId(), bank.getName());
                    }

                    break;
                case "3":
                case "Rewind time":
                    System.out.println("Type date to rewind (year, month, day");
                    var date = LocalDate.of(
                            Integer.parseInt(scan.nextLine()),
                            Integer.parseInt(scan.nextLine()),
                            Integer.parseInt(scan.nextLine()));
                    TimeMachine.TimeRewind(centralBank, date);
                    break;
                case "4":
                case "Go to main menu":
                    ChooseInterfaceType();
                    break;
                default:
                    System.out.println("Try again");
            }
        }
    }

    static void Client() {
        while (true) {
            System.out.println("You can choose:" +
                    "\n1. Register" +
                    "\n2. Create account" +
                    "\n3. Get list of accounts" +
                    "\n4. Balance" +
                    "\n5. Withdraw" +
                    "\n6. Replenishment" +
                    "\n7. Transfer" +
                    "\n8. Transaction history" +
                    "\n9. Cancel transaction" +
                    "\n10. Go to main menu");
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();
            switch (command) {
                case "1":
                case "Register":
                    System.out.println("Type your name and surname");
                    String personName = scan.nextLine();
                    String personSurname = scan.nextLine();
                    System.out.println("Type your address to verify your account");
                    String personAddress = scan.nextLine();
                    System.out.println("Type your passport to end verification");
                    long passport = Integer.parseInt(scan.nextLine());
                    var person = new Person(personName, personSurname, personAddress, passport);
                    person.CheckDoubtfulness();
                    clients.add(person);
                    break;
                case "2":
                case "Create account":
                    System.out.println("Choose the bank by number:");
                    for (Bank bank : centralBank.GetBanks()) {
                        System.out.printf("%d - %s\n", bank.getId(), bank.getName());
                    }

                    int bankId = Integer.parseInt(scan.nextLine());
                    for (Bank bank : centralBank.GetBanks()) {
                        if (bank.getId() == bankId) {
                            System.out.println("Type your full name");
                            String name = scan.nextLine();
                            var tempClients = new ArrayList<Person>();
                            for (Person person1 : clients) {
                                if (String.format("%s %s", person1.getName(), person1.getSurname()).equals(name)) {
                                    tempClients.add(person1);
                                }
                            }
                            for (Person client : tempClients) {
                                System.out.println("Choose type of account:\nDebit\nCredit\nDeposit");
                                String type = scan.nextLine();
                                switch (type) {
                                    case "Debit":
                                        bank.CreateDebitAccount(client, 0);
                                        break;
                                    case "Credit":
                                        bank.CreateCreditAccount(client, 0);
                                        break;
                                    case "Deposit":
                                        System.out.println("Type amount of deposit");
                                        int deposit = Integer.parseInt(scan.nextLine());
                                        System.out.println("Type the end of deposit (year, month, day");
                                        int year = Integer.parseInt(scan.nextLine());
                                        int month = Integer.parseInt(scan.nextLine());
                                        int day = Integer.parseInt(scan.nextLine());
                                        bank.CreateDepositAccount(
                                                client,
                                                deposit,
                                                LocalDate.of(year, month, day));
                                        break;
                                }
                            }
                        }
                    }

                    break;
                case "3":
                case "Get list of accounts":
                    System.out.println("Type your full name");
                    String name = scan.nextLine();
                    var tempClients = new ArrayList<Person>();
                    for (Person person1 : clients) {
                        if (String.format("%s %s", person1.getName(), person1.getSurname()).equals(name)) {
                            tempClients.add(person1);
                        }
                    }
                    for (Person client : tempClients) {
                        for (BaseAccount account : client.GetAccounts()) {
                            System.out.printf("%d:\nBalance: %f", account.getId(), account.getBalance());
                        }
                    }

                    break;
                case "4":
                case "Balance":
                    System.out.println("Type your full name and then type your account id");
                    String accName = scan.nextLine();
                    var tempClients1 = new ArrayList<Person>();
                    for (Person person1 : clients) {
                        if (String.format("%s %s", person1.getName(), person1.getSurname()).equals(accName)) {
                            tempClients1.add(person1);
                        }
                    }
                    int accId = Integer.parseInt(scan.nextLine());
                    for (Person client : tempClients1) {
                        for (BaseAccount account : client.GetAccounts()) {
                            if (account.getId() == accId) {
                                System.out.printf("Balance: %f", account.getBalance());
                            }
                        }
                    }

                    break;
                case "5":
                case "Withdraw":
                    System.out.println("Type your full name and then type your account id");
                    String withName = scan.nextLine();
                    int withId = Integer.parseInt(scan.nextLine());
                    var tempClients2 = new ArrayList<Person>();
                    for (Person person1 : clients) {
                        if (String.format("%s %s", person1.getName(), person1.getSurname()).equals(withName)) {
                            tempClients2.add(person1);
                        }
                    }
                    for (Person client : tempClients2) {
                        for (BaseAccount account : client.GetAccounts()) {
                            if (account.getId() == withId) {
                                for (Bank bank : centralBank.GetBanks()) {
                                    if (bank.getAccounts().contains(account)) {
                                        System.out.println("Type amount of withdraw");
                                        int withdraw = Integer.parseInt(scan.nextLine());
                                        bank.Withdraw(account, withdraw);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    break;
                case "6":
                case "Replenishment":
                    System.out.println("Type your full name and then type your account id");
                    String repName = scan.nextLine();
                    int repId = Integer.parseInt(scan.nextLine());
                    var tempClients3 = new ArrayList<Person>();
                    for (Person person1 : clients) {
                        if (String.format("%s %s", person1.getName(), person1.getSurname()).equals(repName)) {
                            tempClients3.add(person1);
                        }
                    }
                    for (Person client : tempClients3) {
                        for (BaseAccount account : client.GetAccounts()) {
                            if (account.getId() == repId) {
                                for (Bank bank : centralBank.GetBanks()) {
                                    if (bank.getAccounts().contains(account)) {
                                        System.out.println("Type amount of withdraw");
                                        int replenishment = Integer.parseInt(scan.nextLine());
                                        bank.Replenishment(account, replenishment);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    break;
                case "7":
                case "Transfer":
                    System.out.println("Type your full name and then type your account id");
                    String transferName = scan.nextLine();
                    int transferId = Integer.parseInt(scan.nextLine());
                    var tempClients4 = new ArrayList<Person>();
                    for (Person person1 : clients) {
                        if (String.format("%s %s", person1.getName(), person1.getSurname()).equals(transferName)) {
                            tempClients4.add(person1);
                        }
                    }
                    for (Person client : tempClients4) {
                        for (BaseAccount account : client.GetAccounts()) {
                            if (account.getId() == transferId) {
                                System.out.println("Type full name and account id of the recipient");
                                String recipName = scan.nextLine();
                                int recipId = Integer.parseInt(scan.nextLine());
                                var tempClients5 = new ArrayList<Person>();
                                for (Person person1 : clients) {
                                    if (String.format("%s %s", person1.getName(), person1.getSurname()).equals(recipName)) {
                                        tempClients5.add(person1);
                                    }
                                }
                                for (Person client1 : tempClients5) {
                                    for (BaseAccount account1 : client1.GetAccounts()) {
                                        if (account1.getId() == recipId) {
                                            for (Bank bank : centralBank.GetBanks()) {
                                                if (bank.getAccounts().contains(account)) {
                                                    System.out.println("Type the amount of transfer");
                                                    double amount = Double.parseDouble(scan.nextLine());
                                                    bank.Transfer(account, account1, amount);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;
                case "8":
                case "Transaction history":
                    System.out.println("Type your full name and then type your account id");
                    String transName = scan.nextLine();
                    int transId = Integer.parseInt(scan.nextLine());
                    var tempClients6 = new ArrayList<Person>();
                    for (Person person1 : clients) {
                        if (String.format("%s %s", person1.getName(), person1.getSurname()).equals(transName)) {
                            tempClients6.add(person1);
                        }
                    }
                    for (Person client : tempClients6) {
                        for (BaseAccount account : client.GetAccounts()) {
                            if (account.getId() == transId) {
                                for (Transaction transaction : account.GetTransactionsHistory()) {
                                    System.out.printf("Transaction id: %d", transaction.getId());
                                    if (transaction.getSender() != null) {
                                        System.out.printf("Transaction sender: %d", transaction.getSender().getId());
                                    }

                                    if (transaction.getRecipient() != null) {
                                        System.out.printf("Transaction recipient: %d", transaction.getRecipient().getId());
                                    }

                                    System.out.printf("Transaction amount: %f", transaction.getAmount());
                                }
                            }
                        }
                    }

                    break;
                case "9":
                case "Cancel transaction":
                    System.out.println("Type your full name and then type your account id");
                    String cancelName = scan.nextLine();
                    int cancelId = Integer.parseInt(scan.nextLine());
                    var tempClients7 = new ArrayList<Person>();
                    for (Person person1 : clients) {
                        if (String.format("%s %s", person1.getName(), person1.getSurname()).equals(cancelName)) {
                            tempClients7.add(person1);
                        }
                    }
                    for (Person client : tempClients7) {
                        for (BaseAccount account : client.GetAccounts()) {
                            if (account.getId() == cancelId) {
                                System.out.println("Type the id of the transaction");
                                int transCancelId = Integer.parseInt(scan.nextLine());
                                for (Transaction transaction : account.GetTransactionsHistory()) {
                                    if (transaction.getId() == transCancelId) {
                                        for (Bank bank : centralBank.GetBanks()) {
                                            if (bank.getAccounts().contains(account)) {
                                                bank.Cancellation(account, transaction);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;
                case "10":
                case "Go to main menu":
                    ChooseInterfaceType();
                    break;
                default:
                    System.out.println("Try again");
            }
        }
    }

    static void InterfaceType() {
        switch (interfaceType) {
            case "1":
            case "manager":
                Manager();
                break;
            case "2":
            case "client":
                Client();
                break;
            case "3":
            case "stop":
                return;
            default:
                System.out.println("Try again");
                ChooseInterfaceType();
                break;
        }
    }
}
