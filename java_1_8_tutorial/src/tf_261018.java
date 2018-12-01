import java.util.ArrayList;
import java.util.List;

public class tf_261018 {
	
	
	public static void main(String[] args) {
		
		List<MyObject> objectList = new ArrayList<MyObject>();
		
		MyObject a = new MyObject();
		MyObject b = new MyObject();
		MyObject c = new MyObject();
		MyObject d = new MyObject();
		MyObject e = new MyObject();
		MyObject f = new MyObject();
		MyObject g = new MyObject();
		
		//a and b is 0
		a.setName("800");
		a.setSeq("1");
		a.setParentSeq("");
		
		b.setName("800");
		b.setSeq("2");
		b.setParentSeq("");
		
		c.setName("850");
		c.setSeq("3");
		c.setParentSeq("1");
		
		d.setName("851");
		d.setSeq("4");
		d.setParentSeq("2");
		
		e.setName("852");
		e.setSeq("5");
		e.setParentSeq("2");
		
		f.setName("803");
		f.setSeq("6");
		f.setParentSeq("5");
		
		g.setName("823");
		g.setSeq("7");
		g.setParentSeq("4");
		
		//=============================//
		
		objectList.add(a);
		objectList.add(b);
		objectList.add(c);
		objectList.add(d);
		objectList.add(e);
		objectList.add(f);
		objectList.add(g);
		
		List<MyObject> mapList = new ArrayList<MyObject>();
		
		testSolution1(objectList,mapList, "2");
		
		mapList.forEach((model) -> {
			System.out.println(model.getName());
		});
		
	}
	
	
	private static void testSolution1(List<MyObject> mapList, List<MyObject> resultList, String parentSeq)
	{
		for(MyObject test : mapList)
		{
			if(test.getParentSeq().equals(parentSeq))
			{
				resultList.add(test);
				testSolution1(mapList,resultList,test.getSeq());
			}
		}
	}

	static class MyObject{
		public String name;
		public String seq;
		public String parentSeq;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSeq() {
			return seq;
		}

		public void setSeq(String seq) {
			this.seq = seq;
		}

		public String getParentSeq() {
			return parentSeq;
		}

		public void setParentSeq(String parentSeq) {
			this.parentSeq = parentSeq;
		}

	}
}
