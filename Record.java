/*
Title: RMIT Database Systems Assignment 1
Developer(s): 
- Rudi Basiran <s3665980@student.rmit.edu.au> 
Date Created: 30 March 2019 
Description: Record Class
Notes: --
Change History:
 */

class Record {

	private String device_id;
	private String arrival_time;
	private String departure_time;
	private String duration_seconds;
	private String street_marker;
	private String sign;
	private String area;
	private String street_id;
	private String street_name;
	private String between_street1;
	private String between_street2;
	private String side_of_street;
	private String in_violation;

	public Record() {

	}

	public Record(String device_id, String arrival_time, String departure_time, String duration_seconds,
			String street_marker, String sign, String area, String street_id, String street_name,
			String between_street1, String between_street2, String side_of_street, String in_violation) {

		this.device_id = device_id;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
		this.duration_seconds = duration_seconds;
		this.street_marker = street_marker;
		this.sign = sign;
		this.area = area;
		this.street_id = street_id;
		this.street_name = street_name;
		this.between_street1 = between_street1;
		this.between_street2 = between_street2;
		this.side_of_street = side_of_street;
		this.in_violation = in_violation;
	}

	public String getRecord() {
		return device_id + GlobalClass.delimiter + arrival_time + GlobalClass.delimiter + departure_time
				+ GlobalClass.delimiter + duration_seconds + GlobalClass.delimiter + street_marker
				+ GlobalClass.delimiter + sign + GlobalClass.delimiter + area + GlobalClass.delimiter + street_id
				+ GlobalClass.delimiter + street_name + GlobalClass.delimiter + between_street1 + GlobalClass.delimiter
				+ between_street2 + GlobalClass.delimiter + side_of_street + GlobalClass.delimiter + in_violation;
	}

}
