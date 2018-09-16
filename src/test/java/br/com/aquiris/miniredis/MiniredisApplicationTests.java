package br.com.aquiris.miniredis;

import br.com.aquiris.core.domain.DataModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiniredisApplicationTests {

	@Test
	public void contextLoads() {

		List<DataModel> datas = new ArrayList<DataModel>();

		datas.add(new DataModel(6, null, "a"));
		datas.add(new DataModel(4, null, "a"));
		datas.add(new DataModel(5, null, "a"));
		datas.add(new DataModel(2, null, "a"));
		datas.add(new DataModel(3, null, "a"));
		datas.add(new DataModel(0, null, "a"));
		datas.add(new DataModel(1, null, "a"));

        Collections.sort(datas);
        return;
	}

}
