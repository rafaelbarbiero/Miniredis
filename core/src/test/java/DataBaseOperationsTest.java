import br.com.aquiris.core.Database;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DataBaseOperationsTest {

    @Test
    public void setTest(){
        Database.set("mykey", "um");
        Assertions.assertThat(Database.get("mykey")).isEqualToIgnoringCase("um");
    }

    @Test
    public void incrIfNotExistTest(){
        Assertions.assertThat(Integer.parseInt(Database.incr("mykey"))).isEqualTo(1);
    }

    @Test
    public void incrTest(){
        Database.set("mykey", "1");
        Assertions.assertThat(Integer.parseInt(Database.incr("mykey"))).isEqualTo(2);
    }

    @Test
    public void delTest(){
        Database.set("mykey", "um");
        Assertions.assertThat(Database.del("mykey")).isEqualTo("um");
    }

    @Test
    public void dbSizeTest(){
        IntStream.rangeClosed(0, 9).boxed()
                .map(String::valueOf)
                .forEach(value -> Database.set(value, value));
        Assertions.assertThat(Database.getDBSIZE()).isEqualTo(10);
    }

    @Test
    public void zaddTest(){
        //TODO
    }
}
