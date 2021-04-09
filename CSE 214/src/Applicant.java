
/**
 * @author Joohak Lee 
 * 108699180 
 * Recitation 1
 * 
 * <code>Applicant</code> that is used for <code>HiringTable</code>. 
 * Applicant has a name, GPA, College, Skills and companyNames
 */

import java.util.*;
import java.lang.IllegalArgumentException;

public class Applicant implements Cloneable {

	private String[] companyName; // array that contains name of the companies of the applicant
	private String applicantName; // the String that is name of the applicant
	private double applicantGPA; // the number of the applicant's GPA
	private String applicantCollege; // the String that is name of the applicant's college
	private String[] applicantSkills; // array that contains the sets of applicant's skills

	/**
	 * no argument constructor to create an applicant object.
	 */
	public Applicant() {
	}

	/**
	 * creates an applicant with specified value
	 * 
	 * @param companyName      the String array to set company names to.
	 * @param applicantName    the String to set applicant name to.
	 * @param applicantGPA     the double to set applicant's GPA to.
	 * @param applicantCollege the String to set applicant College to.
	 * @param applicantSkills  the String array to set applcant skills to.
	 * 
	 * @throws NegativeGPAException thrown if the provided GPA is lower than 0.
	 * @throws Exception            thrown if the provided GPA is higher than 4.
	 */
	public Applicant(String[] companyName, String applicantName, double applicantGPA, String applicantCollege,
			String[] applicantSkills) throws Exception { // a constructor with parameters//
		this.companyName = companyName;
		this.applicantName = applicantName;
		// if the GPA is less than 0, which is imposssible
		if (applicantGPA < 0) {
			throw new NegativeGPAException();
		}
		// if the GPA is higher than 4, which is impossible in unweighted GPA
		if (applicantGPA > 4) {
			throw new Exception("GPA higher than 4");
		}
		this.applicantGPA = applicantGPA;
		this.applicantCollege = applicantCollege;
		this.applicantSkills = applicantSkills;
	}

	/**
	 * set thes name of the company
	 * 
	 * @param companyName The String array to set the name of the companies.
	 */
	public void setCompanyName(String[] companyName) {
		this.companyName = companyName;
	}

	/**
	 * retrieve the name of the companies of the applicant.
	 * 
	 * @return the String array which is the name of the companies of the applicant.
	 */
	public String[] getCompanyName() {
		return companyName;
	}

	/**
	 * sets the name of the applicant
	 * 
	 * @param name The String to set the name of the applicant.
	 */
	public void setApplicantName(String name) {
		this.applicantName = name;
	}

	/**
	 * retrieve the name of the applicant
	 * 
	 * @return the String which is the name of the applicant
	 */
	public String getApplicantName() {
		return applicantName;
	}

	/**
	 * set the GPA of the applicant.
	 * 
	 * @param GPA the double to set the GPA of the applicant
	 * 
	 * @throws NegativeGPAException thrown if the GPA is less than 0.
	 */
	public void setApplicantGPA(double GPA) throws NegativeGPAException {
		// GPA can't be less than 0
		if (GPA < 0) {
			throw new NegativeGPAException();
		} else {
			this.applicantGPA = GPA;
		}
	}

	/**
	 * retrieve the GPA of the applicant
	 * 
	 * @return the double that is the GPA of the applicant
	 */
	public double getApplicantGPA() {
		return applicantGPA;
	}

	/**
	 * set the name of the college of the applicant
	 * 
	 * @param applicantCollege the String that is the name of the applicant's
	 *                         college
	 */
	public void setApplicantCollege(String applicantCollege) {
		this.applicantCollege = applicantCollege;
	}

	/**
	 * retrieve the name of the applicant's college
	 * 
	 * @return the String that is the applicant's college
	 */
	public String getApplicantCollege() {
		return applicantCollege;
	}

	/**
	 * set the name of the applicant's skills
	 * 
	 * @param applicantSkills array of Strings which are the applicant's skills
	 */
	public void setApplicantSkills(String[] applicantSkills) {
		this.applicantSkills = applicantSkills;
	}

	/**
	 * retrieve the applicant's skills
	 * 
	 * @return the array of Strings which are the applicant's skills
	 */
	public String[] getApplicantSkills() {
		return applicantSkills;
	}

	/**
	 * creates and returns the copy of this applicant object
	 * 
	 * @return the new Object which is a copy of this Applicant object.
	 */
	public Object clone() {
		String[] cloneCompanyName = new String[companyName.length];
		String[] cloneApplicantSkills = new String[applicantSkills.length];
		for (int i = 0; i < companyName.length; i++) {
			cloneCompanyName[i] = companyName[i];
		}
		for (int i = 0; i < applicantSkills.length; i++) {
			cloneApplicantSkills[i] = applicantSkills[i];
		}
		Applicant answer = null;
		// try to create a copy of the applicant object
		try {
			answer = new Applicant(cloneCompanyName, applicantName, applicantGPA, applicantCollege,
					cloneApplicantSkills);
		} catch (NegativeGPAException ex) {
		} catch (Exception e) {
		}
		return answer;
	}

	/**
	 * Tests whether the this Applicant object is equal to the supplied parameter by
	 * checking whether the fields are equal.
	 * 
	 * @param obj The object to compare this Applicant object to.
	 * 
	 * @return returns true if two applicants are same, false otherwise.
	 */
	public boolean equals(Object obj) {
		// if the obj object is same as the Applicant type
		if (obj instanceof Applicant) {
			Applicant person = (Applicant) obj;
			if (person.getApplicantGPA() != this.getApplicantGPA()) { // compare the two GPA
				return false;
			} else if (!person.getApplicantName().equals(this.getApplicantName())) { // compare the two object's name
				return false;
			} else if (!person.getApplicantCollege().equals(this.getApplicantCollege())) {// compare colleges
				return false;
			} else if (person.getApplicantSkills().length != this.getApplicantSkills().length) { // compare how many
																									// skills
				return false;
			} else if (person.getCompanyName().length != this.getCompanyName().length) {// compare how many companies
				return false;
			} else {
				for (int i = 0; i < person.getCompanyName().length; i++) { // check individual companies
					if (!person.getCompanyName()[i].equals(this.getCompanyName()[i])) {
						return false;
					}
				}
				for (int i = 0; i < person.getApplicantSkills().length; i++) { // check individual skills
					if (!person.getApplicantSkills()[i].equals(this.getApplicantSkills()[i])) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * creates a string to return the applicant's informations
	 * 
	 * @return a String that has applicant's name, gpa, college, name of companies,
	 *         skills.
	 */
	public String toString() {
		String cName = "";
		for (int i = 0; i < companyName.length; i++) {
			if (i == companyName.length - 1) {
				cName += companyName[i];
			} else {
				cName += companyName[i] + ", ";
			}
		}
		String aSkills = "";
		for (int i = 0; i < applicantSkills.length; i++) {
			if (i == applicantSkills.length - 1) {
				aSkills += applicantSkills[i];
			} else {
				aSkills += applicantSkills[i] + ", ";
			}
		}
		String test = String.format("%-33s%-16s%-11s%-17s%-5s", cName, this.getApplicantName(), this.getApplicantGPA(),
				this.getApplicantCollege(), aSkills);
		return test;
	}
}

class NegativeGPAException extends Exception { // custom class to catch the negative GPA
	NegativeGPAException() {
		System.out.println("Negative GPA Error");
	}
}
