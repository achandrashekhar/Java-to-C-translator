package cs652.j;

import org.antlr.v4.runtime.misc.Triple;
import org.antlr.v4.runtime.misc.Utils;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class TestCGen extends CommonBaseTest {
	public TestCGen(String filename) {
		super(filename);
	}

	@Test
	public void testCGen() throws Exception {
		checkCGen(filename);
	}

	@Test
	public void testCExec() throws Exception {
		checkCExec(filename);
	}

	public void checkCGen(String filename) throws Exception {
		URL testFolderURL = TestCGen.class.getClassLoader().getResource(SAMPLES_DIR);
		String testFolder = testFolderURL.getPath();
		String workingDir = getWorkingDir();

		String J_pathToFile = testFolder+"/"+filename;
		String C_filename = basename(filename)+".c";

		JTran jTran = new JTran();
		String C_code = jTran.translate(J_pathToFile, C_filename, false, false);

		Utils.writeFile(workingDir+"/"+C_filename, C_code);

		String[] indent_result_cmd = {
			"indent",
			"-bap", "-bad", "-br", "-nce", "-ncs", "-nprs", "-npcs", "-sai", "-saw",
			"-di1", "-brs", "-blf", "--indent-level4", "-nut", "-sob", "-l200",
			C_filename,
			"-o", C_filename // write on top of itself
		};

		// normalize generated code
		exec(indent_result_cmd, workingDir);

		// format the expected file as well
		String expected_C_CodeFilename = testFolder+"/"+C_filename;
		String[] indent_expected_cmd = {
			"indent",
			"-bap", "-bad", "-br", "-nce", "-ncs", "-nprs", "-npcs", "-sai", "-saw",
			"-di1", "-brs", "-blf", "--indent-level4", "-nut", "-sob", "-l200",
			expected_C_CodeFilename,
			"-o", "expected_"+C_filename
		};
		exec(indent_expected_cmd, workingDir);

		// compare with expected c file
		String[] diff_cmd = {
			"diff", "expected_"+C_filename, C_filename
		};
		Triple<Integer, String, String> result = exec(diff_cmd, workingDir);
		int execCode = result.a;
		String stdout = result.b;
		String stderr = result.c;

		assertEquals("", stdout);
		assertEquals("", stderr);
		assertEquals(0, execCode);
	}

	public void checkCExec(String filename) throws Exception {
		URL testFolderURL = TestCGen.class.getClassLoader().getResource(SAMPLES_DIR);
		String testFolder = testFolderURL.getPath();
		String workingDir = getWorkingDir();

		String J_pathToFile = testFolder+"/"+filename;
		String C_filename = basename(filename)+".c";

		JTran jTran = new JTran();
		String C_code = jTran.translate(J_pathToFile, C_filename, false, false);

		Utils.writeFile(workingDir+"/"+C_filename, C_code);

		// compile
		String[] cc = {"cc", "-o", basename(filename), C_filename};
		Triple<Integer, String, String> cc_result = exec(cc, getWorkingDir());
		int execCode = cc_result.a;
		String stdout = cc_result.b;
		String stderr = cc_result.c;

		assertEquals("", stdout);
		assertEquals("", stderr);
		assertEquals(0, execCode);

		// execute
		String[] exec_cmd = {"./"+basename(filename)};
		Triple<Integer, String, String> result = exec(exec_cmd, getWorkingDir());
		execCode = result.a;
		stdout = result.b;
		stderr = result.c;

		String expected_output_filename = basename(filename)+".txt";
		String expected_output = readFile(testFolder+"/"+expected_output_filename);

		assertEquals(expected_output, stdout);
		assertEquals("", stderr);
		assertEquals(0, execCode);
	}
}
