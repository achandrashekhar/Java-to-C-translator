package cs652.j;

import org.antlr.v4.runtime.misc.Triple;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@RunWith(Parameterized.class)
public abstract class CommonBaseTest {
	static TemporaryFolder tmp = new TemporaryFolder();
	static {
		try {
			tmp.create();
			System.out.println("Working dir: "+getWorkingDir());
		}
		catch (Exception e) {
			System.err.println("Can't create tmp dir");
		}
	}

	public static final String SAMPLES_DIR = "samples";
	public static final String SUFFIX = ".j";

	protected String filename;

	public CommonBaseTest(String filename) {
		this.filename = filename;
	}

	public static String getWorkingDir() {
		return tmp.getRoot().getPath();
	}

	@Parameterized.Parameters(name = "{0}")
	public static Collection<Object[]> findInputFiles() throws IOException {
		return findTestCasesInFolder(SAMPLES_DIR);
	}

	protected static Collection<Object[]> findTestCasesInFolder(String folder) throws IOException {
		URL testFolder = CommonBaseTest.class.getClassLoader().getResource(folder);
		if ( testFolder==null ) {
			System.err.println("Can't find samples directory.");
			return Collections.emptyList();
		}
		Collection<Object[]> result = new ArrayList<>();
		// only feed test methods with .ts source files.
		File dir = new File(testFolder.getPath());
		File[] files = dir.listFiles();
		if ( files!=null ) {
			for (File f : files) {
				if ( f.getName().endsWith(SUFFIX) ) {
					Object[] args = new Object[1];
					args[0] = f.getName();
					result.add(args);
				}
			}
		}
		return result;
	}

	protected Triple<Integer, String, String> exec(String[] cmd, String workingDir) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(Arrays.asList(cmd)).directory(new File(workingDir));
		Process process = pb.start();
		int resultCode = process.waitFor();
		String stdout = dump(process.getInputStream());
		String stderr = dump(process.getErrorStream());
		return new Triple<>(resultCode, stdout, stderr);
	}

	protected String dump(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuilder out = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			out.append(line);
			out.append(System.getProperty("line.separator"));
		}
		return out.toString();
	}

	public static String readFile(String path) throws IOException {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			return new String(encoded);
		} catch (NoSuchFileException nsfe) {
			return "";
		}
	}

	/**
	 * e.g., replaceFileSuffix("foo.ts", ".output")
	 */
	public static String replaceFileSuffix(String s, String suffix) {
		if ( s==null || suffix==null ) return s;
		int dot = s.lastIndexOf('.');
		return s.substring(0, dot)+suffix;
	}

	public static String basename(String s) {
		int dot = s.lastIndexOf('.');
		return s.substring(0, dot);
	}
}