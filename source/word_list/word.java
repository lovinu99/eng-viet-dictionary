package word_list;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.util.Comparator;

import com.sun.xml.internal.bind.v2.runtime.output.UTF8XmlOutput;
public class word implements Comparator<word>, Comparable<word> {
	public String word;
	public String  meaning;
	public word() {
		word="";
		meaning="";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	public word(String wordstring, String mean) {
		word=wordstring;
		meaning=mean;
	}
	public String toString() {
		 return word+" - "+ meaning+"\n";
	}
	public int compareTo(word b) {
		return (int) (this.word).compareTo(b.word);
	}
	public int compare(word a, word b) {
		return (int) (a.word).compareTo(b.word);
	}
	public static void main(String[] args) {
		
	}
}
