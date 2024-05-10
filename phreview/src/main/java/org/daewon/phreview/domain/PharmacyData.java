package org.daewon.phreview.domain;

import java.util.List;

public class PharmacyData {
    private DESCRIPTION DESCRIPTION;
    private List<PharmacyInfo> DATA;
}

class DESCRIPTION {
    private String phId;
    private String phName;
    private String phTel;
    private String phAdd;
    private String timeWeekStart;
    private String timeWeekEnd;
    private String timeSatStart;
    private String timeSatEnd;
    private String timeHoliStart;
    private String timeHoliEnd;
    private String phX;
    private String phY;
}

class PharmacyInfo {
    private String phTel;
    private String dutytime2c;
    private String timeHoliEnd;
    private String timeSatEnd;
    private String dutytime4c;
    private String wgs84lat;
    private String timeWeekStart;
    private String wgs84lon;
    private String dutytime5s;
    private String timeHoliStart;
    private String postcdn2;
    private String postcdn1;
    private String dutytime3s;
    private String dutytime3c;
    private String timeWeekEnd;
    private String dutytime7s;
    private String phName;
    private String phId;
    private String dutytime5c;
    private String phAdd;
    private String dutytime7c;
    private String dutytime2s;
    private long work_dttm;
    private String timeSatStart;
    private String dutytime4s;
}