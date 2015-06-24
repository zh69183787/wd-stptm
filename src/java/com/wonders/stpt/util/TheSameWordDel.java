package com.wonders.stpt.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*******************************
 * 本类的作用是删除文本里面的相同行
 * 作者：lujan863
 * 时间：2012年3月7日
 * 南京农业大学
 * ******************************/

public class TheSameWordDel{
	public static void theSamewordDel() throws IOException{
//		File file = new File("C:pandian.txt");// 指定源文件路径
//		BufferedReader input = new BufferedReader(
//				new InputStreamReader(new FileInputStream(file)));
		//读取文本文件路径
//		InputStreamReader reader = new InputStreamReader(System.in);
//		BufferedReader input = new BufferedReader(reader);
		System.out.println("");
		System.out.println("工具名称：文本文件相同行删除工具");
		System.out.println("操作说明：输入文本文件路径后按Enter键即可处理。");
		System.out.println("");
		System.out.println("Please Input the Text Path(请输入文本文件路径):");
//		String path = input.readLine();
		
		//读取文本文件内容，并存到WordList数组内
		File f = new File("C:pandian.txt");
		if(!f.exists()){
			System.out.println("Sorry,the file is unexists(对不起，文件不存在！)!");
		}else{			
			FileInputStream in = new FileInputStream(f);
			InputStreamReader inReader = new InputStreamReader(in); 
			BufferedReader br = new BufferedReader(inReader);
			ArrayList<String> astr=new ArrayList<String>();
			String data = br.readLine();//一次读入一行，直到读入null为文件结束  
			while( data!=null){  
				//System.out.println(data);  
				astr.add(data);
//				String str1 = data.substring(50, 70); //新资产编号
//				String str2 = data.substring(70, 89); //盘点时间(yyyy-mm-dd hh:mm:ss) 71-89位
//				String str3 = data.substring(89, 109); //盘点人   90-109位
//				String str4 = data.substring(109, 113); //盘点结果 110-113位 
//				String str5 = data.substring(114, 120); //盘点结果详细，115-120位
//				
//				astr.add(str1);
//				astr.add(str2);
//				astr.add(str3);
//				astr.add(str4);
				data = br.readLine(); //接着读下一行  
//				System.out.println("11111111"+astr.size());
			}  
			
			//读取完后，删除文件
			f.delete();
			
			//删除重复行，获取不重复String数组str
			ArrayList<String> astr_back=new ArrayList<String>();
			boolean boo = true;

			for(int i=0;i<astr.size();i++){			
				for(int j=i;j<astr.size();j++){
					
					
					System.out.println("第"+i+","+j+"次比较："+astr.get(i)+":"+astr.get(j)+"--"+astr.get(i).equals(astr.get(j)));
					
					
					if(astr.get(i).substring(50, 70).equals(astr.get(j).substring(50, 70)) && 
							Date(astr.get(i).substring(70, 89)) <= Date(astr.get(j).substring(70, 89)) &&(i != j)){//用Date构造
//					if(astr.get(i).equals(astr.get(j)) && (i != j)){
						System.out.println("找到重复行："+astr.get(i)+".....状态：已过滤!");
						boo =false;
						break;
					}
					
					
					//System.out.println("比较结果："+boo);
				}
				if(boo){
					astr_back.add(astr.get(i));
					//System.out.println(astr_back.size());
				}
				boo = true;
			}
			
			
			String[] str = new String[astr_back.size()];
			for(int i=0;i<astr_back.size();i++){
				str[i] = astr_back.get(i);
				//System.out.println(str[i]);
			}
			
			//创建文件
			File fn= new File("C:pandian.txt");
			fn.createNewFile();
			
			//写入数据
			FileWriter fw = new FileWriter(fn);
			for(int i = 0;i<str.length;i++){
				//System.out.println(str[i]);
				fw.write(str[i] + "\r\n");
				//fw.write("我在写入东西\r\n");
			}
			fw.flush();//清空缓存区
			fw.close(); 
			System.out.println("Proccess Finished(处理结束)!");
			//System.out.println(astr == astr_back);
			//System.out.println(astr.size());
			//System.out.println(astr_back.size());
			
			
		}
	}
	
	
	public static long Date(String StringToDate){
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			Date d1;
			try {
				d1 = sdf.parse(StringToDate);
				return d1.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
	}
	
	public static void main(String[] args) throws IOException{
		theSamewordDel();
	}	
}
