import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * Digest: 摘要
 * @author User
 *
 */
public class DigestTest {

	/**
	 * 計算一個文件的摘要(MD5)
	 * @throws Exception 
	 */
	@Test
	public void testFileDigest() throws Exception {
		// 文件passwd保存在項目文件夾中
		String file = "passwd";
		// 打開文件
		InputStream in = new FileInputStream(file);
		// 計算文件中數據的MD5摘要
		String md5 = DigestUtils.md5Hex(in);
		in.close();
		// 輸出
		System.out.println(md5);
	}
	
	@Test
	public void testStringDigest() {
		String pwd = "kevin2056";
		// "123" -> UTF-8 -> bytes -> md5計算 -> 摘要
		String md5 = DigestUtils.md5Hex(pwd);
		// 輸出123的摘要
		System.out.println(md5);
		
		String text = "kevin2056";
		String text2 = "Welcome to my online shopping mall!";
		String result = text + text2;
		
		System.out.println(DigestUtils.md5Hex(result));
	}
	
}
