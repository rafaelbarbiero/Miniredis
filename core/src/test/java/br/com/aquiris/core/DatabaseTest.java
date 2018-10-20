package br.com.aquiris.core;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class DatabaseTest {

    public static final String MYKEY = "mykey";
    public static final String UM = "um";

    @After
    public void afterSetUp(){
        Database.delCount(MYKEY);
    }

    @Test
    public void set() {
        Database.set(MYKEY, UM);
        Assertions.assertThat(Database.get(MYKEY)).isEqualToIgnoringCase(UM);
    }

    @Test
    public void incrIfNotExistTest(){
        Assertions.assertThat(Integer.parseInt(Database.incr(MYKEY))).isEqualTo(1);
    }

    @Test
    public void incr() {
        Assertions.assertThat(Integer.parseInt(Database.incr(MYKEY))).isEqualTo(1);
    }

    @Test
    public void get() {
    }

    @Test
    public void del() {
        Database.set(MYKEY, UM);
        Assertions.assertThat(Database.delCount(MYKEY)).isEqualTo(1);
    }

    @Test
    public void getDBSIZE() {
        IntStream.rangeClosed(0, 9).boxed()
                .map(String::valueOf)
                .forEach(value -> Database.set(value, value));
        Assertions.assertThat(Database.getDBSIZE()).isEqualTo(10);
    }

    @Test
    public void zadd() {
    }

    @Test
    public void getZCARD() {
    }

    @Test
    public void getZRANGE() {
    }

    @Test
    public void getZRANK() {
    }
}