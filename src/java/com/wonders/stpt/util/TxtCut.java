package com.wonders.stpt.util;
import java.io.*;//截取文件中指定的内容
import java.util.HashMap;
import java.util.Map;
public class TxtCut {	
	private static BufferedWriter out;
	
	public static String cut( String path) {	
		
		File file = new File("C:pandian.txt");// 指定源文件路径
		String wrongValue = "0102";
		try {			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));
			String str = "";	
			Map<String,String> map = new HashMap<String,String>();
			int count = 0;
			 while ((str=in.readLine()) != null) {
					count++;
					 for(int i = 0;i<count; i++){
							String str1 = str.substring(50, 70); //新资产编号
							String str2 = str.substring(70, 89); //盘点时间(yyyy-mm-dd hh:mm:ss) 71-89位
							String str3 = str.substring(89, 109); //盘点人   90-109位
							String str4 = str.substring(109, 113); //盘点结果 110-113位 
							String str5 = str.substring(114, 120); //盘点结果详细，115-120位
//							out.write(str1 + "\r\n");
							System.out.println(str4);
							if(str4.equals(wrongValue)){
								str4=str5;
							}
//							System.out.println(str1);
//							System.out.println(str2);
//							System.out.println(str3);
//							System.out.println(str4);
							System.out.println(str5);
							System.out.println("-----------");
							map.put(str1, str4);
							
						}
			 }
			 System.out.println("111:"+str);
//			 AnalyseXml.analyseXml("C:3333.xml", "C:Test_Edited.xml", map);
			 System.out.println(count);
			
			 
			 
//			while(n-->0){
//				str = in.readLine();
//			}
			/*			 
			 * * 可在此处对str进行修改			
			 */		
//			str = in.readLine();
//			out.write(str + "\r\n");	
//			
//			str = str.substring(114, 120);
//			System.out.println(str);
				
			}catch (FileNotFoundException e) {	
				e.printStackTrace();		
			} catch (IOException e) {		
				e.printStackTrace();		
			}
		return null;			
		}	
	public static void main(String[] args) {	
		cut("C:pandian.txt");
		}
	}
