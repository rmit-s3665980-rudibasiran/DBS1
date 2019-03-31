import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;

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

		long startTime = System.nanoTime();

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
		int ttlNumRec = 0;

		DataOutputStream dos = null;
		DataOutputStream pos = null;
		FileInputStream inputStream = null;
		Scanner sc = null;

		Helper.drawLine();

		Page p;
		dos = Helper.openOutputStream(heapfile);
		pos = Helper.openOutputStreamPage(heapfile + ".page");

		try {
			inputStream = new FileInputStream(datafile);
			sc = new Scanner(inputStream, "UTF-8");
			sc.nextLine();
			Record record = null;
			int checkSizeofPage = pagesize - GlobalClass.pagegap;

			p = new Page(pagesize, numPage);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();

				String[] attributes = line.split(GlobalClass.delimiter);
				record = Helper.createRecord(attributes);

				if (checkSizeofPage - record.getSizeOfRecord() > 0) {
					checkSizeofPage = checkSizeofPage - record.getSizeOfRecord();
				} else {
					if (GlobalClass.usePageClass)
						Helper.writePage(p, pos);

					numPage++;
					checkSizeofPage = pagesize - GlobalClass.pagegap;
					if (GlobalClass.usePageClass)
						p.clearPage(numPage);
				}
				if (GlobalClass.usePageClass)
					p.fillRecord(record);

				Helper.writeRecords(record, dos);

				ttlNumRec++;
				System.out.println("[Reading CSV line: " + ttlNumRec + "][Page:" + numPage + "]");

			}
			// scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}

		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}

		pos.close();
		dos.close();

		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;

		Helper.logger(ttlNumRec, numPage, totalTime, GlobalClass.logWrite);

		Helper.drawLine();

	}

}
