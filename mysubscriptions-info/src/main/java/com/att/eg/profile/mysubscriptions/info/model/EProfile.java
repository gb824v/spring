package com.att.eg.profile.mysubscriptions.info.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.att.ajsc.common.couchbase.model.Id;

public class EProfile {
	
	@Id
    private String profileID;
	private String profileType;
	private String accountToken;
	private String accountStatus;
	private String zipCode;
	private String packageCode;
	private String pkgCode;
	private String friendlyName;
	private String avatar;
	private String lastModifiedDate;

	public EProfile() {
		super();
	}

	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}

	public String getProfileType() {
		return profileType;
	}

	public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public String getPkgCode() {
		return pkgCode;
	}

	public void setPkgCode(String pkgCode) {
		this.pkgCode = pkgCode;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
					.append("Profile ID", profileID)
					.append("Profile type", profileType)
					.append("Account token", accountToken)
					.append("Account status", accountStatus)
					.append("Zip code", zipCode)
					.append("Package code", packageCode)
					.append("PkgCode", pkgCode)
					.append("Friendly name", friendlyName)
					.append("Avatar", avatar)
					.toString();
	}
}
