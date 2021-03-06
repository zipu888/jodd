// Copyright (c) 2003-2013, Jodd Team (jodd.org). All Rights Reserved.

package jodd.io;

import jodd.util.ClassLoaderUtil;
import jodd.util.StringUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

public class StreamUtilTest {

	protected String dataRoot;

	@Before
	public void setUp() throws Exception {
		if (dataRoot != null) {
			return;
		}
		URL data = ClassLoaderUtil.getResourceUrl("jodd/io/data");
		dataRoot = data.getFile();
	}

	@Test
	public void testCopy() {
		AsciiInputStream in = new AsciiInputStream("input");
		StringOutputStream out = new StringOutputStream();
		try {
			StreamUtil.copy(in, out);
		} catch (IOException ioex) {
			fail("StreamUtil.copy " + ioex.toString());
		}
		assertEquals("input", out.toString());
		StreamUtil.close(out);
		StreamUtil.close(in);
	}

	@Test
	public void testCompare() {
		try {
			File file = new File(dataRoot, "file/a.txt");
			FileInputStream in1 = new FileInputStream(file);

			String content = "test file\r\n";
			if (file.length() == 10) {
				content = StringUtil.remove(content, '\r');
			}
			AsciiInputStream in2 = new AsciiInputStream(content);
			assertTrue(StreamUtil.compare(in1, in2));
			StreamUtil.close(in2);
			StreamUtil.close(in1);
		} catch (FileNotFoundException e) {
			fail("StreamUtil.testCloneCompare " + e.toString());
		} catch (IOException e) {
			fail("StreamUtil.testCloneCompare " + e.toString());
		}
	}

	@Test
	public void testGetBytes() {
		try {
			FileInputStream in = new FileInputStream(new File(dataRoot, "file/a.txt"));
			byte[] data = StreamUtil.readBytes(in);
			StreamUtil.close(in);

			String s = new String(data);
			s = StringUtil.remove(s, '\r');
			assertEquals("test file\n", s);

			in = new FileInputStream(new File(dataRoot, "file/a.txt"));
			String str = new String(StreamUtil.readChars(in));
			StreamUtil.close(in);
			str = StringUtil.remove(str, '\r');
			assertEquals("test file\n", str);
		} catch (FileNotFoundException e) {
			fail("StreamUtil.testGetBytes " + e.toString());
		} catch (IOException e) {
			fail("StreamUtil.testGetBytes " + e.toString());
		}
	}
}
