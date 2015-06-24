package com.wonders.stpt.util;

/*    */ 
/*    */ import java.security.MessageDigest;
/*    */ 
/*    */ public class MD5Util
/*    */ {
   private static final String[] hexDigits = { 
     "0", "1", "2", "3", "4", "5", "6", "7", 
     "8", "9", "a", "b", "c", "d", "e", "f" };
/*    */ 
/*    */   public static String byteArrayToHexString(byte[] b)
/*    */   {
/* 18 */     StringBuffer resultSb = new StringBuffer();
/* 19 */     for (int i = 0; i < b.length; ++i)
/*    */     {
/* 21 */       resultSb.append(byteToHexString(b[i]));
/*    */     }
/* 23 */     return resultSb.toString();
/*    */   }
/*    */ 
/*    */   private static String byteToHexString(byte b)
/*    */   {
/* 28 */     int n = b;
/* 29 */     if (n < 0)
/*    */     {
/* 31 */       n += 256;
/*    */     }
/* 33 */     int d1 = n / 16;
/* 34 */     int d2 = n % 16;
/*    */ 
/* 36 */     return hexDigits[d1] + hexDigits[d2];
/*    */   }
/*    */ 
/*    */   public static String MD5Encode(String origin)
/*    */   {
/* 41 */     String resultString = null;
/*    */     try
/*    */     {
/* 44 */       resultString = new String(origin);
/* 45 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 46 */       resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/*    */     }
/*    */ 
/* 52 */     return resultString;
/*    */   }
/*    */ }

/* Location:           D:\temp\omtask\WEB-INF\classes\
 * Qualified Name:     com.om.common.MD5
 * JD-Core Version:    0.5.3
 */