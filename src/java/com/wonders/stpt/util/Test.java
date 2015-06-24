package com.wonders.stpt.util;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 //分解txt各行
public class Test{
    public static void main(String args[]){
        String[] strings=new String[0]; 
        String line=null;
        String wrongValue = "0102";
        Map<String,String> map = new HashMap<String,String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:pandian.txt"));
            while((line=br.readLine())!=null){
                String[] temp=new String[strings.length+1];
                System.arraycopy(strings, 0, temp, 0, strings.length);
                temp[temp.length-1]=line;
                strings=temp;
            }
            System.out.println(strings.length);
            for(String str:strings){
            	String str1 = str.substring(50, 70); //新资产编号
				String str2 = str.substring(70, 89); //盘点时间(yyyy-mm-dd hh:mm:ss) 71-89位
				String str3 = str.substring(89, 109); //盘点人   90-109位
				String str4 = str.substring(109, 113); //盘点结果 110-113位 
				String str5 = str.substring(114, 120); //盘点结果详细，115-120位
				
				if(str4.equals(wrongValue)){
					str4=str5;
				}
				map.put(str1, str4);
				
				System.out.println(str1);
				System.out.println(str2);
				System.out.println(str3);
				System.out.println(str4);
				System.out.println(str5);
				System.out.println("-----------");
            }
            AnalyseXml.analyseXml("C:\\3333.xml", "C:Test1_Edited.xml", map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}