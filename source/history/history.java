package history;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.w3c.dom.css.Counter;

import com.sun.accessibility.internal.resources.accessibility;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import jdk.nashorn.internal.ir.Flags;
import sun.nio.cs.HistoricallyNamedCharset;
import xmlfile_worker.xmlfile;
public class history {
	public String date;
	public List<String> wordslist;
	public history() {
		date=LocalDateTime.now().toString().substring(0,10);
		wordslist = new ArrayList<String>();
	}
	public String toString() {
		String string="";
		for (String stringi : wordslist) {
			string +=stringi+"\n";
		}
		return date+"\n" +string;
	}
	public static String counting(String Startday, String endday, List<history> list) {
		List<String> a= new ArrayList<String>();
		for (history date : list) {	
			if(date.date.compareTo(Startday)>=0 )
				if(date.date.compareTo(endday)<=0 ) 
					for (String stringi : date.wordslist) 
						a.add( new String(stringi));
		}
		return count(a);
	}
	public static String count(List<String> list) {
		java.util.Collections.sort(list);
		System.out.println(list);
		int dem=1;
		String s="";
		if(list.size()==0)
			return "Du lieu nhap sai\nhay nhap lai ngay thang";
		s+=list.get(0);
		for(int i=0;i<list.size()-1;i++) {
			if(list.get(i).compareTo(list.get(i+1))==0) {
				if(i==list.size()-1)
					s+=": " + String.valueOf(dem);
				else
					dem++;
			}
			else {
				if(i==list.size()-1)
					s+=list.get(i+1)+": " + "1";
				else {
					s+=": " + String.valueOf(dem)+"\n"+list.get(i+1);
					dem=1;
				}
			}
		}
		return s+": " + String.valueOf(dem);
	}
	public static void add_hitory(List<history> a, List<String> words ) {
	for (history history : a) {
		// nếu ngày hiện tại có trong lịch sử, hủy
		if(LocalDateTime.now().toString().substring(0,10).compareTo(history.date)==0)
			return;
		}
	history newh= new history();
	newh.date=LocalDateTime.now().toString().substring(0,10);
	newh.wordslist=words;
	a.add(newh);
	}
	public static void main(String[] args) {
		List<history> a = new ArrayList<history>();
//		
//		history b= new history();
//		b.date="2019-05-14";
//		b.wordslist.add("rose");
//		b.wordslist.add("is red");
//		b.wordslist.add("violet");
//		b.wordslist.add("is blue");
//		history c= new history();
//		c.date="2019-05-16";
//		c.wordslist.add("cô dạy em");
//		c.wordslist.add("tập thể dục ");
//		c.wordslist.add("buổi sáng");
//		c.wordslist.add("1234...");
//		history d= new history();
//		d.date="2019-05-18";
//		d.wordslist.add("tuần");
//		d.wordslist.add("nào");
//		d.wordslist.add("cũng");
//		d.wordslist.add("vậy");
//		history f= new history();
//		f.date="2019-05-21";
//		f.wordslist.add("aaaaaa");
//		f.wordslist.add("bbbbbb");
//		f.wordslist.add("ccccccccc");
//		f.wordslist.add("dddddddddd");
//		a.add(b);
//		a.add(c);
//		a.add(d);
//		a.add(f);
//
////		System.out.print(counting("2019-05-22", "2019-05-23", a));
//
//		List<String>aList= new ArrayList<String>();
//		aList.add("love");
//		aList.add("yêu");
//		aList.add("like");
//		aList.add("thích");
//		aList.add(":))");
//		aList.add(":))))");
//		add_hitory(a, aList);
//		System.out.println();
//		for (history history : a) {
//			System.out.println(history);
//		}
//		try {
//			xmlfile.writexmlfile_history("oo.xml", a);
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		xmlfile.readxml_history("oo.xml", a);
//		for (history history : a) {
//			System.out.println(history);
//		}
	}
}