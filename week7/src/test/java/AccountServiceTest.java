import org.example.domain.Account;
import org.example.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class AccountServiceTest {
    private AccountService accountService;
    @Before
    public void init() throws IOException {
        accountService = new AccountService();
    }
    @Test
    public void testFindAll(){
        List<Account> accounts = accountService.findAll();
        System.out.println(accounts);
    }
    @Test
    public void testInsert(){
        Calendar createTime = Calendar.getInstance();
        Calendar updateTime = Calendar.getInstance();
        createTime.set(Calendar.YEAR,2023);
        createTime.set(Calendar.MONTH,Calendar.JULY);
        createTime.set(Calendar.DAY_OF_MONTH,21);
        updateTime.set(Calendar.YEAR,2008);
        updateTime.set(Calendar.MONTH,Calendar.FEBRUARY);
        updateTime.set(Calendar.DAY_OF_MONTH,22);
//        Account record = new Account("1", "Alice", 1000, createTime.getTime(), updateTime.getTime());
        Account record = new Account("3", "Bob", 2000, createTime.getTime(), updateTime.getTime());
        accountService.insert(record);
    }
    @Test
    public void testSelectByPrimaryKey(){
        Account account = accountService.selectByPrimaryKey("2");
        System.out.println(account);
    }
    @Test
    public void testUpdateByPrimaryKey(){
        Account record = new Account("2", null, 5000, null,null);
        accountService.updateByPrimaryKey(record);
    }
    @Test
    public void testDeleteByPrimaryKey(){
        String id = "3";
        accountService.deleteByPrimaryKey(id);
    }
    @Test
    public void testTransfer(){
        accountService.transfer("2","1",1000);
    }
}
