import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.IntegerSyntax;

public class Test {	
	public static void main(String[] args) {
		List<Integer> integers=new ArrayList<Integer>();
		integers.forEach(i -> System.out.println(i));
		integers.forEach(i -> {
			int ha=i.hashCode();
			System.out.println(ha);
		});
	}
	
}
