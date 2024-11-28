import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.BankAccount;


public class BankAccountTest {
 BankAccount acc;

@BeforeEach
public  void initBankAccount() {
    acc = new BankAccount();
    System.out.println(" Hello ! Before All Tests");
    
}
@Test
public void testdeposit() {
	acc.deposit(100);
	assertEquals(100,acc.getBalance(),"balance should be 100");
}
@Test
public void testInitialBal()
{
assertEquals(0, acc.getBalance());
}
@Test
public void testWithdraw() {
	acc.deposit(100);
	acc.withdraw(50);
	assertEquals(50, acc.getBalance());
}
@Test
public void testWithdrawMoreThanBalance() {

//	acc.withdraw(50);
	 Exception exception = assertThrows(
             Exception.class, () -> {
           	  					    acc.withdraw(50);
             						  });
assertEquals("Insufficient funds.", exception.getMessage());

}
}
