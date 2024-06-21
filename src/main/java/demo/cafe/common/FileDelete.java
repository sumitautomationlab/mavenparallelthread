package demo.cafe.common;

import java.io.File;

public class FileDelete {
	
	public static void final_file_delete() throws Exception {
		String delete = ".\\Test_Result";
		String not_delete_1 = "Analytics_Report";
		String not_delete_2 = ".\\Test_Result\\Final";
		File folder=new File(delete);
		if(folder.exists() && folder.isDirectory()){

			for (File f : folder.listFiles()) {
				if (f.toString().contains(not_delete_1) || f.toString().equals(not_delete_2) ) {
					System.out.println("No files are deleted");
					continue;
				}
				if(f.delete()){
					System.out.println("'"+f.getName()+"' deleted successfully");
				}else{
					System.out.println("Fail to delete '"+f.getName()+"'");
				}
			}
		}
	}

}
