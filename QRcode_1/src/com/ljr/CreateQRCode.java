package com.ljr;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode {
	public static void CreateQRCode(String content, String imgPath,  int version, String startRgb,  String endRgb) throws IOException{
		int imgSize = 67+(version-1)*12;
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeVersion(version);
		//���ö�ά����Ŵ���
		//qrcode.setQrcodeErrorCorrect('L');
		//�汾��Ϣ
		qrcode.setQrcodeEncodeMode('B');
		//����һ��ͼƬ�������
		BufferedImage bufferedImage= new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
		//�����滭����
		Graphics2D gs = bufferedImage.createGraphics();
		//���ñ�����ɫ
		gs.setBackground(Color.WHITE);
		//���������ɫ
		gs.setColor(Color.BLACK);
		//������
		gs.clearRect(0, 0, imgSize,imgSize);
		//��ά������
		
		//ͨ����ά��Ҫ�������ݻ�ȡһ���������͵Ķ�ά����
		boolean[][]calQrcode = qrcode.calQrcode(content.getBytes("utf-8"));
		int pixoff = 2;
		Random random = new Random();
		//System.out.println(calQrcode.length);
		//��ɫ����ɫ����
		int startR = 0 , startG = 0 , startB = 0;
		if (null != startRgb){
			String [] srgb = startRgb.split(",");
			startR = Integer.valueOf (srgb[0]);
			startG = Integer.valueOf (srgb[1]);
			startB = Integer.valueOf (srgb[2]);
		}
		int endR = 0 , endG = 0 , endB = 0;
		if (null != endRgb){
			String [] ergb = endRgb.split(",");
			endR = Integer.valueOf (ergb[0]);
			endG = Integer.valueOf (ergb[1]);
			endB = Integer.valueOf (ergb[2]);
		}
		for(int i=0; i<calQrcode.length;i++){//������
			for(int j=0; j<calQrcode[i].length;j++){//������
				if(calQrcode[i][j]){
					int r = startR + (endR - startR)*(i+1)/calQrcode.length;
					int g = startG + (endG - startG)*(i+1)/calQrcode.length;
					int b = startB + (endB - startB)*(i+1)/calQrcode.length;
					if (r >255){
						r = 255;
					}
					if (g >255){
						g = 255;
					}
					if (b >255){
						b = 255;
					}
					
					Color color = new Color(r,g,b);
					gs.setColor(color);
					gs.fillRect(i*3+pixoff, j*3+pixoff, 3, 3);
				}
				
			}
		}
		//���ͷ��
		BufferedImage logo = ImageIO.read(new File ("D:/1.jpg"));
		int logoSize = imgSize/3;
		int O = (imgSize-logoSize)/2;
		gs.drawImage(logo,O,O,logoSize,logoSize,null);
		//�رջ滭����
		gs.dispose();
		//�ѻ�����ͼƬ������ڴ浱��
		bufferedImage.flush();
		try{
			ImageIO.write(bufferedImage,"png",new File(imgPath));	
		}catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("������");
	}

		private static final int D = 0;
		private static final String StartRgb = null;
		private Object qr;
	public static void main(String[] args) throws IOException{
		String content = "BEGIN:VCARD\r\n" + 
		                  "FN:����:�����\r\n"+
		                  "ORG:ѧУ:�ӱ��Ƽ�ʦ��ѧԺ\r\n"+
		                  "TEL;CELL;VOICE:15076037670\r\n"+
		                  "ADR;HOME:�ӱ�  ����\r\n"+
		                  "EMAIL;HOME:1074284966@qq.com\r\n" + 
		                 "END:VCARD";
		String imgPath = "D:/qrcode.png";
		int version = 15;
		String startRgb = "255,126,0";
		String endRgb = "153,0,169";
		CreateQRCode(content, imgPath, version,startRgb,endRgb);
	}
}
	
		

