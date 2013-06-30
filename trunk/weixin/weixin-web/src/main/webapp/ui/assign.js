/*
 * 给主卡赋值
 */
function assign(data){
	var basicAcdNewapplication = data.acdNewapplication;
	var acdAppidinfo1 = data.acdAppidInfoList[0];
	var homeAddressData = data.acdAppaddressDataList[0];
	var companyAddressData = data.acdAppaddressDataList[1];
	var otherAddressData = "";
	if(data.acdAppaddressDataList.length>2)
		otherAddressData = data.acdAppaddressDataList[2];
	var relativesContactData = data.acdAppcontactDataList[0];
	var otherContactData = data.acdAppcontactDataList[1];
	var acdAppemployinfo1 = data.acdAppemployInfo;
	
	
	$("#expiryDate").val(cutDate(acdAppidinfo1.expiryDate));
	//alert(data.acdAppidInfoList[0].expiryDate);
	//alert(data.acdNewapplication.fullname);
	$("#b_chinese").val(basicAcdNewapplication.fullname);
	$("#basicIsLongEffective").val(acdAppidinfo1.longEffectiveInd);
	$("#aliasName").val(basicAcdNewapplication.embossname);
	$("#basicSex").val(basicAcdNewapplication.sex);
	$("#basicAcdNewapplication_maritalStatusId").val(basicAcdNewapplication.maritalStatusId);
	$("#noOfDependents").val(basicAcdNewapplication.noOfDependents);
	$("#basicAcdNewapplication_educationId").val(basicAcdNewapplication.educationId);
	$("#basicHourseCondition").val(basicAcdNewapplication.houseCondition);
	$("#basicHourseArea").val(basicAcdNewapplication.houseArea);
	$("#repaymentAmountPerMonth").val(basicAcdNewapplication.repaymentAmountPerMonth);
	$("#monthlyRentAmount").val(basicAcdNewapplication.monthlyRentAmount);
	$("#basicAcdNewapplication_custclassId").val(basicAcdNewapplication.custclassId);
	$("#stateHouse").val(homeAddressData.stateName);
	$("#cityHouse").val(homeAddressData.cityName);
	$("#addressLine").val(homeAddressData.addressLine);
	$("#addressPostcode").val(homeAddressData.addressPostcode);
	$("#nm_mobile_no").val(basicAcdNewapplication.nmMobileNo);
	$("#addressPhone1").val(homeAddressData.addressPhone1);
	$("#nm_email_address").val(basicAcdNewapplication.nmEmailAddress);
	$("#basicAddressFaxno").val(homeAddressData.addressFaxno);
	$("#basicHasBankProduct").val(basicAcdNewapplication.hasBankProduct);
	$("#basicStaffInd").val(basicAcdNewapplication.staffInd);
	
	
	$("#b_companyName").val(acdAppemployinfo1.companyName);
	$("#b_department").val(acdAppemployinfo1.department);
	$("#basicAcdNewapplication_positionId").val(basicAcdNewapplication.positionId);
	$("#basicAcdNewapplication_proflTitleId").val(basicAcdNewapplication.proflTitleId);
	$("#acdAppemployinfo1_businessSic").val(acdAppemployinfo1.businessSic);
	$("#acdAppemployinfo1_companyClassId").val(acdAppemployinfo1.companyClassId);
	$("#serverYear").val(acdAppemployinfo1.serviceYears);
	$("#basicAcdNewapplication_incomeLevelId").val(basicAcdNewapplication.incomeLevelId);
	$("#basicAcdNewapplication_familyIncomeLevelId").val(basicAcdNewapplication.familyIncomeLevelId);
	$("#companyFax").val(acdAppemployinfo1.companyFaxNo);
	$("#companyPhone").val(acdAppemployinfo1.companyContactPhone);
	$("#vocation_postCode").val(companyAddressData.addressPostcode);
	$("#stateName").val(companyAddressData.stateName);
	$("#cityName").val(companyAddressData.cityName);
	$("#addressLineC").val(companyAddressData.addressLine);
	
	
	$("#r_contactName").val(relativesContactData.contactName);
	$("#relativesContactData_relateToCustomer").val(relativesContactData.relateToCustomer);
	$("#r_telNo").val(relativesContactData.telNo);
	$("#r_mobileNo").val(relativesContactData.mobileNo);
	$("#r_homeAddr").val(relativesContactData.homeAddr);
	
	
	$("#o_contactName").val(otherContactData.contactName);
	$("#otherContactData.relateToCustomer").val(otherContactData.relateToCustomer);
	$("#o_telNo").val(otherContactData.telNo);
	$("#o_mobileNo").val(otherContactData.mobileNo);
	$("#o_homeAddr").val(otherContactData.homeAddr);
	
	
	$("#b_localAutopayInd").val(basicAcdNewapplication.localAutopayAccvalidInd);
	$("#b_localAutopay_accNo").val(basicAcdNewapplication.localAutopayAccno);
	$("#basicStatMedia").val(basicAcdNewapplication.statMedia);
	$("#basicBillAddress").val(basicAcdNewapplication.billAddressId);
	$("#basicStatCombinationInd").val(basicAcdNewapplication.statCombinationInd);
	$("#basicTrxnAlertMedia").val(basicAcdNewapplication.trxnAlertMedia);
	$("#basicBillAddress").val(otherAddressData.addressLine);
	$("#basicBillAddressZipCode").val(otherAddressData.addressPostcode);
	$("#basicOverLimitInd").val(basicAcdNewapplication.overLimitInd);
	$("#basicOverLimitPercent").val(basicAcdNewapplication.overLimitPercent);

	
	$("#b_cardCollectionCd").val(basicAcdNewapplication.cardCollectionCd);
	$("#b_deliveryBranchId").val(basicAcdNewapplication.deliveryBranchId);
	$("#baiscCardDeliveryAddress").val(basicAcdNewapplication.cardDeliveryAddressId);
	$("#basicAcdNewapplication_recommenderType").val(basicAcdNewapplication.recommenderType);
	$("#basicRecomCardName").val(basicAcdNewapplication.recomCardName);
	$("#basicRecomCardNo").val(basicAcdNewapplication.recomCardNo);
}


function suppAssign(data){
	var suppAcdNewapplication = data.acdNewapplication;
	var acdAppidinfo3 = data.acdAppidInfoList[0];
	var suppHomeAddressData = data.acdAppaddressDataList[0];
	var suppCompanyAddressData = data.acdAppaddressDataList[1];
	var suppOtherAddressData = "";
	if(data.acdAppaddressDataList.length>2)
		suppOtherAddressData = data.acdAppaddressDataList[2];
	var relativesContactData = data.acdAppcontactDataList[0];
	var otherContactData = data.acdAppcontactDataList[1];
	var acdAppemployinfo2 = data.acdAppemployInfo;
	
	$("#suppIsLongEffective").val(acdAppidinfo3.longEffectiveInd);
	
  	$("#expiryDate3").val(cutDate(acdAppidinfo3.expiryDate));
  	$("#s_chinese").val(suppAcdNewapplication.fullname);
  	$("#aliasName1").val(suppAcdNewapplication.embossname);
  	
  	$("#suppSex").val(suppAcdNewapplication.sex);
  	$("#dateOfBorn1").val(cutDate(suppAcdNewapplication.dateOfBorn));
  	$("#suppAcdNewapplication_educationId").val(suppAcdNewapplication.educationId);
  	$("#suppStateHouse").val(suppHomeAddressData.stateName);
  	$("#suppCityHouse").val(suppHomeAddressData.cityName);
  	$("#suppAddressLine").val(suppHomeAddressData.addressLine);
  	$("#suppAcdNewapplication_nationCode").val(suppAcdNewapplication.nationCode);
  	$("#supp_originationStateId").val(suppAcdNewapplication.originationStateId);
  	$("#suppAcdNewapplication_countryAlphaId").val(suppAcdNewapplication.countryAlphaId);
  	$("#suppAcdNewapplication_relationToBasic").val(suppAcdNewapplication.relationToBasic);
  	$("#homeAddressPhone").val(suppHomeAddressData.addressPhone1);
  	$("#s_nmMobileNo").val(suppAcdNewapplication.nmMobileNo);
  	
  	$("#suppNm_email_address").val(suppAcdNewapplication.nmEmailAddress);
  	$("#s_companyName").val(acdAppemployinfo2.companyName);
  	$("#companyContactPhone").val(acdAppemployinfo2.companyContactPhone);
  	$("#suppStateName").val(suppCompanyAddressData.stateName);
  	$("#suppCityName").val(suppCompanyAddressData.cityName);
  	$("#suppAddressLineC").val(suppCompanyAddressData.addressLine);
  	
  	
  	$("#s_cardCollectionCd").val(suppAcdNewapplication.cardCollectionCd);
  	$("#s_deliveryBranchId").val(suppAcdNewapplication.deliveryBranchId);
  	$("#suppCardDeliveryAddress").val(suppAcdNewapplication.cardDeliveryAddressId);
  	
  	$("#suppAcdNewapplication_recommenderType").val(suppAcdNewapplication.recommenderType);
  	$("#suppRecomCardName").val(suppAcdNewapplication.recomCardName);
  	$("#suppRecomCardNo").val(suppAcdNewapplication.recomCardNo);
  	$("#suppTrxnAlertMedia").val(suppAcdNewapplication.trxnAlertMedia);
}