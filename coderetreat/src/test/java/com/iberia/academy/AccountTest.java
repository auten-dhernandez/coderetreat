package com.iberia.academy;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    @Test
    public void whenDepositAmountThenBalanceIsUpdated() {

        Account account = new Account();
        account.deposit(100);

        assertThat(account.balance(), is(100));
        
    }

    @Test(expected = NotEnoughBalanceException.class)
    public void whenWithdrawAmountThenBalanceCannotBeNegative() {
        Account account = new Account();
        account.withdraw(100);
    }

    @Test
    public void whenWithdrawAmountWithPositiveBalanceThenBalanceIsUpdated() {
        Account account = new Account();
        account.deposit(200);

        account.withdraw(100);

        assertThat(account.balance(), is(100));
    }

    @Test
    public void whenStatementPrintedALineMustBePrintedForEachTransactionwithAHeader() {

        Account account = new Account();
        account.deposit(200);
        account.withdraw(50);

        String statement = account.printStatement();

        String[] lines = statement.split("\\n");
        assertThat(lines.length, is(3));
        assertThat(lines[0], is(Account.STATEMENT_HEADER));

        assertTransaction(lines[1], "+200", "200");
        assertTransaction(lines[2], "-50", "150");

    }

    private void assertTransaction(String transaction, String amount, String balance) {
        String[] transactionComponents = transaction.split("\\s");
        assertThat(transactionComponents[1], is(amount));
        assertThat(transactionComponents[2], is(balance));
    }

}
