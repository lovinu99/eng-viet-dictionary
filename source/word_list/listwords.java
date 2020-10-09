package word_list;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.nio.channels.NonReadableChannelException;
import java.nio.charset.Charset;
import java.util.*;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import com.sun.xml.internal.bind.v2.runtime.output.Encoded;
import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class listwords {
	public List<word> listwords;
	public listwords() {
		listwords =  new ArrayList<word>();
	};

	public void sort_inc() {
		java.util.Collections.sort(listwords);
	}
	public void remove_at(int index) {
		listwords.remove(index);
	}
	public String toString() {
		String temp="";
		for (word word : listwords) {
			temp += word.toString();
		}
		return temp;
	}

	public static void main(String[] args) {
		listwords aListwords = new listwords();
		aListwords.listwords.add(new word("aaa","zzzzzzz"));
		aListwords.listwords.add(new word("cccc","zzzzzzz"));
		aListwords.listwords.add(new word("bbbb","zzzzzzz"));
		System.out.print(aListwords);
		aListwords.sort_inc();
		System.out.print(aListwords);
	}
}
