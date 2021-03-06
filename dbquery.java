import java.io.DataInputStream;

/*
Title: RMIT Database Systems Assignment 1
Developer(s): 
- Rudi Basiran <s3665980@student.rmit.edu.au> 
Date Created: 31 March 2019 
Description: dbquery Class
Notes: --
Change History:
 */
public class dbquery {

	public dbquery() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// java dbquery text pagesize

		// Note that input error detection is not implemented as the aim of the codes is
		// to simulate paging and it assumes that the user will input erroneous
		// parameters when executing the program.

		long startTime = System.nanoTime();

		String textSearch = ""; // text to search for
		String t_pagesize = ""; // temp page size to capture integer input
		String datafile = ""; // file to read

		textSearch = args[0]; // "text"
		t_pagesize = args[1]; // "pagesize"

		int pagesize = GlobalClass.pagesize; // page size
		pagesize = Integer.parseInt(t_pagesize);
		String heapfile = "heap." + t_pagesize;

		// Reading data from the same file
		DataInputStream dis = null;
		dis = Helper.openInputStream(heapfile);

		int x = 0;
		int ttlNumRec = 0;
		int numPage = 0;
		int numFound = 0;
		int checkSizeofPage = pagesize - GlobalClass.pagegap;
		byte[] tmpByteArray = new byte[pagesize];

		while (dis.available() > 0) {

			ttlNumRec++;

			// read as UTF, Int or Double

			String da_name = dis.readUTF();
			String device_id = dis.readUTF();
			String arrival_time = dis.readUTF();
			String departure_time = dis.readUTF();
			double duration_seconds = dis.readDouble();
			String street_marker = dis.readUTF();
			String sign = dis.readUTF();
			String area = dis.readUTF();
			String street_id = dis.readUTF();
			String street_name = dis.readUTF();
			String between_street1 = dis.readUTF();
			String between_street2 = dis.readUTF();
			int side_of_street = dis.readInt();
			String in_violation = dis.readUTF();
			x++;

			// populate instance with data
			Record r = new Record(device_id, arrival_time, departure_time, duration_seconds, street_marker, sign, area,
					street_id, street_name, between_street1, between_street2, side_of_street, in_violation);

			// check whether page has been read
			if (checkSizeofPage - r.getSizeOfRecord() > 0) {
				checkSizeofPage = checkSizeofPage - r.getSizeOfRecord();
			} else {
				numPage++;
				checkSizeofPage = pagesize - GlobalClass.pagegap;
			}

			// search for queried text
			if (da_name.contains(textSearch)) {
				numFound++;

				Helper.loggerMatch(
						"Found in record [" + x + "]: " + device_id + "-->" + arrival_time + "-->" + duration_seconds
								+ "-->" + street_name + "--> " + side_of_street + "-->" + in_violation + "\n");

			}

		}

		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;

		Helper.logger(ttlNumRec, numPage, totalTime, GlobalClass.logSearch);

		Helper.drawLine();

	}

}
