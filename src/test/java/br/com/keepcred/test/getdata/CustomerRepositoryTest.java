package br.com.keepcred.test.getdata;

import java.io.IOException;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import br.com.keepcred.test.getdata.utils.AbstractGetDataTest;

public class CustomerRepositoryTest extends AbstractGetDataTest {



	@Test
	@Rollback(false)
	public void test() throws IOException {
	
	}

	// @Autowired
	// private ReadFileExcel readFile;
	//
	// @Test
	// @Rollback(false)
	// public void writeFile() {
	// try {
	// readFile.read();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
