package com.kanglong.dao;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("PSGAPI")
public class Api {

    /**
     * id: 主键
     * 
     */
    private Long id;

    /**
     * insertTime: 操作时间 默认为操作记录时系统时间, 对应数据库字段为:INSERT_TIME
     */
    private Date insertTime = new Date();

    /**
     * updateTime: 更新时间 默认为更新记录时系统时间, 对应数据库字段为:UPDATE_TIME
     */
    private Date updateTime = new Date();

    /**
     * isValid:记录有效性,默认值为1
     */
    private Integer isValid = 1;
	 /**
     * psgId:旅客ID.
     * 
     */
    private Long psgId;
    /**
     * lastName:姓.
     * 
     */
    private String lastName;
    /**
     * firstName:名
     * 
     */
    private String firstName;
    /**
     * middleName:中间名
     * 
     */
    private String middleName;
    /**
     * gender:性别
     * 
     */
    private String gender;
    /**
     * placeOfBirth:出生地
     * 
     */
    private String placeOfBirth;
    /**
     * dateOfBirth:出生日期
     * 
     */
    private String dateOfBirth;
    /**
     * nationality:国籍
     * 
     */
    private String nationality;
    /**
     * countryOfResidence:居住国家
     * 
     */
    private String countryOfResidence;
    /**
     * inTransitPsgFlag:是否In－Transit
     * 
     */
    private String inTransitPsgFlag;
    /**
     * psptNo:护照号
     * 
     */
    private String psptNo;
    /**
     * psptType:护照类型
     * 
     */
    private String psptType;
    /**
     * psptIssueDate:护照发布日期
     * 
     */
    private String psptIssueDate;
    /**
     * psptExpiryDate:护照有效期截止日期
     * 
     */
    private String psptExpiryDate;
    /**
     * psptIssueCountry:护照发布国家
     * 
     */
    private String psptIssueCountry;
    /**
     * primaryPassportHolder:API_PRIMARY_PASSPORT_HOLDER
     * 
     */
    private String primaryPassportHolder;
    /**
     * redressNo:补号
     * 
     */
    private String redressNo;
    /**
     * knownTravelerNo:公众旅客号
     * 
     */
    private String knownTravelerNo;
    /**
     * visaNo:签证号
     * 
     */
    private String visaNo;
    /**
     * visaType:签证类型
     * 
     */
    private String visaType;
    /**
     * visaIssueDate:签证发布日期
     * 
     */
    private String visaIssueDate;
    /**
     * visaExpiryDate:签证有效期截止日期
     * 
     */
    private String visaExpiryDate;
    /**
     * visaIssuePlace:签证发布地点
     * 
     */
    private String visaIssuePlace;
    /**
     * otherNo:其他证件号
     * 
     */
    private String otherNo;
    /**
     * otherType:其他证件类型
     * 
     */
    private String otherType;
    /**
     * otherIssueDate:其他证件发布日期
     * 
     */
    private String otherIssueDate;
    /**
     * otherExpiryDate:其他证件有效期截止日期
     * 
     */
    private String otherExpiryDate;
    /**
     * otherIssuePlace:其他证件发布地点
     * 
     */
    private String otherIssuePlace;
    /**
     * homeAddress:家庭地址
     * 
     */
    private String homeAddress;
    /**
     * homeCity:家庭地址的城市
     * 
     */
    private String homeCity;
    /**
     * homeState:家庭地址的州
     * 
     */
    private String homeState;
    /**
     * homeCountry:家庭地址的国家
     * 
     */
    private String homeCountry;
    /**
     * homePostalCode:家庭地址的邮政编码
     * 
     */
    private String homePostalCode;
    /**
     * homeContactPhone:家庭地址的联系电话
     * 
     */
    private String homeContactPhone;
    /**
     * destAddress:目的地址
     * 
     */
    private String destAddress;
    /**
     * destCity:目的地址的城市
     * 
     */
    private String destCity;
    /**
     * destState:目的地址的州
     * 
     */
    private String destState;
    /**
     * destCountry:目的地址的国家
     * 
     */
    private String destCountry;
    /**
     * destPostalCode:目的地址的邮政编码
     * 
     */
    private String destPostalCode;
    /**
     * destContactPhone:目的地址的联系电话
     * 
     */
    private String destContactPhone;

    /**
     * dataSource: 数据来源：0为PNL，1为DIP。 对应数据库字段:DATA_SOURCE
     */
    private Integer dataSource;
    
    /**
     * visaApplicableCountry:签证适用国家.
     * 
     */
    private String visaApplicableCountry;
    
    /**
     * otherApplicableCountry:其他证件适用国家.
     * 
     */
    private String otherApplicableCountry;
    /**
     * isManualEntry:是否手工输入(1为手工输入，null或其他暂定为非手工输入）
     */
    private Integer isManualEntry;
    
	public Long getPsgId() {
		return psgId;
	}
	public void setPsgId(Long psgId) {
		this.psgId = psgId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getCountryOfResidence() {
		return countryOfResidence;
	}
	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}
	public String getInTransitPsgFlag() {
		return inTransitPsgFlag;
	}
	public void setInTransitPsgFlag(String inTransitPsgFlag) {
		this.inTransitPsgFlag = inTransitPsgFlag;
	}
	public String getPsptNo() {
		return psptNo;
	}
	public void setPsptNo(String psptNo) {
		this.psptNo = psptNo;
	}
	public String getPsptType() {
		return psptType;
	}
	public void setPsptType(String psptType) {
		this.psptType = psptType;
	}
	public String getPsptIssueDate() {
		return psptIssueDate;
	}
	public void setPsptIssueDate(String psptIssueDate) {
		this.psptIssueDate = psptIssueDate;
	}
	public String getPsptExpiryDate() {
		return psptExpiryDate;
	}
	public void setPsptExpiryDate(String psptExpiryDate) {
		this.psptExpiryDate = psptExpiryDate;
	}
	public String getPsptIssueCountry() {
		return psptIssueCountry;
	}
	public void setPsptIssueCountry(String psptIssueCountry) {
		this.psptIssueCountry = psptIssueCountry;
	}
	public String getPrimaryPassportHolder() {
		return primaryPassportHolder;
	}
	public void setPrimaryPassportHolder(String primaryPassportHolder) {
		this.primaryPassportHolder = primaryPassportHolder;
	}
	public String getRedressNo() {
		return redressNo;
	}
	public void setRedressNo(String redressNo) {
		this.redressNo = redressNo;
	}
	public String getKnownTravelerNo() {
		return knownTravelerNo;
	}
	public void setKnownTravelerNo(String knownTravelerNo) {
		this.knownTravelerNo = knownTravelerNo;
	}
	public String getVisaNo() {
		return visaNo;
	}
	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public String getVisaIssueDate() {
		return visaIssueDate;
	}
	public void setVisaIssueDate(String visaIssueDate) {
		this.visaIssueDate = visaIssueDate;
	}
	public String getVisaExpiryDate() {
		return visaExpiryDate;
	}
	public void setVisaExpiryDate(String visaExpiryDate) {
		this.visaExpiryDate = visaExpiryDate;
	}
	public String getVisaIssuePlace() {
		return visaIssuePlace;
	}
	public void setVisaIssuePlace(String visaIssuePlace) {
		this.visaIssuePlace = visaIssuePlace;
	}
	public String getOtherNo() {
		return otherNo;
	}
	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}
	public String getOtherType() {
		return otherType;
	}
	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}
	public String getOtherIssueDate() {
		return otherIssueDate;
	}
	public void setOtherIssueDate(String otherIssueDate) {
		this.otherIssueDate = otherIssueDate;
	}
	public String getOtherExpiryDate() {
		return otherExpiryDate;
	}
	public void setOtherExpiryDate(String otherExpiryDate) {
		this.otherExpiryDate = otherExpiryDate;
	}
	public String getOtherIssuePlace() {
		return otherIssuePlace;
	}
	public void setOtherIssuePlace(String otherIssuePlace) {
		this.otherIssuePlace = otherIssuePlace;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getHomeCity() {
		return homeCity;
	}
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}
	public String getHomeState() {
		return homeState;
	}
	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}
	public String getHomeCountry() {
		return homeCountry;
	}
	public void setHomeCountry(String homeCountry) {
		this.homeCountry = homeCountry;
	}
	public String getHomePostalCode() {
		return homePostalCode;
	}
	public void setHomePostalCode(String homePostalCode) {
		this.homePostalCode = homePostalCode;
	}
	public String getHomeContactPhone() {
		return homeContactPhone;
	}
	public void setHomeContactPhone(String homeContactPhone) {
		this.homeContactPhone = homeContactPhone;
	}
	public String getDestAddress() {
		return destAddress;
	}
	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}
	public String getDestCity() {
		return destCity;
	}
	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}
	public String getDestState() {
		return destState;
	}
	public void setDestState(String destState) {
		this.destState = destState;
	}
	public String getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}
	public String getDestPostalCode() {
		return destPostalCode;
	}
	public void setDestPostalCode(String destPostalCode) {
		this.destPostalCode = destPostalCode;
	}
	public String getDestContactPhone() {
		return destContactPhone;
	}
	public void setDestContactPhone(String destContactPhone) {
		this.destContactPhone = destContactPhone;
	}
	public Integer getDataSource() {
		return dataSource;
	}
	public void setDataSource(Integer dataSource) {
		this.dataSource = dataSource;
	}
	public String getVisaApplicableCountry() {
		return visaApplicableCountry;
	}
	public void setVisaApplicableCountry(String visaApplicableCountry) {
		this.visaApplicableCountry = visaApplicableCountry;
	}
	public String getOtherApplicableCountry() {
		return otherApplicableCountry;
	}
	public void setOtherApplicableCountry(String otherApplicableCountry) {
		this.otherApplicableCountry = otherApplicableCountry;
	}
	public Integer getIsManualEntry() {
		return isManualEntry;
	}
	public void setIsManualEntry(Integer isManualEntry) {
		this.isManualEntry = isManualEntry;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
}
