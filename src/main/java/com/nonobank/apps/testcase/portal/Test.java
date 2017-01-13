package com.nonobank.apps.testcase.portal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Test {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws IOException {

		// try {
		// OutputStream os = new FileOutputStream("D:/country.csv");
		// CsvWriter writer = new CsvWriter(os, ',', Charset.forName("GBK"));
		// String[] contents = { "case名称", "描述", "入参", "结果", "错误日志" };
		// writer.writeRecord(contents);
		// List<String[]> alList = new ArrayList<String[]>();
		// List<String> list = new ArrayList<String>();
		// list.add("case名称");
		// list.add("描述");
		// list.add("入参");
		// list.add("结果");
		// list.add("错误日志");
		// alList.add(list.toArray(new String[list.size()]));

		// List<List<String>> lst = new ArrayList<>();
		// List<String> lst2 = new ArrayList<>();
		// lst2.add("aaa");
		// lst2.add("bbb");
		// lst2.add("ccc");
		// lst2.add("ddd");
		// lst2.add("eee");
		// lst.add(lst2);
		// for (List<String> rowsData : lst) {
		// List<String> list2 = new ArrayList<String>();
		// for (String rowData : rowsData) {
		// list2.add(rowData);
		// }
		//// alList.add(list2.toArray(new String[list2.size()]));
		// writer.writeRecord(list2.toArray(new String[list2.size()]));
		// }
		//
		// // writer.writeAll(alList);
		// writer.close();
		// System.out.println(writer.toString());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		OutputStream os = new FileOutputStream("./2.csv");
		OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream("./1.csv"), "gbk");
		oStreamWriter.append("我和你");
		oStreamWriter.close();

	}

	public static void aaa() {

	}
}
