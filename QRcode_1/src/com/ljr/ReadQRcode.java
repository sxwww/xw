package com.ljr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

public class ReadQRcode {

	
	public static void main(String[] args) {
		File file = new File ("D:/qrcode.png");
		BufferedImage bufferedImage = null;
		try{
			bufferedImage = ImageIO.read(file);
		}catch (IOException e ){
			e.printStackTrace();
		}
		QRCodeDecoder qrCodeDecoder = new QRCodeDecoder();
		byte[] decode = qrCodeDecoder.decode(new MyQRCodeImage(bufferedImage));
		String result = new String (decode);
		System.out.println("½âÂë£º"+result);
	}

}
