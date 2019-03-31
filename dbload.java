import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Title: RMIT Database Systems Assignment 1
Developer(s): 
- Rudi Basiran <s3665980@student.rmit.edu.au> 
Date Created: 30 March 2019 
Description: dbload Class
Notes: --
Change History:
 */

public class dbload {

	public dbload() {

	}

	public static void main(String[] args) throws Exception {

		// java dbload -p pagesize datafile

		String option = ""; // -p option
		String t_pagesize = ""; // temp page size to capture integer input
		String datafile = ""; // file to read

		option = args[0]; // "-p"
		t_pagesize = args[1]; // "pagesize"
		datafile = args[2]; // "datafile"
		int pagesize = GlobalClass.pagesize; // page size

		pagesize = Integer.parseInt(t_pagesize);

		String heapfile = "heap." + t_pagesize;

		Helper.drawLine();
		System.out.println("Parameters Captured:");
		System.out.println("Option: " + option);
		System.out.println("Pagesize: " + t_pagesize);
		System.out.println("Datafile: " + datafile);
		System.out.println("Heapfile: " + heapfile);
		Helper.drawLine();

		int numPage = 0;
		int ttlBytes = 0;
		int numRec = 0;
		int recordInPage = 0;

		DataOutputStream dos = null;
		try {

			// string converted into bytes
			// byte[] bytesArray = mycontent.getBytes();

			Helper.drawLine();
			System.out.println("File Written Successfully");
			Helper.drawLine();

			// return bytes in UTF-8 character encoding
			// pros - no need to handle UnsupportedEncodingException
			// pros - bytes in specified encoding scheme

			System.out.println("Reading CSV");
			List<Record> records = Helper.readRecordsFromCSV(datafile);
			List<Page> pages = new ArrayList<>();

			Helper.drawLine();

			dos = Helper.openOutputStream(heapfile);
			Page p = new Page(numPage, pagesize);

			for (Record r : records) {
				if (p.isFull(r.getSizeOfRecord())) {
					if (GlobalClass.debugMode) {
						System.out.println("Full! Page No./Record No.:" + numPage + "/" + recordInPage
								+ " [Size of Rec." + r.getSizeOfRecord() + "][Page Space Remaining:" + p.getRemaining()
								+ "][No. of Recs in Page:" + p.numRecords() + "]");
					}

					pages.add(p);
					numPage++;
					p = new Page(numPage, pagesize);
					recordInPage = 0;
					p.addRecord(r);
					recordInPage++;
				} else {
					if (GlobalClass.debugMode) {
						System.out.println("NotFull! Page No./Record No.:" + numPage + "/" + recordInPage
								+ " [Size of Rec." + r.getSizeOfRecord() + "][Page Space Remaining:" + p.getRemaining()
								+ "][No. of Recs in Page:" + p.numRecords() + "]");
					}
					p.addRecord(r);
					recordInPage++;
				}

			}
			boolean found = false;
			for (Page pg : pages) {
				if (pg.getPageNumber() == p.getPageNumber()) {
					found = true;
					break;
				}

			}
			if (!found)
				pages.add(p);
			numPage = 0;
			numRec = 0;
			for (Page pg : pages) {
				numPage++;
				for (Record r : pg.getRecords()) {
					numRec++;
					Helper.writeRecords(numPage, r, dos);
				}
			}

			// to read, use Arrays.toString(bytesArray)
			dos.close();

			Helper.logger(numRec, numPage);

			Helper.drawLine();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (dos != null) {
					dos.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error in closing the Stream");
			}
		}
	}

}
