// ==UserScript==
// @name              jiaodaerfuyuanqianghao
// @namespace         https://github.com/syhyz1990/baiduyun
// @version           1.0
// @author            MikeJono

// @description       抢号
// @license           AGPL-3.0-or-later
// @match             *://www.2yuanyy.com/Appointment/DoctorIndex?DepartmentId=bfe09d8c-f8f7-4d8c-b120-000c8823f985&DoctorId=00ff048d-aa4c-b995-4490-0fba14d83829&ClinicLabelId=5bc35a54-5e49-4c13-9b63-110784a52774&ClinicMarkers=null
// @require           https://unpkg.com/jquery@3.6.0/dist/jquery.min.js
// @require           https://unpkg.com/sweetalert2@10.16.6/dist/sweetalert2.all.min.js
// @require           https://unpkg.com/js-md5@0.7.3/build/md5.min.js
// @connect           baidu.com
// @connect           baidupcs.com
// @connect           aliyundrive.com
// @connect           189.cn
// @connect           xunlei.com
// @connect           quark.cn
// @connect           youxiaohou.com
// @connect           localhost
// @connect           *
// @run-at            document-idle
// @grant             unsafeWindow
// @grant             GM_xmlhttpRequest
// @grant             GM_setClipboard
// @grant             GM_setValue
// @grant             GM_getValue
// @grant             GM_openInTab
// @grant             GM_info
// @grant             GM_registerMenuCommand
// @grant             GM_cookie
// ==/UserScript==
//DoctorId: "00ff048d-aa4c-b995-4490-0fba14d83829", DoctorCode: "698", DoctorName: "任建文"
//ClinicLabelId "5bc35a54-5e49-4c13-9b63-110784a52774"
// ClinicLabelName"任建文"
// ClinicMode 1
// DepartmentId "bfe09d8c-f8f7-4d8c-b120-000c8823f985"
// DepartmentName "皮肤科门诊"
// EndClinicDate "2023-02-09T14:32:30.6215318+08:00"
// OrgCode "6101020016"
// OrganizationId "010e5278-aa45-81d0-4d3e-7d5be619b7d9"
// OrganizationName "西安交通大学第二附属医院"
// StartClinicDate "2023-02-07T14:32:30.6215318+08:00"

(async function () {
        'use strict';
        console.log("-----------------------挂号开始--------------------------")
        //1.循环查询医生的排号情况
        //1.1定时器
        setInterval(()=>{
            //1.2 查询医生号源
            //查询条件
            let addition = {
                "noon": 1,//上午
                "weekday": "星期六",
                idnum:"61240119910917806X",
                PatientId: "00e09f60-ae37-9a24-460c-3866c7331a32"
                // PatientId: "00de7948-ae37-b880-4b1b-55e0ec922fd2",//xs
            }
            var searchdoctorappointments = {
                "DoctorId": "00ff048d-aa4c-b995-4490-0fba14d83829",
                "ClinicId": "5bc35a54-5e49-4c13-9b63-110784a52774"  //任建文
            }
            HB.Query("booking/searchdoctorappointments", searchdoctorappointments, async function (ajaxstatus, response) {


                if (!ajaxstatus.AjaxStatus) {
                    HB.MessageError("该医生暂无出诊安排");
                    console.log("1.该医生暂无出诊安排")
                } else {
                    if (HB.IsSucess(response)) {
                        var datas = response.ResultData;
                        for (let i = 0; i < datas.length; i++) {
                            let apps = datas[i].Appointments
                            if (apps.length > 0) { //有排班
                                for (let j = 0; j < apps.length; j++) {
                                    let total = apps[j].Total //总共的号数
                                    let usedCount = apps[j].Counts //已经预约的号数
                                    let date = apps[j].Date.substring(0, 10) //预约时间  2023-02-10 T00:00:00
                                    let noon = apps[j].Noon //预约时间的上午 1； 预约时间的下午 2
                                    let SchmId = apps[j].SchmId //排班号
                                    let ClinicLabelId = apps[j].ClinicLabelId //排班号
                                    if (total > usedCount) {//证明有预约号
                                        //确认排班信息
                                        // await doConfirm(SchmId, date, noon) //todo 返回结果，根据结果值判断
                                        setTimeout(() => {
                                            let flag = doConfirm(SchmId, date, noon,ClinicLabelId)
                                            if (flag) return
                                        }, 2000)
                                    } else {
                                        console.log(datas[i].ClinicLabelName + "在" + datas[i].DepartmentName + "科室，" + date + "  " + (noon == 1 ? "上午" : "下午") + "预约已满")
                                    }
                                }
                            } else {
                                console.log(datas[i].ClinicLabelName + "在" + datas[i].DepartmentName + "科室，暂无排班")
                            }
                        }
                    }
                }
            });
            function doConfirm(SchmId, date, noon,ClinicLabelId) {
                var confirmAddtion = {
                    NoonId:noon,SchmId:SchmId,
                    ClinicLabelId:ClinicLabelId,
                    ClinicDate: date
                }
                HB.Query("standard/getconfirmappoint", confirmAddtion, async function (ajaxstatus, response) {


                    if (!ajaxstatus.AjaxStatus) {
                        HB.MessageError("确认预约失败");
                        console.log("确认预约失败！！！！！")
                    } else {
                        if (HB.IsSucess(response)) {
                            var datas = response.ResultData;

                            let resList = datas.TimePartResponsesList
                            let info = datas.PersonalBookInfo
                            let ClinicLabelId = info.ClinicLabelId
                            let WeekText = info.WeekText //星期几

                            if(addition.weekday.indexOf(WeekText)<0){
                                console.log("排号在【"+WeekText+"】,不满足【"+addition.weekday+"】的预定")
                                return false
                            }
                            for (let i = 0; i < resList.length; i++) {
                                console.log(resList[i])
                                let count = resList[i].Count//该时间段已经预约的数量
                                let total = resList[i].Total//该时间段总数量
                                let EndTime = resList[i].EndTime
                                let StartTime = resList[i].StartTime
                                let SchmDetailId = resList[i].SchmDetailId
                                if (total > count) { //有排班
                                    let flag = doPay(StartTime, EndTime, ClinicLabelId, SchmId, date, noon, SchmDetailId)
                                    if (flag) return true
                                    else return false
                                }
                            }
                        }
                    }
                });
            }
            function doPay(StartTime, EndTime, ClinicLabelId, SchmId,date, noon, SchmDetailId) {

                var confirmAddtion = {
                    ClinicLabelId: ClinicLabelId,
                    ClinicDate: date,
                    Noon: noon, // ChannelId:"",
                    HospitalGuid: "010e5278-aa45-81d0-4d3e-7d5be619b7d9",
                    SchmId: SchmId,
                    SchmDetailId: SchmDetailId,
                    CardNum:addition.idnum,//cjj
                    IdCode: addition.idnum,
                    IdType: "0",
                    AppointmentType: "2",
                    PayType: "OfflinePay",
                    PayChannel:"0e80b9d4-0e5d-4635-8e31-a645010baf28",
                    PatientId: addition.PatientId,//cjj
                    TimePart: StartTime,
                    EndTimePart: EndTime,
                    IsClinic: false,
                    OperateType: "NormalAppointment",
                }
                HB.Query("booking/clinicpay", confirmAddtion, function (ajaxstatus, response) {
                    if (!ajaxstatus.AjaxStatus) {
                        HB.MessageError("预约失败");
                        console.log("预约失败")
                        return false
                    } else {
                        if (HB.IsSucess(response)) {
                            alert("预约成功！！！！！！！")
                            console.log("预约成功！！！！！")
                            return true
                        } else{
                            console.log("错误信息： "+response.Message)
                            return false
                        }
                    }
                });
            }
            console.log("-----------------------查询一次结束--------------------------")
        },5000)
        console.log("-----------------------挂号结束--------------------------")

    }

)();

