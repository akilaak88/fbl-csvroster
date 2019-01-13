package com.fastbridge.assignment.csvroster.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="state_id")
	private String stateId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="birthdate")
	private Date birthdate;
	
	@Column(name="race")
	private String race;
	
	@Column(name="meal_status")
	private String mealStatus;
	
	@Column(name="english_proficiency")
	private String englishProficiency;
	
	@Column(name="native_language")
	private String nativeLanguage;
	
	@Column(name="service_code")
	private String serviceCode;
	
	@Column(name="primary_disability_type")
	private String primaryDisabilityType;
	
	@Column(name="iep_reading")
	private  boolean iepReading;
	
	@Column(name="iep_math")
	private  boolean iepMath;
	
	@Column(name="iep_behavior")
	private  boolean iepBehavior;
	
	@Column(name="gifted_talented")
	private  boolean giftedTalented;
	
	@Column(name="section504")
	private  boolean section504;
	
	@Column(name="mobility")
	private  boolean mobility;
	
	public Student() {
		
	}

	public Student(String stateId, String firstName, String lastName, String gender, Date birthdate, String race,
			String mealStatus, String englishProficiency, String nativeLanguage, String serviceCode,
			String primaryDisabilityType, boolean iepReading, boolean iepMath, boolean iepBehavior,
			boolean giftedTalented, boolean section504, boolean mobility) {
		this.stateId = stateId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.race = race;
		this.mealStatus = mealStatus;
		this.englishProficiency = englishProficiency;
		this.nativeLanguage = nativeLanguage;
		this.serviceCode = serviceCode;
		this.primaryDisabilityType = primaryDisabilityType;
		this.iepReading = iepReading;
		this.iepMath = iepMath;
		this.iepBehavior = iepBehavior;
		this.giftedTalented = giftedTalented;
		this.section504 = section504;
		this.mobility = mobility;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getMealStatus() {
		return mealStatus;
	}

	public void setMealStatus(String mealStatus) {
		this.mealStatus = mealStatus;
	}

	public String getEnglishProficiency() {
		return englishProficiency;
	}

	public void setEnglishProficiency(String englishProficiency) {
		this.englishProficiency = englishProficiency;
	}

	public String getNativeLanguage() {
		return nativeLanguage;
	}

	public void setNativeLanguage(String nativeLanguage) {
		this.nativeLanguage = nativeLanguage;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getPrimaryDisabilityType() {
		return primaryDisabilityType;
	}

	public void setPrimaryDisabilityType(String primaryDisabilityType) {
		this.primaryDisabilityType = primaryDisabilityType;
	}

	public boolean isIepReading() {
		return iepReading;
	}

	public void setIepReading(boolean iepReading) {
		this.iepReading = iepReading;
	}

	public boolean isIepMath() {
		return iepMath;
	}

	public void setIepMath(boolean iepMath) {
		this.iepMath = iepMath;
	}

	public boolean isIepBehavior() {
		return iepBehavior;
	}

	public void setIepBehavior(boolean iepBehavior) {
		this.iepBehavior = iepBehavior;
	}

	public boolean isGiftedTalented() {
		return giftedTalented;
	}

	public void setGiftedTalented(boolean giftedTalented) {
		this.giftedTalented = giftedTalented;
	}

	public boolean isSection504() {
		return section504;
	}

	public void setSection504(boolean section504) {
		this.section504 = section504;
	}

	public boolean isMobility() {
		return mobility;
	}

	public void setMobility(boolean mobility) {
		this.mobility = mobility;
	}

	
}
