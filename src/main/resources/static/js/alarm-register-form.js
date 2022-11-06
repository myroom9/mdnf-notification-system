document.addEventListener('DOMContentLoaded', function () {
    jQuery('#rotateSendTime').timepicker({});
});

function notificationWaySelectEvent() {
    const selectedValue = $("#notificationWay option:selected").val();
    if (selectedValue === 'SINGLE') {
        $('#singleDiv').show();
        $('#rotateDiv').hide();
    } else {
        $('#singleDiv').hide();
        $('#rotateDiv').show();
    }
}

function registerAlarm() {

    var len = $("input[name='day_check_box']:checked").length;
    var checkArr = [];
    if(len > 1){ //개수를 체크하고 2개부터는 each함수를 통해 각각 가져온다.
        $("input[name='day_check_box']:checked").each(function(e){
            var value = $(this).val();
            checkArr.push(value);
        })
    }

    console.log(checkArr);

    if ($('#alarmTitle').val() === '') {
        alert("알람 제목을 입력해주세요.")
        return ;
    }

    /*if ($('#datetimepicker1Input').val() === '') {
        alert("알람 발송 시간을 입력해주세요. (달력 아이콘 클릭!)")
        return ;
    }*/

    const localTime = new Date(picker.viewDate.getTime() - (picker.viewDate.getTimezoneOffset() * 60000)).toISOString();

    let alarmRegisterData = $("form[name=alarm-register-form]").serializeArray();

    alarmRegisterData.push({name: 'checkedDays', value: checkArr});
    alarmRegisterData.push({name: 'singleAlarmTime', value: localTime});

    if ($("#notificationWay option:selected").val() === 'ROTATE') {
        const offset = 1000 * 60 * 60 * 9
        const amPm = $('#rotateSendTime').val().split(' ')[1];
        const userSetSendTime = $('#rotateSendTime').val().split(' ')[0];
        let setHours = userSetSendTime.split(':')[0];
        const setMins = userSetSendTime.split(':')[1];

        if (amPm === 'PM' && setHours !== '12') {
            setHours = parseInt(setHours) + 12;
        }

        if (amPm === 'AM' && setHours === '12') {
            setHours = '00';
        }

        let rotateAlarmTime = new Date(new Date().getTime() + offset).toISOString().split('T')[0] + " " + setHours + ":" + setMins;
        console.log('rotateAlarmTime11 => ', rotateAlarmTime);
        alarmRegisterData.push({name: 'rotateAlarmTime', value: new Date(new Date(rotateAlarmTime).getTime() + offset).toISOString()});
        // console.log("test => ", new Date(rotateAlarmTime + offset).toISOString())
    } else {
        alarmRegisterData.push({name: 'rotateAlarmTime', value: new Date().toISOString()});
    }

    $.ajax({
        url: "/alarm/register",
        data: alarmRegisterData,
        method: "POST"
    })
        // HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨.
        .done(function (res) {
            console.log("res => ", res);
            alert('알람 등록에 성공하였습니다.')
            location.href = "/alarm/schedule";
        })
        // HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.
        .fail(function (xhr, status, errorThrown) {
            console.log("failed res => ", xhr);
            alert('알람 등록에 실패하였습니다. 다시 시도해주세요.');
        })
        //
        .always(function (xhr, status) {

        });
}

const picker = new tempusDominus.TempusDominus(document.getElementById('datetimepicker1'), {
    display: {
        components: {
            seconds: false,
            useTwentyfourHour: false,
        },
        icons: {
            type: 'icons',
            time: 'fa fa-solid fa-clock',
            date: 'fa fa-solid fa-calendar',
            up: 'fa fa-solid fa-arrow-up',
            down: 'fa fa-solid fa-arrow-down',
            previous: 'fa fa-solid fa-chevron-left',
            next: 'fa fa-solid fa-chevron-right',
            today: 'fa fa-solid fa-calendar-check',
            clear: 'fa fa-solid fa-trash',
            close: 'fas fa-solid fa-xmark'
        },
    },
});