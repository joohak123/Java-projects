/**
 * 
 * @author Joohak Lee 108699180 Recitation 1
 * 
 *         <code>HiringTable </code> that is used for <code>HiringTable</code>,
 *         this classs implements codes from class <code>Applicant</code>
 *         HiringTable stores the list of the APplicant objects in an array.
 **/
public class HiringTable implements Cloneable {
	private Applicant[] data;
	public final static int MAX_SKILLS = 3; // the number of skills that applicant can put in
	public final static int MAX_COMPANIES = 3; // the number of companies that applicant can put in
	public final static int MAX_APPLICANTS = 50; // the maximum number of applicants in the list

	/**
	 * Constructs an instance of the
	 * <dd><code>HiringTable</code> with no
	 * <dd><code>Applicants</code> objects in it.
	 * 
	 * <dl>
	 * <dt><b>Post condition:<b>
	 * <dt>
	 * <dd><code>HiringTable</code> has been initialized to be an empty list of
	 * Applicants.
	 * </dl>
	 */
	public HiringTable() {
		this.data = new Applicant[0];
	}

	/**
	 * retrieve the number of applicants in the list.
	 * 
	 * <dl>
	 * <dt><b>Pre condition:<b>
	 * <dt>The
	 * <dd><code>HiringTable</code> has been instantiated.
	 * 
	 * @return The number of
	 *         <dd><code>Applicants</code> objects in this
	 *         <dd><code>HiringTable</code>.
	 */
	public int size() {
		return data.length;
	}

	/**
	 * adds the newly created applicant to the list.
	 * 
	 * @param newApplicant an Applicant object with sets of informations.
	 * 
	 *                     <dt>Preconditions: This
	 *                     <dd><code>Applicant</code> object has been instantiated
	 *                     and the number of applicants in the
	 *                     <dd><code>HiringTable</code> is less than the
	 *                     MAX_APPLICANTS.
	 * 
	 *                     <dt>Postconditions: The new
	 *                     <dd><code>Applicant</code> is now inserted at the end of
	 *                     the list.
	 * 
	 * @throws FullTableException indicates that there is no more room in the
	 *                            <dd><code>HiringTable</code> for new
	 *                            <dd><code>applicants</code>.
	 */
	public void addApplicant(Applicant newApplicant) throws FullTableException {
		if (data.length >= MAX_APPLICANTS) { // the maximum number of applicants can't be greater than 50.
			throw new FullTableException();
		}
		Applicant[] temporary = new Applicant[data.length + 1];
		for (int i = 0; i < data.length; i++) {
			temporary[i] = data[i];
		}
		temporary[data.length] = newApplicant;
		data = temporary;
	}

	/**
	 * remove the applicant from the list with the same name as the parameter.
	 * 
	 * <dt>Preconditions: The
	 * <dd><code>HiringTable</code> has been intantiated.
	 * 
	 * <dt>Postconditions: The
	 * <dd><code>Applicant</code> with the name given has been removed from the
	 * list.
	 * 
	 * @param name String that is the name of the applicant.
	 * 
	 * @throws ApplicantNotFoundException thrown when the parameter is not equal to
	 *                                    the name of the applicants in the list.
	 */
	public void removeApplicant(String name) throws ApplicantNotFoundException {
		if (data.length == 0) {
			throw new ApplicantNotFoundException();
		}
		try {
			Applicant currentApp;
			Applicant[] temporary = new Applicant[data.length - 1];
			int a = 0;
			for (int i = 0; i < data.length; i++) {
				currentApp = data[i];
				if (!currentApp.getApplicantName().equals(name)) {
					temporary[a] = currentApp;
					a++;
				}
			}
			data = temporary;
		} catch (IndexOutOfBoundsException e) {
			throw new ApplicantNotFoundException();
		}
	}

	/**
	 * retrieve the
	 * <dd><code>Applicant</code> with the same name as the parameter.
	 * 
	 * @param name name- name of the
	 *             <dd><code>Applicant</code> to retrieve.
	 * 
	 *             <dt>Preconditions: the
	 *             <dd><code>HiringTable</code> object has been instantiated.
	 * 
	 * @return the
	 *         <dd><code>Applicant</code> with the corresponding name.
	 * 
	 * @throws ApplicantNotFoundException indicates that the applicant with the
	 *                                    given name was not found.
	 */
	public Applicant getApplicant(String name) throws ApplicantNotFoundException {
		if (data.length == 0) {
			throw new ApplicantNotFoundException();
		}
		Applicant currentApp;
		for (int i = 0; i < data.length; i++) {
			currentApp = data[i];
			if (currentApp.getApplicantName().equals(name)) {
				return currentApp;
			}
		}
		throw new ApplicantNotFoundException();
	}

	/**
	 * retrieve the applicant's information.
	 * 
	 * @return the
	 *         <dd><code>Applicant</code>'s information.
	 */
	public Applicant[] getData() {
		return data;
	}

	/**
	 * set the
	 * <dd><code>Applicant</code>'s information with new information.
	 * 
	 * @param data Applicant that is going to replace the
	 *             <dd><code>Applicant</code>.
	 */
	public void setData(Applicant[] data) {
		this.data = data;
	}

	/**
	 * Prints all the
	 * <dd><code>Applicant</code> objects that match the requested criteria.
	 * 
	 * @param table   the list of applicants to search in
	 * 
	 * @param company The company must be in the Applicant's application
	 * 
	 * @param skill   The skill that must be in the Applicant's application
	 * 
	 * @param college The college that must be in the Applicant's application
	 * 
	 * @param GPA     The minimum GPA that must be in the Applicant's application
	 * 
	 *                <dt>Precondtions: The
	 *                <dd><code>HiringTable</code> object has been instantiated.
	 * 
	 *                <dt>Postconditions: Displays a neatly formatted table of each
	 *                Applicant filtered from the
	 *                <dd><code>HiringTable</code>.
	 */
	public static void refineSearch(HiringTable table, String company, String skill, String college, double GPA) {
		if (table == null) {
			System.out.println("Null table");
			return;
		}
		System.out.println(
				String.format("%-33s%-16s%-11s%-17s%-5s", "Company Name", "Applicant", "GPA", "College", "Skills"));
		System.out.println(
				"--------------------------------------------------------------------------------------------------");
		for (Applicant person : table.getData()) {
			if (!college.equals("") && !person.getApplicantCollege().equals(college)) {
				continue;
			}
			if (person.getApplicantGPA() < GPA) {
				continue;
			}
			boolean notFound = true;
			for (int i = 0; i < person.getCompanyName().length; i++) {
				if (person.getCompanyName()[i].equals(company)) {
					notFound = false;
					break;
				}
			}
			if (!company.equals("") && notFound == true) {
				continue;
			}
			notFound = true;
			for (int i = 0; i < person.getApplicantSkills().length; i++) {
				if (person.getApplicantSkills()[i].equals(skill)) {
					notFound = false;
					break;
				}
			}
			if (!skill.equals("") && notFound == true) {
				continue;
			}
			System.out.println(person);
		}
	}

	/**
	 * Creates a copy of this
	 * <dd><code>HiringTable</code>. Any changes done on the cloned
	 * <dd><code>HiringTable</code> or the original will not affect one another.
	 * 
	 * <dt>Preconditions: This
	 * <dd><code>HiringTable</code> has been instantiated.
	 * 
	 * @returns A copy of this
	 *          <dd><code>HiringTable</code> object.
	 */
	public Object clone() {
		HiringTable answer = new HiringTable();
		Applicant[] temporary = new Applicant[data.length];
		for (int i = 0; i < data.length; i++) {
			temporary[i] = (Applicant) data[i].clone();
		}
		answer.setData(temporary);
		return answer;
	}

	/**
	 * tests whether the two
	 * <dd><code>HiringTable</code> are same or not.
	 * 
	 * @return if the two
	 *         <dd><code>HiringTable</code> are same returns true, otherwise false.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof HiringTable) {
			HiringTable test = (HiringTable) obj;
			if (test.size() != this.size()) {
				return false;
			} else {
				for (int i = 0; i < this.size(); i++) {
					if (!this.getData()[i].equals(test.getData()[i])) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Prints a neatly formatted table of each item in the list as shown in the
	 * sample output.
	 * 
	 * <dt>Preconditions: The
	 * <dd><code>HiringTable</code> has been instantiated.
	 * 
	 * <dt>Postconditions: Displays a eatly formatted table of each
	 * <dd><code>Applicant</code> from the
	 * <dd><code>HiringTable</code>.
	 */
	public void printApplicantTable() {
		System.out.println(
				String.format("%-33s%-16s%-11s%-17s%-5s", "Company Name", "Applicant", "GPA", "College", "Skills"));
		System.out.println(
				"--------------------------------------------------------------------------------------------------");
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}
}

class FullTableException extends Exception {
	FullTableException() {
	}
}

class ApplicantNotFoundException extends Exception {
	ApplicantNotFoundException() {
	}
}