package com.xs.enetity;


/**
 * javabean 申请单信息表
 * <p> 创建日期：2020-04-19 </p>
 * @author s_xd@winning.com.cn
 */
public class Lis_ApplySheetList_Mdl {
    private String sheetno;

    private String hospitalcode;

    private String subsyscode;

    private String hisapplyno;

    private String patientid;

    private String cureno;

    private String cardtype;

    private String cardno;

    private String hospno;

    private String invoiceno;

    private String patname;

    private String imecode;

    private String sex;

    private String sexdesc;

    private String age;

    private String ageunit;

    private String age2;

    private String ageunit2;

    private String agedesc;

    private String chargetype;

    private String chargetypename;

    private String wardorreg;

    private String wardorregname;

    private String brlx;

    private String ward;

    private String wardname;

    private String bedno;

    private String yebz;

    private String applydeptcode;

    private String applydeptname;

    private String applydoccode;

    private String applydocname;

    private String patpropcode;

    private String patpropname;

    private String idnum;

    private String address;

    private String birthday;

    private String pattype;

    private String pattypehint;
    private String clinicdesc;

    private String m_phone;

    private String remark;

    private String diseasecode;

    private String diseasename;

    private String abotype;

    private String rhdtype;

    private String serumantibody;

    private String recipientarea;

    private String doctormemo;

    private String transfusionhistory;

    private String reactionhistory;

    private String allergyhistory;

    private String allergyname;

    private String pregnancyhistory;

    private String gestationnum;

    private String productionnum;

    private String abortionnum;

    private String livenum;

    private String transplanthistory;

    private String donorblood;

    private String bloodtime;

    private String bloodpurpose;

    private String tbloodnature;

    private String operationname;

    private String operationlevel;

    private String bloodplace;

    private String informedstatus;

    private String unassignreason;

    private String applysheettype;

    private String applysheetname;

    private String applytime;

    private String applyamount;

    private String execdept;

    private String execdeptname;

    private String applysheettxm;

    private String labtxm;

    private String orderno;

    private String approvaltime;

    private String approvalflag;

    private String preparationno;

    private String sheetstatus;

    private String receivetime;

    private String receivecode;

    private String receivename;

    private String labtxmprinttime;

    private String labtxmprintcode;

    private String labtxmprintname;

    private String sheetregtime;

    private String sheetregcode;

    private String sheetregname;

    private String confirmtime;

    private String confirmcode;

    private String confirmname;

    private String confirmcomment;

    private String mjzbz;

    private String labmatchflag;

    private String times;

    private String yexh;

    private String labtxmprintcheckcode;

    private String labtxmprintcheckname;

    private String timetemp;

    public String getSheetno() {
        return sheetno;
    }


    public String getLabtxmprintcheckcode() {
        return labtxmprintcheckcode;
    }

    public void setLabtxmprintcheckcode(String labtxmprintcheckcode) {
        this.labtxmprintcheckcode = labtxmprintcheckcode;
    }

    public String getLabtxmprintcheckname() {
        return labtxmprintcheckname;
    }

    public void setLabtxmprintcheckname(String labtxmprintcheckname) {
        this.labtxmprintcheckname = labtxmprintcheckname;
    }

    public void setSheetno(String sheetno ) {
        if(sheetno == null || sheetno.equals("")){
            this.sheetno = null;
            return;
        }
        this.sheetno = sheetno;
    }

    public String getHospitalcode() {
        return hospitalcode;
    }

    public void setHospitalcode( String hospitalcode ) {
        if(hospitalcode == null || hospitalcode.equals("")){
            this.hospitalcode = null;
            return;
        }
        this.hospitalcode = hospitalcode;
    }

    public String getHisapplyno() {
        return hisapplyno;
    }

    public void setHisapplyno( String hisapplyno ) {
        if(hisapplyno == null || hisapplyno.equals("")){
            this.hisapplyno = null;
            return;
        }
        this.hisapplyno = hisapplyno;
    }

    @Override
    public String toString() {
        return "Lis_ApplySheetList_Mdl{" +
                "sheetno='" + sheetno + '\'' +
                ", hospitalcode='" + hospitalcode + '\'' +
                ", subsyscode='" + subsyscode + '\'' +
                ", hisapplyno='" + hisapplyno + '\'' +
                ", patientid='" + patientid + '\'' +
                ", cureno='" + cureno + '\'' +
                ", cardtype='" + cardtype + '\'' +
                ", cardno='" + cardno + '\'' +
                ", hospno='" + hospno + '\'' +
                ", invoiceno='" + invoiceno + '\'' +
                ", patname='" + patname + '\'' +
                ", imecode='" + imecode + '\'' +
                ", sex='" + sex + '\'' +
                ", sexdesc='" + sexdesc + '\'' +
                ", age='" + age + '\'' +
                ", ageunit='" + ageunit + '\'' +
                ", age2='" + age2 + '\'' +
                ", ageunit2='" + ageunit2 + '\'' +
                ", agedesc='" + agedesc + '\'' +
                ", chargetype='" + chargetype + '\'' +
                ", chargetypename='" + chargetypename + '\'' +
                ", wardorreg='" + wardorreg + '\'' +
                ", wardorregname='" + wardorregname + '\'' +
                ", brlx='" + brlx + '\'' +
                ", ward='" + ward + '\'' +
                ", wardname='" + wardname + '\'' +
                ", bedno='" + bedno + '\'' +
                ", yebz='" + yebz + '\'' +
                ", applydeptcode='" + applydeptcode + '\'' +
                ", applydeptname='" + applydeptname + '\'' +
                ", applydoccode='" + applydoccode + '\'' +
                ", applydocname='" + applydocname + '\'' +
                ", patpropcode='" + patpropcode + '\'' +
                ", patpropname='" + patpropname + '\'' +
                ", idnum='" + idnum + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", pattype='" + pattype + '\'' +
                ", pattypehint='" + pattypehint + '\'' +
                ", clinicdesc='" + clinicdesc + '\'' +
                ", m_phone='" + m_phone + '\'' +
                ", remark='" + remark + '\'' +
                ", diseasecode='" + diseasecode + '\'' +
                ", diseasename='" + diseasename + '\'' +
                ", abotype='" + abotype + '\'' +
                ", rhdtype='" + rhdtype + '\'' +
                ", serumantibody='" + serumantibody + '\'' +
                ", recipientarea='" + recipientarea + '\'' +
                ", doctormemo='" + doctormemo + '\'' +
                ", transfusionhistory='" + transfusionhistory + '\'' +
                ", reactionhistory='" + reactionhistory + '\'' +
                ", allergyhistory='" + allergyhistory + '\'' +
                ", allergyname='" + allergyname + '\'' +
                ", pregnancyhistory='" + pregnancyhistory + '\'' +
                ", gestationnum='" + gestationnum + '\'' +
                ", productionnum='" + productionnum + '\'' +
                ", abortionnum='" + abortionnum + '\'' +
                ", livenum='" + livenum + '\'' +
                ", transplanthistory='" + transplanthistory + '\'' +
                ", donorblood='" + donorblood + '\'' +
                ", bloodtime='" + bloodtime + '\'' +
                ", bloodpurpose='" + bloodpurpose + '\'' +
                ", tbloodnature='" + tbloodnature + '\'' +
                ", operationname='" + operationname + '\'' +
                ", operationlevel='" + operationlevel + '\'' +
                ", bloodplace='" + bloodplace + '\'' +
                ", informedstatus='" + informedstatus + '\'' +
                ", unassignreason='" + unassignreason + '\'' +
                ", applysheettype='" + applysheettype + '\'' +
                ", applysheetname='" + applysheetname + '\'' +
                ", applytime='" + applytime + '\'' +
                ", applyamount='" + applyamount + '\'' +
                ", execdept='" + execdept + '\'' +
                ", execdeptname='" + execdeptname + '\'' +
                ", applysheettxm='" + applysheettxm + '\'' +
                ", labtxm='" + labtxm + '\'' +
                ", orderno='" + orderno + '\'' +
                ", approvaltime='" + approvaltime + '\'' +
                ", approvalflag='" + approvalflag + '\'' +
                ", preparationno='" + preparationno + '\'' +
                ", sheetstatus='" + sheetstatus + '\'' +
                ", receivetime='" + receivetime + '\'' +
                ", receivecode='" + receivecode + '\'' +
                ", receivename='" + receivename + '\'' +
                ", labtxmprinttime='" + labtxmprinttime + '\'' +
                ", labtxmprintcode='" + labtxmprintcode + '\'' +
                ", labtxmprintname='" + labtxmprintname + '\'' +
                ", sheetregtime='" + sheetregtime + '\'' +
                ", sheetregcode='" + sheetregcode + '\'' +
                ", sheetregname='" + sheetregname + '\'' +
                ", confirmtime='" + confirmtime + '\'' +
                ", confirmcode='" + confirmcode + '\'' +
                ", confirmname='" + confirmname + '\'' +
                ", confirmcomment='" + confirmcomment + '\'' +
                ", mjzbz='" + mjzbz + '\'' +
                ", labmatchflag='" + labmatchflag + '\'' +
                ", times='" + times + '\'' +
                ", yexh='" + yexh + '\'' +
                ", labtxmprintcheckcode='" + labtxmprintcheckcode + '\'' +
                ", labtxmprintcheckname='" + labtxmprintcheckname + '\'' +
                ", timetemp='" + timetemp + '\'' +
                '}';
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid( String patientid ) {
        if(patientid == null || patientid.equals("")){
            this.patientid = null;
            return;
        }
        this.patientid = patientid;
    }

    public String getCureno() {
        return cureno;
    }

    public void setCureno( String cureno ) {
        if(cureno == null || cureno.equals("")){
            this.cureno = null;
            return;
        }
        this.cureno = cureno;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype( String cardtype ) {
        if(cardtype == null || cardtype.equals("")){
            this.cardtype = null;
            return;
        }
        this.cardtype = cardtype;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno( String cardno ) {
        if(cardno == null || cardno.equals("")){
            this.cardno = null;
            return;
        }
        this.cardno = cardno;
    }

    public String getHospno() {
        return hospno;
    }

    public void setHospno( String hospno ) {
        if(hospno == null || hospno.equals("")){
            this.hospno = null;
            return;
        }
        this.hospno = hospno;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno( String invoiceno ) {
        if(invoiceno == null || invoiceno.equals("")){
            this.invoiceno = null;
            return;
        }
        this.invoiceno = invoiceno;
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname( String patname ) {
        if(patname == null || patname.equals("")){
            this.patname = null;
            return;
        }
        this.patname = patname;
    }

    public String getImecode() {
        return imecode;
    }

    public void setImecode( String imecode ) {
        if(imecode == null || imecode.equals("")){
            this.imecode = null;
            return;
        }
        this.imecode = imecode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex( String sex ) {
        if(sex == null || sex.equals("")){
            this.sex = null;
            return;
        }
        this.sex = sex;
    }

    public String getSexdesc() {
        return sexdesc;
    }

    public void setSexdesc( String sexdesc ) {
        if(sexdesc == null || sexdesc.equals("")){
            this.sexdesc = null;
            return;
        }
        this.sexdesc = sexdesc;
    }

    public String getAge() {
        return age;
    }

    public void setAge( String age ) {
        if(age == null || age.equals("")){
            this.age = null;
            return;
        }
        this.age = age;
    }

    public String getAgeunit() {
        return ageunit;
    }

    public void setAgeunit( String ageunit ) {
        if(ageunit == null || ageunit.equals("")){
            this.ageunit = null;
            return;
        }
        this.ageunit = ageunit;
    }

    public String getAge2() {
        return age2;
    }

    public void setAge2( String age2 ) {
        if(age2 == null || age2.equals("")){
            this.age2 = null;
            return;
        }
        this.age2 = age2;
    }

    public String getAgeunit2() {
        return ageunit2;
    }

    public void setAgeunit2( String ageunit2 ) {
        if(ageunit2 == null || ageunit2.equals("")){
            this.ageunit2 = null;
            return;
        }
        this.ageunit2 = ageunit2;
    }

    public String getAgedesc() {
        return agedesc;
    }

    public void setAgedesc( String agedesc ) {
        if(agedesc == null || agedesc.equals("")){
            this.agedesc = null;
            return;
        }
        this.agedesc = agedesc;
    }

    public String getChargetype() {
        return chargetype;
    }

    public void setChargetype( String chargetype ) {
        if(chargetype == null || chargetype.equals("")){
            this.chargetype = null;
            return;
        }
        this.chargetype = chargetype;
    }

    public String getChargetypename() {
        return chargetypename;
    }

    public void setChargetypename( String chargetypename ) {
        if(chargetypename == null || chargetypename.equals("")){
            this.chargetypename = null;
            return;
        }
        this.chargetypename = chargetypename;
    }

    public String getWardorreg() {
        return wardorreg;
    }

    public void setWardorreg( String wardorreg ) {
        if(wardorreg == null || wardorreg.equals("")){
            this.wardorreg = null;
            return;
        }
        this.wardorreg = wardorreg;
    }

    public String getWardorregname() {
        return wardorregname;
    }

    public void setWardorregname( String wardorregname ) {
        if(wardorregname == null || wardorregname.equals("")){
            this.wardorregname = null;
            return;
        }
        this.wardorregname = wardorregname;
    }

    public String getBrlx() {
        return brlx;
    }

    public void setBrlx( String brlx ) {
        if(brlx == null || brlx.equals("")){
            this.brlx = null;
            return;
        }
        this.brlx = brlx;
    }

    public String getWard() {
        return ward;
    }

    public void setWard( String ward ) {
        if(ward == null || ward.equals("")){
            this.ward = null;
            return;
        }
        this.ward = ward;
    }

    public String getWardname() {
        return wardname;
    }

    public void setWardname( String wardname ) {
        if(wardname == null || wardname.equals("")){
            this.wardname = null;
            return;
        }
        this.wardname = wardname;
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno( String bedno ) {
        if(bedno == null || bedno.equals("")){
            this.bedno = null;
            return;
        }
        this.bedno = bedno;
    }

    public String getYebz() {
        return yebz;
    }

    public void setYebz( String yebz ) {
        if(yebz == null || yebz.equals("")){
            this.yebz = null;
            return;
        }
        this.yebz = yebz;
    }

    public String getApplydeptcode() {
        return applydeptcode;
    }

    public void setApplydeptcode( String applydeptcode ) {
        if(applydeptcode == null || applydeptcode.equals("")){
            this.applydeptcode = null;
            return;
        }
        this.applydeptcode = applydeptcode;
    }

    public String getApplydeptname() {
        return applydeptname;
    }

    public void setApplydeptname( String applydeptname ) {
        if(applydeptname == null || applydeptname.equals("")){
            this.applydeptname = null;
            return;
        }
        this.applydeptname = applydeptname;
    }

    public String getApplydoccode() {
        return applydoccode;
    }

    public void setApplydoccode( String applydoccode ) {
        if(applydoccode == null || applydoccode.equals("")){
            this.applydoccode = null;
            return;
        }
        this.applydoccode = applydoccode;
    }

    public String getApplydocname() {
        return applydocname;
    }

    public void setApplydocname( String applydocname ) {
        if(applydocname == null || applydocname.equals("")){
            this.applydocname = null;
            return;
        }
        this.applydocname = applydocname;
    }

    public String getPatpropcode() {
        return patpropcode;
    }

    public void setPatpropcode( String patpropcode ) {
        if(patpropcode == null || patpropcode.equals("")){
            this.patpropcode = null;
            return;
        }
        this.patpropcode = patpropcode;
    }

    public String getPatpropname() {
        return patpropname;
    }

    public void setPatpropname( String patpropname ) {
        if(patpropname == null || patpropname.equals("")){
            this.patpropname = null;
            return;
        }
        this.patpropname = patpropname;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum( String idnum ) {
        if(idnum == null || idnum.equals("")){
            this.idnum = null;
            return;
        }
        this.idnum = idnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        if(address == null || address.equals("")){
            this.address = null;
            return;
        }
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday( String birthday ) {
        if(birthday == null || birthday.equals("")){
            this.birthday = null;
            return;
        }
        this.birthday = birthday;
    }

    public String getPattype() {
        return pattype;
    }

    public void setPattype( String pattype ) {
        if(pattype == null || pattype.equals("")){
            this.pattype = null;
            return;
        }
        this.pattype = pattype;
    }

    public String getPattypehint() {
        return pattypehint;
    }

    public void setPattypehint( String pattypehint ) {
        if(pattypehint == null || pattypehint.equals("")){
            this.pattypehint = null;
            return;
        }
        this.pattypehint = pattypehint;
    }

    public String getClinicdesc() {
        return clinicdesc;
    }

    public void setClinicdesc( String clinicdesc ) {
        if(clinicdesc == null || clinicdesc.equals("")){
            this.clinicdesc = null;
            return;
        }
        this.clinicdesc = clinicdesc;
    }

    public String getM_phone() {
        return m_phone;
    }

    public void setM_phone( String m_phone ) {
        if(m_phone == null || m_phone.equals("")){
            this.m_phone = null;
            return;
        }
        this.m_phone = m_phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark( String remark ) {
        if(remark == null || remark.equals("")){
            this.remark = null;
            return;
        }
        this.remark = remark;
    }

    public String getDiseasecode() {
        return diseasecode;
    }

    public void setDiseasecode( String diseasecode ) {
        if(diseasecode == null || diseasecode.equals("")){
            this.diseasecode = null;
            return;
        }
        this.diseasecode = diseasecode;
    }

    public String getDiseasename() {
        return diseasename;
    }

    public void setDiseasename( String diseasename ) {
        if(diseasename == null || diseasename.equals("")){
            this.diseasename = null;
            return;
        }
        this.diseasename = diseasename;
    }

    public String getAbotype() {
        return abotype;
    }

    public void setAbotype( String abotype ) {
        if(abotype == null || abotype.equals("")){
            this.abotype = null;
            return;
        }
        this.abotype = abotype;
    }

    public String getRhdtype() {
        return rhdtype;
    }

    public void setRhdtype( String rhdtype ) {
        if(rhdtype == null || rhdtype.equals("")){
            this.rhdtype = null;
            return;
        }
        this.rhdtype = rhdtype;
    }

    public String getSerumantibody() {
        return serumantibody;
    }

    public void setSerumantibody( String serumantibody ) {
        if(serumantibody == null || serumantibody.equals("")){
            this.serumantibody = null;
            return;
        }
        this.serumantibody = serumantibody;
    }

    public String getRecipientarea() {
        return recipientarea;
    }

    public void setRecipientarea( String recipientarea ) {
        if(recipientarea == null || recipientarea.equals("")){
            this.recipientarea = null;
            return;
        }
        this.recipientarea = recipientarea;
    }

    public String getDoctormemo() {
        return doctormemo;
    }

    public void setDoctormemo( String doctormemo ) {
        if(doctormemo == null || doctormemo.equals("")){
            this.doctormemo = null;
            return;
        }
        this.doctormemo = doctormemo;
    }

    public String getTransfusionhistory() {
        return transfusionhistory;
    }

    public void setTransfusionhistory( String transfusionhistory ) {
        if(transfusionhistory == null || transfusionhistory.equals("")){
            this.transfusionhistory = null;
            return;
        }
        this.transfusionhistory = transfusionhistory;
    }

    public String getReactionhistory() {
        return reactionhistory;
    }

    public void setReactionhistory( String reactionhistory ) {
        if(reactionhistory == null || reactionhistory.equals("")){
            this.reactionhistory = null;
            return;
        }
        this.reactionhistory = reactionhistory;
    }

    public String getAllergyhistory() {
        return allergyhistory;
    }

    public void setAllergyhistory( String allergyhistory ) {
        if(allergyhistory == null || allergyhistory.equals("")){
            this.allergyhistory = null;
            return;
        }
        this.allergyhistory = allergyhistory;
    }

    public String getAllergyname() {
        return allergyname;
    }

    public void setAllergyname( String allergyname ) {
        if(allergyname == null || allergyname.equals("")){
            this.allergyname = null;
            return;
        }
        this.allergyname = allergyname;
    }

    public String getPregnancyhistory() {
        return pregnancyhistory;
    }

    public void setPregnancyhistory( String pregnancyhistory ) {
        if(pregnancyhistory == null || pregnancyhistory.equals("")){
            this.pregnancyhistory = null;
            return;
        }
        this.pregnancyhistory = pregnancyhistory;
    }

    public String getGestationnum() {
        return gestationnum;
    }

    public void setGestationnum( String gestationnum ) {
        if(gestationnum == null || gestationnum.equals("")){
            this.gestationnum = null;
            return;
        }
        this.gestationnum = gestationnum;
    }

    public String getProductionnum() {
        return productionnum;
    }

    public void setProductionnum( String productionnum ) {
        if(productionnum == null || productionnum.equals("")){
            this.productionnum = null;
            return;
        }
        this.productionnum = productionnum;
    }

    public String getAbortionnum() {
        return abortionnum;
    }

    public void setAbortionnum( String abortionnum ) {
        if(abortionnum == null || abortionnum.equals("")){
            this.abortionnum = null;
            return;
        }
        this.abortionnum = abortionnum;
    }

    public String getLivenum() {
        return livenum;
    }

    public void setLivenum( String livenum ) {
        if(livenum == null || livenum.equals("")){
            this.livenum = null;
            return;
        }
        this.livenum = livenum;
    }

    public String getTransplanthistory() {
        return transplanthistory;
    }

    public void setTransplanthistory( String transplanthistory ) {
        if(transplanthistory == null || transplanthistory.equals("")){
            this.transplanthistory = null;
            return;
        }
        this.transplanthistory = transplanthistory;
    }

    public String getDonorblood() {
        return donorblood;
    }

    public void setDonorblood( String donorblood ) {
        if(donorblood == null || donorblood.equals("")){
            this.donorblood = null;
            return;
        }
        this.donorblood = donorblood;
    }

    public String getBloodtime() {
        return bloodtime;
    }

    public void setBloodtime( String bloodtime ) {
        if(bloodtime == null || bloodtime.equals("")){
            this.bloodtime = null;
            return;
        }
        this.bloodtime = bloodtime;
    }

    public String getBloodpurpose() {
        return bloodpurpose;
    }

    public void setBloodpurpose( String bloodpurpose ) {
        if(bloodpurpose == null || bloodpurpose.equals("")){
            this.bloodpurpose = null;
            return;
        }
        this.bloodpurpose = bloodpurpose;
    }

    public String getTbloodnature() {
        return tbloodnature;
    }

    public void setTbloodnature( String tbloodnature ) {
        if(tbloodnature == null || tbloodnature.equals("")){
            this.tbloodnature = null;
            return;
        }
        this.tbloodnature = tbloodnature;
    }

    public String getOperationname() {
        return operationname;
    }

    public void setOperationname( String operationname ) {
        if(operationname == null || operationname.equals("")){
            this.operationname = null;
            return;
        }
        this.operationname = operationname;
    }

    public String getOperationlevel() {
        return operationlevel;
    }

    public void setOperationlevel( String operationlevel ) {
        if(operationlevel == null || operationlevel.equals("")){
            this.operationlevel = null;
            return;
        }
        this.operationlevel = operationlevel;
    }

    public String getBloodplace() {
        return bloodplace;
    }

    public void setBloodplace( String bloodplace ) {
        if(bloodplace == null || bloodplace.equals("")){
            this.bloodplace = null;
            return;
        }
        this.bloodplace = bloodplace;
    }

    public String getInformedstatus() {
        return informedstatus;
    }

    public void setInformedstatus( String informedstatus ) {
        if(informedstatus == null || informedstatus.equals("")){
            this.informedstatus = null;
            return;
        }
        this.informedstatus = informedstatus;
    }

    public String getUnassignreason() {
        return unassignreason;
    }

    public void setUnassignreason( String unassignreason ) {
        if(unassignreason == null || unassignreason.equals("")){
            this.unassignreason = null;
            return;
        }
        this.unassignreason = unassignreason;
    }

    public String getApplysheettype() {
        return applysheettype;
    }

    public void setApplysheettype( String applysheettype ) {
        if(applysheettype == null || applysheettype.equals("")){
            this.applysheettype = null;
            return;
        }
        this.applysheettype = applysheettype;
    }

    public String getApplysheetname() {
        return applysheetname;
    }

    public void setApplysheetname( String applysheetname ) {
        if(applysheetname == null || applysheetname.equals("")){
            this.applysheetname = null;
            return;
        }
        this.applysheetname = applysheetname;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime( String applytime ) {
        if(applytime == null || applytime.equals("")){
            this.applytime = null;
            return;
        }
        this.applytime = applytime;
    }

    public String getApplyamount() {
        return applyamount;
    }

    public void setApplyamount( String applyamount ) {
        if(applyamount == null || applyamount.equals("")){
            this.applyamount = null;
            return;
        }
        this.applyamount = applyamount;
    }

    public String getExecdept() {
        return execdept;
    }

    public void setExecdept( String execdept ) {
        if(execdept == null || execdept.equals("")){
            this.execdept = null;
            return;
        }
        this.execdept = execdept;
    }

    public String getExecdeptname() {
        return execdeptname;
    }

    public void setExecdeptname( String execdeptname ) {
        if(execdeptname == null || execdeptname.equals("")){
            this.execdeptname = null;
            return;
        }
        this.execdeptname = execdeptname;
    }

    public String getApplysheettxm() {
        return applysheettxm;
    }

    public void setApplysheettxm( String applysheettxm ) {
        if(applysheettxm == null || applysheettxm.equals("")){
            this.applysheettxm = null;
            return;
        }
        this.applysheettxm = applysheettxm;
    }

    public String getLabtxm() {
        return labtxm;
    }

    public void setLabtxm( String labtxm ) {
        if(labtxm == null || labtxm.equals("")){
            this.labtxm = null;
            return;
        }
        this.labtxm = labtxm;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno( String orderno ) {
        if(orderno == null || orderno.equals("")){
            this.orderno = null;
            return;
        }
        this.orderno = orderno;
    }

    public String getApprovaltime() {
        return approvaltime;
    }

    public void setApprovaltime( String approvaltime ) {
        if(approvaltime == null || approvaltime.equals("")){
            this.approvaltime = null;
            return;
        }
        this.approvaltime = approvaltime;
    }

    public String getApprovalflag() {
        return approvalflag;
    }

    public void setApprovalflag( String approvalflag ) {
        if(approvalflag == null || approvalflag.equals("")){
            this.approvalflag = null;
            return;
        }
        this.approvalflag = approvalflag;
    }

    public String getPreparationno() {
        return preparationno;
    }

    public void setPreparationno( String preparationno ) {
        if(preparationno == null || preparationno.equals("")){
            this.preparationno = null;
            return;
        }
        this.preparationno = preparationno;
    }

    public String getSheetstatus() {
        return sheetstatus;
    }

    public void setSheetstatus( String sheetstatus ) {
        if(sheetstatus == null || sheetstatus.equals("")){
            this.sheetstatus = null;
            return;
        }
        this.sheetstatus = sheetstatus;
    }

    public String getReceivetime() {
        return receivetime;
    }

    public void setReceivetime( String receivetime ) {
        if(receivetime == null || receivetime.equals("")){
            this.receivetime = null;
            return;
        }
        this.receivetime = receivetime;
    }

    public String getReceivecode() {
        return receivecode;
    }

    public void setReceivecode( String receivecode ) {
        if(receivecode == null || receivecode.equals("")){
            this.receivecode = null;
            return;
        }
        this.receivecode = receivecode;
    }

    public String getReceivename() {
        return receivename;
    }

    public void setReceivename( String receivename ) {
        if(receivename == null || receivename.equals("")){
            this.receivename = null;
            return;
        }
        this.receivename = receivename;
    }

    public String getLabtxmprinttime() {
        return labtxmprinttime;
    }

    public void setLabtxmprinttime( String labtxmprinttime ) {
        if(labtxmprinttime == null || labtxmprinttime.equals("")){
            this.labtxmprinttime = null;
            return;
        }
        this.labtxmprinttime = labtxmprinttime;
    }

    public String getLabtxmprintcode() {
        return labtxmprintcode;
    }

    public void setLabtxmprintcode( String labtxmprintcode ) {
        if(labtxmprintcode == null || labtxmprintcode.equals("")){
            this.labtxmprintcode = null;
            return;
        }
        this.labtxmprintcode = labtxmprintcode;
    }

    public String getLabtxmprintname() {
        return labtxmprintname;
    }

    public void setLabtxmprintname( String labtxmprintname ) {
        if(labtxmprintname == null || labtxmprintname.equals("")){
            this.labtxmprintname = null;
            return;
        }
        this.labtxmprintname = labtxmprintname;
    }

    public String getSheetregtime() {
        return sheetregtime;
    }

    public void setSheetregtime( String sheetregtime ) {
        if(sheetregtime == null || sheetregtime.equals("")){
            this.sheetregtime = null;
            return;
        }
        this.sheetregtime = sheetregtime;
    }

    public String getSheetregcode() {
        return sheetregcode;
    }

    public void setSheetregcode( String sheetregcode ) {
        if(sheetregcode == null || sheetregcode.equals("")){
            this.sheetregcode = null;
            return;
        }
        this.sheetregcode = sheetregcode;
    }

    public String getSheetregname() {
        return sheetregname;
    }

    public void setSheetregname( String sheetregname ) {
        if(sheetregname == null || sheetregname.equals("")){
            this.sheetregname = null;
            return;
        }
        this.sheetregname = sheetregname;
    }

    public String getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime( String confirmtime ) {
        if(confirmtime == null || confirmtime.equals("")){
            this.confirmtime = null;
            return;
        }
        this.confirmtime = confirmtime;
    }

    public String getConfirmcode() {
        return confirmcode;
    }

    public void setConfirmcode( String confirmcode ) {
        if(confirmcode == null || confirmcode.equals("")){
            this.confirmcode = null;
            return;
        }
        this.confirmcode = confirmcode;
    }

    public String getConfirmname() {
        return confirmname;
    }

    public void setConfirmname( String confirmname ) {
        if(confirmname == null || confirmname.equals("")){
            this.confirmname = null;
            return;
        }
        this.confirmname = confirmname;
    }

    public String getConfirmcomment() {
        return confirmcomment;
    }

    public void setConfirmcomment( String confirmcomment ) {
        if(confirmcomment == null || confirmcomment.equals("")){
            this.confirmcomment = null;
            return;
        }
        this.confirmcomment = confirmcomment;
    }

    public String getTimetemp() {
        return timetemp;
    }

    public void setTimetemp( String timetemp ) {
        if(timetemp == null || timetemp.equals("")){
            this.timetemp = null;
            return;
        }
        this.timetemp = timetemp;
    }

    public String getMjzbz() {
        return mjzbz;
    }

    public void setMjzbz( String mjzbz ) {
        if(mjzbz == null || mjzbz.equals("")){
            this.mjzbz = null;
            return;
        }
        this.mjzbz = mjzbz;
    }

    public String getLabmatchflag() {
        return labmatchflag;
    }

    public void setLabmatchflag( String labmatchflag ) {
        if(labmatchflag == null || labmatchflag.equals("")){
            this.labmatchflag = null;
            return;
        }
        this.labmatchflag = labmatchflag;
    }


    public String getSubsyscode() {
        return subsyscode;
    }

    public void setSubsyscode(String subsyscode) {
        this.subsyscode = subsyscode;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getYexh() {
        return yexh;
    }

    public void setYexh(String yexh) {
        this.yexh = yexh;
    }

}
