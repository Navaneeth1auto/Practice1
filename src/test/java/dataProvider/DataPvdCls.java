package dataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataPvdCls {
	
	@Test(dataProvider="credentials")
	public void dataProviderEx(String userName, String password)
	{
		System.out.println(userName+",  "+password);
	}
	
	@DataProvider
	public Object[][] credentials() throws Throwable
	{
		ExcelRead read=new ExcelRead("C:\\Users\\Navaneeth\\eclipse-workspace\\mavenExample\\TestData.xlsx", "Flipkart");
		int NoOfRow=read.NoOfRows()+1;
		System.out.println(NoOfRow);
		Object[][] dataSet = new Object[NoOfRow][2];
		for(int i=0;i<NoOfRow;i++)
		{
			dataSet[i][0]=read.read(i, "UserName");
			dataSet[i][1]=read.read(i, "Password");
		}
		return dataSet;
	}

}
