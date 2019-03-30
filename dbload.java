import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class dbload {

	public dbload() {

	}

	public static void main(String[] args) {

		// java dbload -p pagesize datafile

		String option = ""; // -p option
		String t_pagesize = ""; // temp page size to capture integer input
		String datafile = ""; // file to read

		option = args[0]; // =="-p"
		t_pagesize = args[1]; // =="pagesize"
		datafile = args[2]; // =="datafile"
		int pagesize = 4096; // page size

		pagesize = Integer.parseInt(t_pagesize);

		String heapfile = "heap." + t_pagesize;

		System.out.println("================================");
		System.out.println("Parameters Captured:");
		System.out.println("Option: " + option);
		System.out.println("Pagesize: " + t_pagesize);
		System.out.println("Datafile: " + datafile);
		System.out.println("Heapfile: " + heapfile);
		System.out.println("================================");

		FileOutputStream fos = null;
		File file;

		String mycontent = "This is my Data which needs to be written into the file";

		try {

			file = new File(heapfile);
			fos = new FileOutputStream(file);

			// create new file if not exist

			if (!file.exists()) {
				file.createNewFile();
			}

			// string converted into bytes
			// byte[] bytesArray = mycontent.getBytes();

			// fos.write(bytesArray);
			fos.flush();
			System.out.println("================================");
			System.out.println("File Written Successfully");
			System.out.println("================================");

			System.out.println("Reading CSV");
			List<Record> records = readRecordsFromCSV(datafile);
			System.out.println("================================");
			System.out.println("Printing CSV");
			for (Record r : records) {
				System.out.println(r);
				byte[] bytesArray = r.getRecord().getBytes();

				fos.write(bytesArray);
			}
			System.out.println("================================");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error in closing the Stream");
			}
		}
	}

	private static List<Record> readRecordsFromCSV(String fileName) {
		List<Record> records = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			// read the first line from the csv - ignore the header
			String line = br.readLine();

			// read next line after header
			line = br.readLine();

			// loop until all lines are read
			while (line != null) {
				String[] attributes = line.split(",");
				Record record = createRecord(attributes);

				// adding book into ArrayList
				records.add(record);

				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();

			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return records;
	}

	private static Record createRecord(String[] metadata) {

		String device_id = metadata[0];
		String arrival_time = metadata[1];
		String departure_time = metadata[2];
		String duration_seconds = metadata[3];
		String street_marker = metadata[4];
		String sign = metadata[5];
		String area = metadata[6];
		String street_id = metadata[7];
		String street_name = metadata[8];
		String between_street1 = metadata[9];
		String between_street2 = metadata[10];
		String side_of_sStreet = metadata[11];
		String in_violation = metadata[12];

		// create and return record of this metadata
		return new Record(device_id, arrival_time, departure_time, duration_seconds, street_marker, sign, area,
				street_id, street_name, between_street1, between_street2, side_of_sStreet, in_violation);
	}

}