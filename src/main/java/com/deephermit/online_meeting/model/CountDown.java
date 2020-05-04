package com.deephermit.online_meeting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountDown {
    private String MeetingId;
    private String day;
    private String hour;
    private String minute;
    private String second;
}
