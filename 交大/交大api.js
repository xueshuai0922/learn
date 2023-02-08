var clinicMarkers = decodeURI(HB.QueryString("ClinicMarkers"));  //专病标签
$(function () {

    if (medicalRecord == 0) {
        $(".info").find("p").show();
        $(".info .J-ClinicDesc-p").hide();
        $(".J-clinicmarkers").hide();
    }
    getdoctor();
    //getcliniclistbyid();
    //chuzhen();
    $(".public-popup-close").click(function() {
        btnCancel();
    });
});
var HospitalGuid = "";


//获取医生个人信息
function getdoctor() {
    var request = {
        "DoctorId": HB.QueryString("DoctorId"),//"880c740d-6916-4ae0-86f1-a66001355009"
        "ClinicId":HB.QueryString("ClinicLabelId")
    }
    HB.Query("employee/getdoctor", request, function (ajaxstatus, response) {
        if (ajaxstatus.AjaxStatus) {
            if (HB.IsSucess(response)) {
                var datas = response.ResultData;
                $("#Blank").attr("href", "/News/NewsListInfo?DoctorId=" + datas.DoctorCode + "&callIndex=jzxz&DepartmentId=" + datas.DepartmentCode + "&HospitalId=" + datas.OrganizationCode);
                //$("#doctorimg").attr("src", $("#HbManageroot").val() + datas.DoctorImage);
                $("#doctorimg").attr("src", HeadImageUrl(datas.DoctorImage));
                $(".doctor-index-title .name").text(datas.DoctorName);

                if (datas.DoctorTitle != null && datas.DoctorTitle != undefined)
                {
                    $(".doctor-index-title .type").text(datas.DoctorTitle);
                }

                if (medicalRecord == 0) {
                    if (datas.DoctorId == "00000000-0000-0000-0000-000000000000" || datas.DoctorId == "" || datas.DoctorId == null || datas.DoctorId == undefined) {
                        if (clinicMarkers && (clinicMarkers!="null") &&  clinicMarkers.length > 0) {
                            $(".doctor-index-title .depart").html("<a class=\"public-hover\" href=\"/Appointment/DepartmentIndex?DepartmentId=" + datas.DepartmentId + "&orgId=" + datas.OrganizationId + " \">" + datas.DepartmentName + "</a>" + "<span style='display:inline-block;padding-left:10px;'>" + clinicMarkers + "</span>");
                        } else {
                            $(".doctor-index-title .depart").html("<a class=\"public-hover\" href=\"/Appointment/DepartmentIndex?DepartmentId=" + datas.DepartmentId + "&orgId=" + datas.OrganizationId + " \">" + datas.DepartmentName + "</a>");
                        }
                    }
                } else {
                    $(".doctor-index-title .depart").html("<a class=\"public-hover\" href=\"/Appointment/DepartmentIndex?DepartmentId=" + datas.DepartmentId + "&orgId=" + datas.OrganizationId + " \">" + datas.DepartmentName + "</a>");
                }
                $("#sex").text(datas.SexText);
                if (datas.DoctorSpecial) {
                    $("#doctorSpecial-all").val(datas.DoctorSpecial);
                    $(".doctorSpecial").text(datas.DoctorSpecial);
                }
                if (datas.DoctorIntro) {
                    $(".doctorIntro").text(datas.DoctorIntro);
                    $("#doctorIntro-all").val(datas.DoctorIntro);
                }
                else {
                    $(".doctorIntro").text("");
                    $("#doctorIntro-all").val("");
                }
                if ($(".doctorSpecial").text().length > 150) {
                    $(".doctorSpecial").text($(".doctorSpecial").text().substring(0, 150) + "...");
                }
                if ($(".doctorIntro").text().length > 170) {
                    $(".doctorIntro").text($(".doctorIntro").text().substring(0, 170) + "...");
                }
                if (datas.DoctorId == "00000000-0000-0000-0000-000000000000" || datas.DoctorId == "" || datas.DoctorId == null || datas.DoctorId == undefined) {
                    $("#deptCollect2").hide();
                    $("#deptCollect1").hide();
                    $(".doctor-index-title .type").hide();
                    $(".J-clinicmarkers").show();
                } else {
                    if (datas.IsFollow === true) {
                        $("#deptCollect1").addClass("show").removeClass("hidden");
                        $("#deptCollect2").addClass("hidden").removeClass("show");
                    } else {
                        $("#deptCollect2").addClass("show").removeClass("hidden");
                        $("#deptCollect1").addClass("hidden").removeClass("show");
                    }
                    $(".doctor-index-title .type").show();
                    $(".J-clinicmarkers").hide();
                }
                $(".open").click(function () {
                    var str = "";
                    str += ("<div class=\'pop-mask-black\'></div>");
                    str += ("		<div class=\'pop-mask-content\'>");
                    str += ("			<div class=\'content\'>");
                    if ($(this).prev().attr("class") === "doctorSpecial") {
                        str += ("<p>" + $("#doctorSpecial-all").val() + "</p>");
                    }
                    if ($(this).prev().attr("class") === "doctorIntro") {
                        str += ("<p>" + $("#doctorIntro-all").val() + "</p>");
                    }
                    if ($(this).prev().attr("class") === "J-ClinicDesc") {
                        str += ("<p>" + $("#J-ClinicDesc-all").val() + "</p>");
                    }
                    str += ("			    <div class=\'hide\' style=\'color:#0674E4;cursor:pointer\'><a class=\"public-hover\">[收起]</a></div>");
                    str += ("		 	</div>");
                    str += ("		</div>");
                    $("body").append(str);
                    $(".hide").click(function () {
                        $(".pop-mask-black").remove();
                        $(".pop-mask-content").remove();
                    });
                });
                $(".qr-show").empty();
                var defauleImgSrc = "../Content/images/default/defaultCode.png";
                var qrcode = "";
                if ((datas.QrCodeImage == "") || (datas.QrCodeImage == 'undefind') || (datas.QrCodeImage == null)) {
                    qrcode = ("<img src=\'" + defauleImgSrc + "\' style=\'width: 140px;   text-align: center; height: 140px; vertical-align: middle;margin: 10px auto;padding-top: 10px;\'>");
                    $(".qr-show").append(qrcode).show();
                } else {
                    qrcode = ("<img src=\'" + $("#HbManageroot").val() + datas.QrCodeImage + "\' style=\'width: 140px;   text-align: center; height: 140px; vertical-align: middle;margin: 10px auto;padding-top: 10px;\'>");
                    $(".qr-show").append(qrcode).show();
                }
                if (medicalRecord == 1) {
                    var clinicDesc = "";
                    if (datas.DoctorId == "" || datas.DoctorId == "00000000-0000-0000-0000-000000000000" || datas.DoctorId == "null") {
                        clinicDesc = localStorage.getItem("ClinicDesc");
                        if (clinicDesc == "null" || clinicDesc == "undefined" || clinicDesc == null) {
                            clinicDesc = "";
                        }
                        if (clinicMarkers == "null" || clinicMarkers == "undefined" || clinicMarkers == undefined) {
                            clinicMarkers = "";
                        }
                        $("#J-ClinicDesc-all").val(clinicDesc);
                        $(".J-ClinicDesc").text(clinicDesc);
                        $(".J-clinicmarkers").html(clinicMarkers);
                        $(".info").find("p").hide();
                        $(".info .J-ClinicDesc-p").show();
                    } else {
                        clinicDesc = localStorage.getItem("ClinicDesc");
                        $(".J-clinicmarkers").html(clinicMarkers);
                        $(".info").find("p").hide();
                        $(".info").find("p").eq(1).show();
                    }


                }
                //$(".qr-show img").attr("src", $("#HbManageroot").val() + datas.QrCodeImage);   //此处注意ie兼容二维码的出现。
                getstopnotes(datas.DepartmentId, datas.DoctorId);
                gettopdoctors(datas.DepartmentId);
                HospitalGuid = datas.OrganizationId;
                //HospitalGuid = datas.OrganizationCode;
                searchappointment();
                $("#docMesLoad").hide();
                $("#docMessage").hide();
                $("#docMesLoad").fadeIn(500);
                // proxyImg($("#doctorimg").attr('src'), '/Content/images/default/docdefault.png', $("#doctorimg"));
                /*$("#doctorimg").error(function () {
                    $(this).attr("src", "/Content/images/default/docdefault.png");
                })*/

            }
            else {
                HB.MessageError(response.Message);
            }

        }
        else {
            HB.MessageError(response.Message);
        }
    });
}

//获取推荐医生（按医院、科室）
function gettopdoctors(DepartmentId) {
    var request = {
        "orgId": $("#idc_orgid").val(),//"cc213144-8339-4ad9-9493-759b5f8d227b",
        "deptId": DepartmentId,//"d8efbe0f-c33b-49a5-8e79-232a87426908",
        "pageIndex ": 1,
        "pageSize": 20
    }
    HB.Query("employee/GetEmployeeInfoByDept", request, function (ajaxstatus, response) {
        if (!ajaxstatus.AjaxStatus) {
            HB.MessageError(response.Message);
        }
        else {
            if (HB.IsSucess(response)) {
                var datas = response.ResultData;
                var doctorlist = "";
                var count = 0;
                for (var i = 0; i < datas.length; i++) {
                    if (datas[i].PassportId != HB.QueryString("DoctorId") && count < 4) {
                        //for (var i = 0; i < 3; i++) {
                        var titleText = datas[i].TitleText;
                        if (titleText==null) {
                            titleText = "";
                        }
                        doctorlist += ("<a href=\'DoctorIndex?DoctorId=" + datas[i].PassportId + "\'><div class=\'doctor-info\'>");
                        // doctorlist += ("									<img class=\'doctor-img\' src=\'" + $("#HbManageroot").val() + datas[i].HeadImageUrl + "\'  />");
                        doctorlist += ("									<img class=\'doctor-img\' src=\'" + HeadImageUrl(datas[i].HeadImageUrl) + "\'  />");
                        doctorlist += ("									<div class=\'doctor-text\'>");
                        doctorlist += ("									   <div class=\'doctor-name\' title=\'" + datas[i].EmployeeName + "\'>" + datas[i].EmployeeName + "</div>");
                        doctorlist += ("									   <div class=\'doctor-type\'><span title=\'" + datas[i].DepartmentText + "\'>" + datas[i].DepartmentText + "</span><span title=\'" + titleText + "\'>" + titleText + "</span></div>");
                        var special = "";
                        if (datas[i].Specialty) {
                            special = datas[i].Specialty;
                        }
                        if (special.length > 17) {
                            doctorlist += ("									   <div class=\'doctor-good \ title=\""+special+"\"'>擅长:" + special.substring(0, 17) + "...</div>");
                        }
                        else {
                            doctorlist += ("									   <div class=\'doctor-good \'>擅长:" + special + "</div>");
                        }

                        doctorlist += ("									</div>");
                        doctorlist += ("								</div></a>");

                        count++;
                    }
                }

                $(".doctor-list").append(doctorlist);
                $(".doctor-index-box").hide();
                $("#docDepart").hide();
                $(".doctor-index-box").fadeIn(500);
                /*$(".doctor-img").error(function () {
                    $(this).attr("src", "/Content/images/default/docdefault.png");
                });*/
                // proxyImg($(".doctor-img").attr('src'), '/Content/images/default/docdefault.png', $(".doctor-img"));
            }
            else {
                HB.MessageError(response.Message);
            }
        }
    });
}

//停诊通知
function getstopnotes(DepartmentId, DoctorId) {
    var myDate = new Date().Format("yyyy-MM-dd");
    var stopnotes = {
        "OrganizationId": $("#idc_orgid").val(),
        //"DepartmentId": DepartmentId,//"d8efbe0f-c33b-49a5-8e79-232a87426908",
        "DoctorId": DoctorId,//"00000000-0000-0000-0000-000000000000",
        "ClinicLabelId": "",//"1ee1ca71-466b-4ecb-91dd-6ccaee6b7a49",
        "pageSize": 4,
        "pageIndex": 1,
        "startTime": myDate,
        "EndTime": ""
    }
    HB.Query("standard/newgetstopnotes", stopnotes, function (ajaxstatus, response) {
        if (!ajaxstatus.AjaxStatus) {
            HB.MessageError(response.Message);
        }
        else {
            if (HB.IsSucess(response)) {
                if (response.ResultData != null) {
                    var datas = response.ResultData;
                    if (datas.length <= 0) {
                        $(".stoplist ul").append("<li><span class=\'name\'>无停诊信息</span></li>");
                    } else {
                        var stoplist = "";
                        for (var i = 0; i < datas.length; i++) {
                            var depclic = datas[i].ClinicLabelName;
                            var depdoc = datas[i].DoctorName;
                            var dep = datas[i].DepartmentName;
                            if (depdoc.length > 3) {
                                depdoc = depdoc.substr(0, 7) + "...";
                            }
                            if (depclic.length > 3) {
                                depclic = depclic.substr(0, 7) + "...";
                            }
                            if (dep.length > 3) {
                                dep = dep.substr(0, 4) + "...";
                            }
                            if (datas[i].StopTypeName == "临时停诊" || datas[i].StopTypeName == "假期临时停诊") {
                                if (datas[i].StopTypeName == "临时停诊") {
                                    datas[i].StopTypeName = "停诊";
                                    stoplist += ("<li><span class=\'name\' title=\"" + datas[i].ClinicLabelName+ "\">");
                                    stoplist += (depclic + "</span> <span class=\'depart\'>" + datas[i].DepartmentName + "</span><br>" + datas[i].StopDate.substring(0, 10) + " " + datas[i].NoonName + " " + getMyDay(new Date(datas[i].StopDate.substring(0, 10))) + " " + datas[i].StopTypeName + "<br></li>");
                                } else {
                                    stoplist += ("<li><span class=\'name\' title=\"" + datas[i].ClinicLabelName + "\">");
                                    stoplist += (depclic + "</span> <span class=\'depart\'>" + datas[i].ClinicLabelName + "</span><br>" + datas[i].StopDate.substring(0, 10) + "" + datas[i].NoonName + " " + getMyDay(new Date(datas[i].StopDate.substring(0, 10))) + "" + datas[i].StopTypeName + "<br></li>");
                                }
                            } else {
                                stoplist += ("<li><span class=\'name\' title=\"" + datas[i].ClinicLabelName + "\">");
                                stoplist += (depclic + "</span> <span class=\'depart\'>" + datas[i].DepartmentName + "</span><br>" + datas[i].NoonName + " " + datas[i].StopTypeName + " <br></li>");
                            }
                        }
                        $(".stoplist ul").append(stoplist);
                    }
                } else {
                    $(".stoplist ul").append("<li>无停诊信息！！</li>");
                }
            }
            else {
                HB.MessageError(response.Message);
            }
        }
    });
}

//预约数据检索（号源）
function searchappointment() {
    console.log(HB.QueryString("zhuanbin"));
    var searchdoctorappointments = {
        "DoctorId": HB.QueryString("DoctorId"),
        "ClinicId": HB.QueryString("ClinicLabelId")
    }
    var noonTypes = "";
    HB.Query("booking/searchdoctorappointments", searchdoctorappointments, function (ajaxstatus, response) {
        if (!ajaxstatus.AjaxStatus) {
            HB.MessageError("该医生暂无出诊安排");
            //HB.MessageError(response.Message);
        }
        else {
            if (HB.IsSucess(response)) {
                var day = 0;
                var startDate = "";
                var endDate = "";
                var s = "";
                var datas = response.ResultData;
                console.log(datas);
                for (var gi = 0; gi < datas.length; gi++) {
                    if (datas[gi].StartClinicDate != "0001-01-01T00:00:00") {
                        day =(parseInt((DateDiff(datas[gi].EndClinicDate.substring(0, 10), datas[gi].StartClinicDate.substring(0, 10)) - 1)/7)+1)*7-1;

                        startDate = HB.DateFormat(datas[gi].StartClinicDate.substring(0, 10), "yyyy-MM-dd");
                        s = new Date(startDate.replace(/-/g, "/"));
                        endDate = dateCount(s, day);

                        break;
                    }
                }

                var dateArr = getAll(startDate, endDate);
                dateArr = startDate + "," + dateArr + endDate;
                dateArr = dateArr.split(",");

                function getDate(strDate) {
                    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
                        function (aaa) { return parseInt(aaa, 10) - 1; }).match(/\d+/g) + ')');
                    return date;
                }

                for (var n = 0; n < dateArr.length; n++) {
                    dateArr[n] = getNowFormatDate(getDate(dateArr[n]));
                }

                function infoText(types) {
                    var text = "";
                    switch (types) {
                        case 1:
                            text = "上午";
                            break;
                        case 2:
                            text = "下午";
                            break;
                        case 3:
                            text = "夜间";
                            break;
                        case 4:
                            text = "凌晨";
                            break;
                        case 5:
                            text = "全天";
                            break;
                        default:
                    }
                    return text;
                }

                var ACount = 0;
                console.log(datas[0].Appointments);
                for (var a = 0; a < datas.length; a++) {
                    if (datas[a].Appointments.length != 0) {
                        ACount++;
                        var open;
                        if (ACount < 2) {
                            open = "bellows--is-open";
                        }
                        console.log(datas);
                        var shecdule = "";
                        shecdule += (" <div class=\'bellows__item " + open + "\'>");
                        shecdule += ("                        <div class=\'bellows__header \'>");
                        shecdule += ("                            <h3>" + datas[a].ClinicLabelName + "&nbsp;&nbsp;" + datas[a].DepartmentName + "&nbsp;（" + datas[a].OrganizationName + "）</h3>");
                        shecdule += ("                        </div>");
                        shecdule += ("                        <div class=\'bellows__content\'>");
                        shecdule += ("                            <div id=\'schedule-" + a + "\' class=\'schedule-outer clear\'>");
                        shecdule += ("");
                        shecdule += ("                                <div class=\'prev\'></div>");
                        shecdule += ("                                <div class=\'left\'>");
                        shecdule += ("                                    ");
                        shecdule += ("                                </div>");
                        shecdule += ("                                <div class=\'right\'>");
                        shecdule += ("                                    <ul class=\'outer\'></ul>");
                        shecdule += ("                                </div>");
                        shecdule += ("                                <div class=\'next\'></div>");
                        shecdule += ("                            </div>");
                        shecdule += ("                        </div>");
                        shecdule += ("                    </div>");
                        $(".bellows").append(shecdule);
                        var arr = [];
                        var isFirst = true;
                        var noonType;
                        for (var o = 0; o < datas[a].Appointments.length; o++) {

                            if (isFirst) {
                                arr.push(infoText(parseInt(datas[a].Appointments[o].Noon)));
                            }
                            isFirst = false;
                            if ($.inArray(infoText(parseInt(datas[a].Appointments[o].Noon)), arr) === -1) {
                                arr.push(infoText(parseInt(datas[a].Appointments[o].Noon)));
                            }
                            //若午别只有一个，则添加
                            if (arr.length == 1) {
                                if (arr[0] == "上午") {
                                    arr.push("下午");
                                }
                                if (arr[0] == "下午") {
                                    arr.unshift("上午");
                                }
                                if (arr[0] == "夜间") {
                                    arr.push("凌晨");
                                }
                                if (arr[0] == "凌晨") {
                                    arr.unshift("夜间");
                                }
                                if (arr[0] == "全天") {
                                    arr.push("");
                                }
                            }
                            noonTypes = arr.length;
                            noonType = arr;
                        }

                        var noontext = " <div class=\"title\"></div>";
                        for (var i = 0; i < arr.length; i++) {
                            noontext += ("<div class=\"item\">" + arr[i] + "</div>");

                        }
                        $("#schedule-" + a + " .left").append(noontext);
                        var weekday = Math.ceil(day / 7);
                        var daterow1 = "";
                        for (var w = 0; w < weekday; w++) {
                            var daterow = "";
                            for (var i = 0; i < 7; i++) {
                                daterow += (" <li>");
                                daterow += ("                            <div id=\'" + i + "day" + "\' class=\'title\'>");
                                if (dateArr[7 * w + i] == undefined) {
                                    daterow += ("                                <p class=\'date\'></p>");

                                } else {
                                    daterow += ("                                <p class=\'date\'>" + dateArr[7 * w + i] + "</p>");
                                }

                                var arys1 = new Date(Date.parse(dateArr[i]));
                                var today = new Array('周日', '周一', '周二', '周三', '周四', '周五', '周六');
                                var week = today[arys1.getDay()];
                                if (dateArr[7 * w + i] == undefined) {
                                    week = "";
                                    daterow += ("                                <div class=\'week\'>" + week + "</div>");
                                } else {
                                    daterow += ("                                <div class=\'week\'>" + week + "</div>");
                                }
                                daterow += ("                            </div>");
                                for (var j = 0; j < noonTypes; j++) {
                                    daterow += (" <div id="+w + a + i + j + " class=\'item\'></div>");

                                }
                                daterow += ("                        </li>");
                            }
                            daterow1 += ("<li><ul class=\'inner\'>");
                            daterow1 += daterow;
                            daterow1 += ("</ul></li>");

                        }
                        var channelid = $("#idc_channelId").val();
                        $("#schedule-" + a + " .outer").append(daterow1);
                        for (var p = 0; p < weekday; p++) {
                            for (var k = 0; k < datas[a].Appointments.length; k++) {
                                for (var m = 0; m < 7; m++) {
                                    if (datas[a].Appointments[k].Total > datas[a].Appointments[k].Count && datas[a].Appointments[k].Date.substring(0, 10) == $("#schedule-" + a + " .outer").find(".inner li").eq(7 * p + m).find(".date").text()) {
                                        var chaday = DateDiff(datas[a].Appointments[k].Date.substring(0, 10), $(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(0).find(".date").text());
                                        if (parseInt(datas[a].Appointments[k].Noon) == 5) {
                                            $(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(chaday).find("#" + p + a + (parseInt(chaday) - 7 * p) + arr.indexOf("全天")).html("<a data-OrgCode=\"" + datas[a].OrgCode + "\" class=\"islogins\" onclick=\"gotoSomeWhere.call(this)\" hrefs=\"ConfirmAppoint?ClinicLabelId=" + datas[a].Appointments[k].ClinicLabelId + "&ClinicDate=" + datas[a].Appointments[k].Date.substring(0, 10) + "&NoonId=" + datas[a].Appointments[k].Noon + "&NoonText=" + datas[a].Appointments[k].NoonName + "&HospitalGuid=" + HospitalGuid + "&SchmId=" + datas[a].Appointments[k].SchmId + "\"><div class=\'active \'>" + datas[a].Appointments[k].Counts + "/" + datas[a].Appointments[k].Total + "</div></a>");
                                        } else if (parseInt(datas[a].Appointments[k].Noon) == 2) {
                                            $(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(chaday).find("#" + p + a + (parseInt(chaday) - 7 * p) + arr.indexOf("下午")).html("<a data-OrgCode=\"" + datas[a].OrgCode + "\" class=\"islogins\" onclick=\"gotoSomeWhere.call(this)\" hrefs=\"ConfirmAppoint?ClinicLabelId=" + datas[a].Appointments[k].ClinicLabelId + "&ClinicDate=" + datas[a].Appointments[k].Date.substring(0, 10) + "&NoonId=" + datas[a].Appointments[k].Noon + "&NoonText=" + datas[a].Appointments[k].NoonName + "&HospitalGuid=" + HospitalGuid + "&SchmId=" + datas[a].Appointments[k].SchmId + "\"><div class=\'active \'>" + datas[a].Appointments[k].Counts + "/" + datas[a].Appointments[k].Total + "</div></a>");
                                        } else if (parseInt(datas[a].Appointments[k].Noon) == 3) {
                                            $(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(chaday).find("#" + p + a + (parseInt(chaday) - 7 * p) + arr.indexOf("夜间")).html("<a data-OrgCode=\"" + datas[a].OrgCode + "\" class=\"islogins\" onclick=\"gotoSomeWhere.call(this)\" hrefs=\"ConfirmAppoint?ClinicLabelId=" + datas[a].Appointments[k].ClinicLabelId + "&ClinicDate=" + datas[a].Appointments[k].Date.substring(0, 10) + "&NoonId=" + datas[a].Appointments[k].Noon + "&NoonText=" + datas[a].Appointments[k].NoonName + "&HospitalGuid=" + HospitalGuid + "&SchmId=" + datas[a].Appointments[k].SchmId + "\"><div class=\'active \'>" + datas[a].Appointments[k].Counts + "/" + datas[a].Appointments[k].Total + "</div></a>");
                                        } else if (parseInt(datas[a].Appointments[k].Noon) == 4) {
                                            $(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(chaday).find("#" + p + a + (parseInt(chaday) - 7 * p) + arr.indexOf("凌晨")).html("<a data-OrgCode=\"" + datas[a].OrgCode + "\" class=\"islogins\" onclick=\"gotoSomeWhere.call(this)\" hrefs=\"ConfirmAppoint?ClinicLabelId=" + datas[a].Appointments[k].ClinicLabelId + "&ClinicDate=" + datas[a].Appointments[k].Date.substring(0, 10) + "&NoonId=" + datas[a].Appointments[k].Noon + "&NoonText=" + datas[a].Appointments[k].NoonName + "&HospitalGuid=" + HospitalGuid + "&SchmId=" + datas[a].Appointments[k].SchmId + "\"><div class=\'active \'>" + datas[a].Appointments[k].Counts + "/" + datas[a].Appointments[k].Total + "</div></a>");
                                        } else {
                                            //$(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(chaday).find("#" + p + a + (parseInt(chaday) - 7 * p) + 0).html("<a onclick=\"islogins()\" class=\"islogins\" href=\"ConfirmAppoint?ClinicLabelId=" + datas[a].Appointments[k].ClinicLabelId + "&ClinicDate=" + datas[a].Appointments[k].Date.substring(0, 10) + "&NoonId=" + datas[a].Appointments[k].Noon + "&NoonText=" + datas[a].Appointments[k].NoonName + "&HospitalGuid=" + HospitalGuid + "\"><div class=\'active \'>" + datas[a].Appointments[k].Count + "/" + datas[a].Appointments[k].Total + "</div></a>");
                                            $(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(chaday).find("#" + p + a + (parseInt(chaday) - 7 * p) + arr.indexOf("上午")).html("<a data-OrgCode=\"" + datas[a].OrgCode + "\" onclick=\"gotoSomeWhere.call(this)\" class=\"islogins\" hrefs=\"ConfirmAppoint?ClinicLabelId=" + datas[a].Appointments[k].ClinicLabelId + "&ClinicDate=" + datas[a].Appointments[k].Date.substring(0, 10) + "&NoonId=" + datas[a].Appointments[k].Noon + "&NoonText=" + datas[a].Appointments[k].NoonName + "&HospitalGuid=" + HospitalGuid + "&SchmId=" + datas[a].Appointments[k].SchmId + "\"><div class=\'active \'>" + datas[a].Appointments[k].Counts + "/" + datas[a].Appointments[k].Total + "</div></a>");
                                        }
                                    } else if ((datas[a].Appointments[k].Total - datas[a].Appointments[k].Counts) == 0 && datas[a].Appointments[k].Total != 0 &&
                                        $("#schedule-" + a + " .outer").find(".inner li").eq(m).find(".date").text()) {
                                        var daycha = DateDiff(datas[a].Appointments[k].Date.substring(0, 10), $(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(0).find(".date").text());
                                        $(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(daycha).find("#" + p + a + daycha + (parseInt(datas[a].Appointments[k].Noon) - 1) + "").html("<div class=\'full\'>约满</div>");
                                    } else if (datas[a].Appointments[k].Total == 0 && datas[a].Appointments[k].Counts == 0 && $("#schedule-" + a + " .outer").find(".inner li").eq(m).find(".date").text()) {
                                        var daycha2 = DateDiff(datas[a].Appointments[k].Date.substring(0, 10), $(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(0).find(".date").text());
                                        $(".bellows").find("#schedule-" + a + " .outer").find(".inner li").eq(daycha2).find("#" + p + a + daycha2 + (parseInt(datas[a].Appointments[k].Noon) - 1) + "").html("<div class=\'stop\'>停诊</div>");

                                    }


                                }
                            }


                        }
                        daterow1 = "";

                        $.schedule("#schedule-" + a + "");
                        $("#schedule-" + a + " .right").height($("#schedule-" + a + " .left").height());

                        $(".prev").height($(".inner .left").height());
                        $(".next").height($(".inner .left").height());

                    }
                }
                ab();
                $('.bellows').bellows({
                    singleItemOpen: true,
                    easing: 'ease-in-out',
                    duration: 600
                });
                $(".inner").each(function () {
                    $(this).find("li").eq($(this).find("li").length - 1).find(".item").css("border-right", "1px solid #d2d2d2");
                    $(this).find("li").eq($(this).find("li").length - 1).find(".title").css("border-right", "1px solid #d2d2d2");
                })
                $(".doctor-index-date").hide();
                $("#docClinic").hide();
                $("#docLoading").hide();
                $(".doctor-index-date").fadeIn(500);
            }
            else {
                HB.MessageError("该医生暂无出诊安排");
                //  HB.MessageError(response.Message);
            }
        }
    });
}
//页面跳转
function gotoSomeWhere() {
    if (HB.Security.AutoLogined()) {
        var __self = this;
        var href = $(this).attr('hrefs');
        var isShowComfire = orgConfig.YuYueGuaHao.BusinessConfig.YuYueXvZhi == 'true' ? true : false;
        if (isShowComfire) {
            var data = {
                CallIndex: $(this).attr("data-OrgCode") + "_jzxz_0001"
            }
            HB.Query("cms/getmorearticleoneinfo", data, function (ar, response) {
                if (ar.AjaxStatus) {
                    if (HB.IsSucess(response)) {
                        var datas = response.ResultData;
                        appointKnow(datas.Content, href);
                    }
                    else {
                        HB.MessageError(response.Message);
                    }
                }
                else {
                    HB.MessageError(response);
                }
            });

        } else {
            window.location.href = href;
        }
    } else {
        createLogin();
    }
}

function appointKnow(mesg, href) {
    $(".public-popups").show();
    $(".public-dialog").removeClass("bounceOutDown").addClass("bounceInDown");
    $(".public-dialog").css('margin-top', -300 + 'px');
    $(".public-popup-content").html(mesg);
    $("#surebtn").html("<button class=\"btn btn-default btn-secondary btnAnimate\" onclick=\"CancelAppointmentOnClick('" + href + "')\">确定</button><button onclick=\"btnCancel()\" class=\"btn btn-public btn-secondary btn-cancel\">取消</button>")
}
function btnCancel() {
    $(".public-dialog").removeClass("bounceInDown").addClass("bounceOutDown");
    //setTimeout(function () {
    $(".public-popups").hide();
    //}, 1000);
}
function CancelAppointmentOnClick(href) {
    window.location.href = href;
}

function confirmAppointment(ClinicLabelId, ClinicDate, NoonId, NoonText, HospitalGuid) {
    if (HB.Security.AutoLogined()) {
        window.location.href = "ConfirmAppoint?ClinicLabelId=" + ClinicLabelId + "&ClinicDate=" + ClinicDate + "&NoonId=" + NoonId + "&NoonText=" + NoonText + "&HospitalGuid=" + HospitalGuid;
    }
}

//收藏科室
function collectDept() {
    if (HB.Security.AutoLogined()) {
        var request = {
            TargetId: HB.QueryString("DoctorId"),//"d8efbe0f-c33b-49a5-8e79-232a87426908",
            FollowType: "2"
        };
        HB.Query("follow/follow", request, function (ar, response) {
            if (ar.AjaxStatus) {
                if (HB.IsSucess(response)) {
                    //HB.MessageSucess("收藏成功");
                    $("#deptCollect1").addClass("show").removeClass("hidden");
                    $("#deptCollect2").addClass("hidden").removeClass("show");
                }
                else {
                    HB.MessageError("您还没有登录，无法使用该功能");
                }
            }
            else {
                HB.MessageError("您还没有登录，无法使用该功能");
            }
        });
    }

}
//取消收藏
function cancelCollectDept() {
    var request = {
        TargetId: HB.QueryString("DoctorId"),//"d8efbe0f-c33b-49a5-8e79-232a87426908",
        FollowType: "2"
    };
    HB.Query("follow/follow", request, function (ar, response) {
        if (ar.AjaxStatus) {
            if (HB.IsSucess(response)) {
                //HB.MessageSucess("取消收藏成功");
                $("#deptCollect2").addClass("show").removeClass("hidden");
                $("#deptCollect1").addClass("hidden").removeClass("show");
            }
            else {
                HB.MessageError(response.Message);
            }
        }
        else {
            HB.MessageError(response);
        }
    });
}

function DateDiff(sDate1, sDate2) {    //sDate1和sDate2是2006-12-18格式
    var aDate, oDate1, oDate2, iDays;
    aDate = sDate1.split("-");
    oDate1 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0])   //兼容火狐 必须将日期格式转化为2016/12/18.
    aDate = sDate2.split("-");
    oDate2 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0])
    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24)    //把相差的毫秒数转换为天数
    return iDays;
}
function dateCount(arg, addDay) {
    var date1 = arg;
    var date2 = new Date(date1);
    date2.setDate(date1.getDate() + parseInt(addDay));
    var times = date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDate();
    return (times);
}
Date.prototype.format = function () {
    var s = '';
    s += this.getFullYear() + '-'; // 获取年份。
    s += (this.getMonth() + 1) + "-"; // 获取月份。
    s += this.getDate(); // 获取日。
    return (s); // 返回日期。
};
function getAll(begin, end) {
    var ab = begin.split("-");
    var ae = end.split("-");
    var db = new Date();
    db.setUTCFullYear(ab[0], ab[1] - 1, ab[2]);
    var de = new Date();
    de.setUTCFullYear(ae[0], ae[1] - 1, ae[2]);
    var unixDb = db.getTime();
    var unixDe = de.getTime();
    var str = "";
    for (var k = unixDb + 24 * 60 * 60 * 1000; k < unixDe;) {
        str += (new Date(parseInt(k))).format() + ",";
        k = k + 24 * 60 * 60 * 1000;
    }
    return str;
}
//获取当前日期
function getNowFormatDate(obj) {
    var day = obj;
    var Year = 0;
    var Month = 0;
    var Day = 0;
    var CurrentDate = "";
    //初始化时间
    //Year       = day.getYear();//有火狐下2008年显示108的bug
    Year = day.getFullYear(); //ie火狐下都可以
    Month = day.getMonth() + 1;
    Day = day.getDate();
    CurrentDate += Year + "-";
    if (Month >= 10) {
        CurrentDate += Month + "-";
    } else {
        CurrentDate += "0" + Month + "-";
    }
    if (Day >= 10) {
        CurrentDate += Day;
    } else {
        CurrentDate += "0" + Day;
    }

    return CurrentDate;
}

//获取的时间就会补零


function ab() {
    $.schedule = (function ($, undefined) {
        return function ($parent) {
            $parent = $($parent);
            var $inner = $parent.find('.right .inner'),
                $outer = $parent.find('.right .outer'),
                $outerLi = $parent.find('.right .outer>li'),
                $prevButton = $parent.find('.prev'),
                $nextButton = $parent.find('.next');
            var width = 784,
                height = 310;
            $outer.css({
                width: width + 'px',
                height: height + 'px'
            });
            var scheduleObj = (function (width) {
                var index = 0, length = $outerLi.length;
                return {
                    render: function () {
                        $outerLi.each(function (i) {

                            var browser = navigator.appName;
                            var b_version = navigator.appVersion;
                            var userAgent = navigator.userAgent; //ȡ���������userAgent�ַ�
                            var isOpera = userAgent.indexOf("Opera") > -1; //�ж��Ƿ�Opera�����
                            var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //�ж��Ƿ�IE�����
                            var isFF = userAgent.indexOf("Firefox") > -1; //�ж��Ƿ�Firefox�����
                            var isSafari = userAgent.indexOf("Safari") > -1; //�ж��Ƿ�Safari�����
                            var version = b_version.split(";");
                            if (isIE) {
                                var trim_Version = version[1].replace(/[ ]/g, "");
                                if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE6.0") {
                                    var browser = navigator.appName
                                    var b_version = navigator.appVersion
                                    var version = b_version.split(";");
                                    var trim_Version = version[1].replace(/[ ]/g, "");
                                    if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE6.0") {
                                        $(this).animate({ left: '' + width * (i - index) + 'px' });
                                    }
                                }
                                else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE7.0") {
                                    $(this).animate({ left: '' + width * (i - index) + 'px' }, 300);
                                }
                                else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE8.0") {
                                    $(this).animate({ left: '' + width * (i - index) + 'px' }, 300);
                                }
                                else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE9.0") {
                                    $(this).animate({ left: '' + width * (i - index) + 'px' }, 300);
                                }
                            }
                            else {
                                $(this).css('transform', 'translateX(' + width * (i - index) + 'px)');//css3 transfrom����
                            }
                            //$(this).css('left',''+width*(i-index)+'px');�������left����
                            //$(this).animate({left:''+width*(i-index)+'px'});jquery animate����

                        });
                        if (index == 0) {
                            $prevButton.addClass('disable');
                        } else {
                            $prevButton.removeClass('disable');
                        }
                        if (index == length - 1) {
                            $nextButton.addClass('disable');
                        } else {
                            $nextButton.removeClass('disable');
                        }
                    },
                    next: function () {
                        index += 1;
                        if (index > length - 1) {
                            index = length - 1;
                        }
                        this.render();
                    },
                    prev: function () {
                        index -= 1;
                        if (index < 0) {
                            index = 0;
                        }
                        this.render();
                    }
                }
            })(width);
            scheduleObj.render();
            setTimeout(function () {
                $outer.addClass('done');
            }, 0);
            $parent.addClass('done');
            $prevButton.click(function () {
                scheduleObj.prev();
            });
            $nextButton.click(function () {
                scheduleObj.next();
            });
        }
    })(jQuery);
}
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function getEvent() //同时兼容ie和ff的写法
{
    if (document.all) return window.event;
    func = getEvent.caller;
    while (func != null) {
        var arg0 = func.arguments[0];
        if (arg0) {
            if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
                return arg0;
            }
        }
        func = func.caller;
    }
    return null;
}
function getMyDay(date) {
    var week;
    if (date.getDay() == 0) week = "周日";
    if (date.getDay() == 1) week = "周一";
    if (date.getDay() == 2) week = "周二";
    if (date.getDay() == 3) week = "周三";
    if (date.getDay() == 4) week = "周四";
    if (date.getDay() == 5) week = "周五";
    if (date.getDay() == 6) week = "周六";
    return week;
}

//处理医生头像图片
function HeadImageUrl(headImgUrlpram) {
    var headImgUrl = headImgUrlpram;
    if (headImgUrl == null || headImgUrl == "null" || headImgUrl == "") {
        headImgUrl = "/Content/images/default/docdefault.png";
    }
    if (headImgUrl.indexOf("http") == -1) {
        if (headImgUrl != "/Content/images/default/docdefault.png") {
            headImgUrl = $("#HbManageroot").val() + headImgUrl;
        }
    }
    return headImgUrl;
}