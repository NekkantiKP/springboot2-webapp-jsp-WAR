package net.guides.springboot2.springboot2webappjsp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CSVCreater {


	public static void main(String[] args) throws Exception {
		Emp e=new Emp(1,"jack","rom");
		Designation d=new Designation("SE","computers");
		Emp[] empList=new Emp[] {e,e};
		Designation[] dList=new Designation[] {d,d};
		byte[] csvData = getByteArrayForCSV(empList,dList);
		OutputStream os = null;
		os = new FileOutputStream(new File("b.csv"));
		os.write(csvData);
		os.close();
	}

	private static byte[] getByteArrayForCSV(Emp[] e,Designation d[]) {
		try {
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < d.length; i++) {
				sb.append(e[i].toCSV());
				sb.append(d[i].toCSV());	
				sb.append("\n");
			}
			return sb.toString().getBytes();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
}

class Emp{
	int eno;
	String ename;
	String address;
	public Emp() {
		// TODO Auto-generated constructor stub
	}
	
	public Emp(int eno, String ename, String address) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.address = address;
	}

	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String toCSV() {
		return ""+ eno + "," + ename + "," + address + ",";
	}
}

class Designation{
	
	String name;
	String dept;
	public Designation(String name, String dept) {
		super();
		this.name = name;
		this.dept = dept;
	}
	public String toCSV() {
		return ""+ name + "," + dept + ",";
	}
}
