package com.qa.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.base.BaseTest;

public class CommonUtils extends BaseTest {

	public static String CaptureScreenshot(String substring) {

		String SSFilePath = null;

		LocalDateTime dt = LocalDateTime.now();

		String Subfoldername = dt.format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss"));

		System.out.println("Date = " + Subfoldername);

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		File trg = new File("./test-output/screenshots/" + Subfoldername + "/" + substring + ".png");
		try {
			FileUtils.copyFile(src, trg);

			SSFilePath = trg.getAbsolutePath();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Issue with ss capture process.....failed!!!!");

			e.printStackTrace();

		}

		return SSFilePath;
	}

}
