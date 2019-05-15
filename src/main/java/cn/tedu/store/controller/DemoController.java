package cn.tedu.store.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.ResponseResult;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

	/**
	 * DemoController 中添加處理圖片下載方法
	 * produces 用於設定content-t
	 * @ResponseBody 與返回值byte[]配合, 填充
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/demo.do", produces="image/png")
	@ResponseBody
	public byte[] pngDemo() throws IOException {
		// 讀取一個png圖片, 返回圖片數據即可
		String path = "cn/tedu/store/controller/matrix.png";
		// 從包裏讀取文件
		InputStream in = getClass().getClassLoader().getResourceAsStream(path);
		// avaliable()方法可以檢查留一次可以讀取多少文字, 小文件就是文件長度
		byte[] bytes = new byte[in.available()];
		in.read(bytes);
		System.out.println("content-length: " + bytes.length);
		in.close();
		return bytes;
	}
	
	/**
	 * 圖片下載
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/download.do", produces="image/png")
	@ResponseBody
	public byte[] downloadPng(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Disposition", "attachment; filename=\"Hello.png\"");
		byte[] bytes = createPng("Hello");
		return bytes;
	}
	
	/**
	 * Excel 導出
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/export.do", produces="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@ResponseBody
	public byte[] exportExcel(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Disposition", "attachment; filename=\"Demo.xlsx\"");
		byte[] bytes = createExcel();
		return bytes;
		
	}
	
	/**
	 * 上載服務器端處理關鍵點
	 * 1. 導入上載處理組件 commons-fileupload 用於解析文件上載請求
	 * 2. 配置Spring MVC上載解析器, 使用fileupload組件
	 * 	- 設置最大上載限制
	 * 	- 設置文件名的中文編碼
	 * 3. 控制器中使用MultipartFile接受上載的文件數據
	 * 	- 注意: 變量名與客戶端name屬性一致
	 * 	- 文件的全部訊息可以通過MultipartFile對象獲得
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	
	@RequestMapping("/upload.do")
	@ResponseBody
	public ResponseResult<Void> upload(MultipartFile image, String memo, HttpServletRequest request) throws IllegalStateException, IOException {
		// MultipartFile 封裝了全部的上載文件信息
		// 利用其方法可以獲取全部的上載訊息
		// 顯示上載文件的文件名OriginalFilename
		// 文件大小(字節數)image.getSize();
		// 返回name屬性的名 image.getName();
		// 返回文件的全部字節 image.getBytes();
		// 返回流, 其中包含文件的全部字節 image.getInputStream();
		// 返回文件的類型 image.getContentType()
		// 將上載的文件直接保存到一個目標文件中 image.trasferTo(file)
		
		String fileName = image.getOriginalFilename();
		// String path = "D:/images";
		String path = "/uploads"; // WEB路徑
		
		// 將WEB路徑轉換為當前操作系統的實際路徑
		path = request.getServletContext().getRealPath(path);
		// 輸出實際的路徑
		System.out.println(path);
		
		File dir = new File(path);
		dir.mkdir();
		File file = new File(path, fileName);
		image.transferTo(file);
		
		System.out.println("文件的input name屬性名稱: " + image.getName());
		System.out.println("文件的大小" + image.getSize());
		System.out.println("文件原始名: " + image.getOriginalFilename());
		System.out.println("文件說明內容: " + memo);
		
		// 返回結果
		return new ResponseResult<Void>(1, "Success");
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/upload-images.do")
	@ResponseBody
	public ResponseResult<Void> uploadImages(
			@RequestParam(value="images") MultipartFile[] images, String memo, HttpServletRequest request) throws IOException {
		System.out.println(images.length);
		System.out.println(memo);
		
		// 保存文件
		if(images == null || images.length == 0) {
			System.out.println("沒有上載文件");
			return new ResponseResult<Void>(0, "沒有上載文件");
		}
		
		String path = "/uploads";
		path = request.getServletContext().getRealPath(path);
		File dir = new File(path);
		dir.mkdir();
		for(MultipartFile file : images) {
			String n = file.getOriginalFilename();
			file.transferTo(new File(dir, n));
			System.out.println("Save " + n);
		}
		
		return new ResponseResult<Void>(1, "成功");
	}
	
	private byte[] createExcel() throws IOException {
		// 利用POI API 創建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 創建工作表
		XSSFSheet sheet = workbook.createSheet("九九乘法表");
		
		// 九九乘法表算法
		for (int i = 1; i <= 9; i++) {
			// 在工作表中創建行, 參數為行號
			XSSFRow row = sheet.createRow(i-1);
            for (int j = 1; j <= 9; j++) {
            	// 在行中創建單元格, 參數是單元格序號
        		XSSFCell cell = row.createCell(j-1);
        		String line = i + " * " + j + " = " + i * j;
        		// 在單元格中添加數據
        		cell.setCellValue(line);
        		
        		// 測試輸出
                System.out.println(line + "\t");
            }
            System.out.println("\n");
        }
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 將Excel寫到流中
		workbook.write(out);
		workbook.close();
		out.close();
		// 從流中獲取全部數據
		byte[] bytes = out.toByteArray();
		
		return bytes;
	}
	
	private byte[] createPng(String code) throws IOException {
		// 創建一個圖片對象
		BufferedImage img = new BufferedImage(100, 40, BufferedImage.TYPE_3BYTE_BGR);
		Random r = new Random();
		for(int i = 0 ; i < 5000 ; i++) {
			int x = r.nextInt(img.getWidth());
			int y = r.nextInt(img.getHeight());
			int rgb = r.nextInt(0xffffff);
			img.setRGB(x, y, rgb);
		}
		// 利用API繪製驗證碼字符串
		Graphics2D g = img.createGraphics();
		Color c = new Color(r.nextInt(0xffffff));
		g.setColor(c);
		Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 35);
		g.setFont(font);
		g.drawString(code, 5, 36);
		
		// 利用API繪製混淆線
		for(int i = 0 ; i < 10 ; i++) {
			int x1 = r.nextInt(img.getWidth());
			int y1 = r.nextInt(img.getHeight());
			int x2 = r.nextInt(img.getWidth());
			int y2 = r.nextInt(img.getHeight());
			g.setColor(new Color(r.nextInt(0xffffff)));
			g.drawLine(x1, y1, x2, y2);
			
		}
		
//		img.setRGB(0, 0, 0x0000ff);
//		img.setRGB(50, 20, 0xffff00);
		// 將圖片對象編碼為.png數據
		// 創建數組輸出流, 作為緩存區(醬油瓶)
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 將png圖片數據(醬油)緩存到緩存區
		ImageIO.write(img, "png", out);
		out.close();
		// 獲取緩存區中的數據(醬油)
		byte[] bytes = out.toByteArray();
		return bytes;
	}
}
