package com.jqg.job;



import java.io.File;



/**
 * 数据库备份恢复
 * @author	曹中德
 * @version	 1.0
 *
 */
public class DbBackupRes {
	
	
	/**
	 * 备份执行的语句
	 */
	public static  String backupSql="mysqldump  -uroot -p123456  --hex-blob --lock-all-tables jqgp2p >";
	/**
	 * 恢复执行的语句
	 */
	public static String restoreSql;
	/**
	 * 数据库执行文件位置
	 */
	public static String dir="C:\\mysql_backup";
	/**
	 * 备份文件保存位置
	 */
	public static String savepath="c:/mysql_backup/";
	
	/**
	 * 备份数据 
	 * @param fileName 保存的文件名称
	 */
	public static void backupDatabase(String fileName){
	
	      try{
	  			File file=new File(savepath);
	  			if(!file.exists())file.mkdirs();//目录不存在创建目录
	  			Runtime.getRuntime().exec("cmd /c "+backupSql+savepath+"/"+fileName,null,new File(dir));
	  			System.out.println("备份完成");
	  			
	      } catch (Exception e) {   
	    	  
	    	  
	    	  System.out.println("备份失败");
	   
	    	  
	      }
	     
		
	}
	/**
	 * 恢复数据
	 * @param file
	 */
	public static void restoreDatabase(File file){
		try{
			System.out.println("==="+restoreSql+file.getPath());
  			Runtime.getRuntime().exec("cmd /c "+restoreSql+file.getPath(),null,new File(dir));
  			System.out.println("恢复完成");
      
        } catch (Exception e) {   
    	  
    	  
    	  System.out.println("恢复失败");
   
    	  
       }
	}
	
	
	
	
}
