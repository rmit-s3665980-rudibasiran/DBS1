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

		String text = ""; // text to search for
		String t_pagesize = ""; // temp page size to capture integer input
		String datafile = ""; // file to read

		text = args[0]; // "text"
		t_pagesize = args[1]; // "pagesize"

		int pagesize = GlobalClass.pagesize; // page size
		pagesize = Integer.parseInt(t_pagesize);
		String heapfile = "heap." + t_pagesize;

		// Reading data from the same file
		DataInputStream dis = null;
		dis = Helper.openInputStream(heapfile);

		int x = 0;
		byte[] tmpByteArray = new byte[pagesize];
		Record r = new Record();
		while (dis.available() > 0) {

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
			boolean in_violation = (dis.readUTF() == "TRUE" ? true : false);
			x++;

			if (da_name.contains(text)) {
				System.out.println("Found in record [" + x + "] " + device_id + "-->" + arrival_time + " --> "
						+ street_name + " --> " + side_of_street + " -->" + (in_violation ? "TRUE" : "FALSE"));
			}

		}

		// while ((x = dis.read(tmpByteArray)) != -1) {

	}

}
