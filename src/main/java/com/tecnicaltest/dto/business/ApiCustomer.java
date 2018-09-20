package com.tecnicaltest.dto.business;

public class ApiCustomer {

	private Long id;

	private String fullName;

	private String idDocument;

	private String address;

	private String firstName;

	private String lastName;


	public ApiCustomer () {
		super();
	}
	
	public ApiCustomer(Long id, String firstName, String lastName, String idDocument, String address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idDocument = idDocument;
		this.address = address;
		this.fullName = ((firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName)).trim();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(String idDocument) {
		this.idDocument = idDocument;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
