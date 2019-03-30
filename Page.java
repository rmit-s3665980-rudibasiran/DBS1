import java.util.List;

/*
Title: RMIT Database Systems Assignment 1
Developer(s): 
- Rudi Basiran <s3665980@student.rmit.edu.au> 
Date Created: 30 March 2019 
Description: Page Class
Notes: --
Change History:
 */
public class Page {

	private int pagesize;
	private Record[] record;
	private List<Record> records;

	public Page() {
		this.pagesize = GlobalClass.pagesize;
	}

	public Page(int p) {
		this.pagesize = p;
	}

	public void addRecord(Record r) {
		records.add(r);
	}

	public boolean notFull(int x) {
		return ((record.length + x) < pagesize ? true : false);
	}
}
