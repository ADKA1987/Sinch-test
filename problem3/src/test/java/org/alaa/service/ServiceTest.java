package org.alaa.service;

import org.alaa.domain.PolishNotationDomain;
import org.alaa.domain.RequestSystem;
import org.alaa.domain.RequestUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest
{
    @Autowired
    Service service;

    private RequestSystem requestSystem;
    private RequestUser requestUser;
    private Request request;
    private PolishNotationDomain polishNotationDomain;
    @Before
    public void init(){
        requestSystem = new RequestSystem("Junit");
        requestUser = new RequestUser("Test");

    }

    @Test
    public void test1(){

        polishNotationDomain = new PolishNotationDomain("+ + 0.5 1.5 * 4 10");
        var response = service.getPolishNotion("testTransaction",requestUser,requestSystem,polishNotationDomain);
        Assert.assertTrue(Objects.requireNonNull(response.block()).isRight());
    }
    @Test
    public void test2(){

        polishNotationDomain = new PolishNotationDomain("- 2e3 - 700 + 7 * 2 15");
        var response = service.getPolishNotion("testTransaction",requestUser,requestSystem,polishNotationDomain);
        Assert.assertTrue(Objects.requireNonNull(response.block()).isRight());
    }
    @Test
    public void test3(){

        polishNotationDomain = new PolishNotationDomain("- -1.5 * 3.1415 / -7 -2");
        var response = service.getPolishNotion("testTransaction",requestUser,requestSystem,polishNotationDomain);
        Assert.assertTrue(Objects.requireNonNull(response.block()).isRight());
    }
    @Test
    public void test4(){

        polishNotationDomain = new PolishNotationDomain("100500");
        var response = service.getPolishNotion("testTransaction",requestUser,requestSystem,polishNotationDomain);
        Assert.assertTrue(Objects.requireNonNull(response.block()).isRight());
    }
    @Test
    public void test5(){

        polishNotationDomain = new PolishNotationDomain("1 2");
        var response = service.getPolishNotion("testTransaction",requestUser,requestSystem,polishNotationDomain);
        Assert.assertTrue(Objects.requireNonNull(response.block()).isLeft());
    }
    @Test
    public void test6(){

        polishNotationDomain = new PolishNotationDomain("+ 1");
        var response = service.getPolishNotion("testTransaction",requestUser,requestSystem,polishNotationDomain);
        Assert.assertTrue(Objects.requireNonNull(response.block()).isLeft());
    }
}
