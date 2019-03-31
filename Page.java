import java.util.ArrayList;
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

	private int pagenumber;
	private int pagesize;
	private int balance;
	private int gap = GlobalClass.pagegap;
	private List<Record> records = new ArrayList<>();

	public Page() {
		this.pagesize = GlobalClass.pagesize;
	}

	public Page(int n, int p) {
		this.pagenumber = n;
		this.pagesize = p;
		this.balance = p - gap;

	}

	public int getPageNumber() {
		return this.pagenumber;
	}

	public void addRecord(Record r) {
		this.records.add(r);
		this.balance = this.balance - r.getSizeOfRecord();

		if (GlobalClass.debugMode) {
			System.out.println("Page Number:" + this.pagenumber);
			System.out.println("Balance:" + this.balance);
			System.out.println("No. of Records:" + this.records.size());
			System.out.println("Size. of New Record:" + r.getSizeOfRecord());
		}
	}

	public boolean isFull(int newRecLength) {
		return ((this.balance - newRecLength) < 0 ? true : false);
	}

	public int getRemaining() {
		return (this.balance);
	}

	public List<Record> getRecords() {
		return this.records;
	}

	public int numRecords() {
		return this.records.size();
	}
}
