/*
Title: RMIT Database Systems Assignment 1
Developer(s): 
- Rudi Basiran <s3665980@student.rmit.edu.au> 
Date Created: 30 March 2019 
Description: --
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
	private String side_of_sStreet;
	private String in_violation;

	public Record() {

	}

	public Record(String device_id, String arrival_time, String departure_time, String duration_seconds,
			String street_marker, String sign, String area, String street_id, String street_name,
			String between_street1, String between_street2, String side_of_sStreet, String in_violation) {
		this.device_id = device_id;
		this.arrival_time = arrival_time;
		this.street_id = street_id;
		this.street_name = street_name;
	}

	public String getRecord() {
		return device_id + "," + arrival_time + "," + street_id + "," + street_name;
	}

	@Override
	public String toString() {
		return "Record [device_id=" + device_id + ", arrival_time=" + arrival_time + ", street_id=" + street_id
				+ ",street_name=" + street_name + "]";
	}
}
