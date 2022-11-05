$(function() {
    var hdr = {
        left: '',
        center: 'prev, title,next',
        right: '',
    };

    $('#calendar').fullCalendar({
        height: 650,
        header: hdr,
        defaultView: 'month',
        editable: false,
        droppable: false,
        displayEventTime: false,
        dayClick: function(date, allDay) {
        },
        eventClick: function(info) {},
        select: function (start, end, allDay) {},
        events: function(start, end, timezone, callback){
            var events = [];
            var tglCurrent = $('#calendar').fullCalendar('getDate');

            console.log("test => ", tglCurrent.toISOString().split('T')[0])

            $.ajax({
                url: "/alarm/schedule-data?currentDate=" + tglCurrent.toISOString().split('T')[0],
                method: "GET"
            })
            .done(function (res) {
                console.log("res => ", res);

                if (res.data.length !== 0) {
                    res.data.forEach((o, index) => {
                        const alarmSchedule = o.alarmSchedule;
                        events.push({title:o.title, start: new Date(alarmSchedule.split('T')[0]), end: new Date(alarmSchedule.split('T')[0]), time: alarmSchedule.split('T')[1].split('.')[0], sendFlag: o.sendFlag});
                     })
                }
                callback(events);
            })
            .fail(function (xhr, status, errorThrown) {
                console.log("failed res => ", xhr);
                alert("알람 일정 조회에 실패했습니다.");
            })
        },
        eventRender: function (event, element, icon) {
            if (event.sendFlag) {
            element.append("[o]").append(" <b> 알람시간: " + event.time + "</b>").find(".fc-title");
            } else {
               element.append("[x]").append(" <b> 알람시간: " + event.time + "</b>").find(".fc-title");
            }
        },
        windowResize: function (event, ui) {
            $('#calendar').fullCalendar('render');
        }
    });
});
