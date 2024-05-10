package org.daewon.phreview.repository;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.PharmacyData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Log4j2
public class PharmacyRepositoryTests {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Value("${org.daewon.json.path}")
    private String jsonData;

    // insert
    @Test
    public void testInsert() throws FileNotFoundException {

        JsonElement jsonElement = JsonParser.parseReader(new FileReader(jsonData));
        // Gson 객체 생성
        Gson gson = new Gson();
        // JSON을 Java 객체로 변환
        PharmacyData objects = gson.fromJson(jsonElement, PharmacyData.class);

        // .phName("100세건강약국")
        // .phTel("02-445-1460")
        // .phAdd("서울특별시 강남구 헌릉로571길 7, 강남레체 1층 101호 (세곡동)")
        // // .timeWeekStart(LocalTime.of(9, 0))
        // // .timeWeekStart(Time.valueOf(LocalTime.of(9,00)))
        // .timeWeekStart()
        // .timeWeekEnd(Time.valueOf(LocalTime.of(20, 00)))
        // .timeSatStart(Time.valueOf(LocalTime.of(9, 00)))
        // .timeSatEnd(Time.valueOf(LocalTime.of(19,00)))
        // .timeHoliStart(Time.valueOf(LocalTime.of(0,0)))
        // .timeHoliEnd(Time.valueOf(LocalTime.of(0, 0)))
        // .phX(37.4660448664795)
        // .phY(127.101366881801)
        // .build();
        // Pharmacy result = pharmacyRepository.save(pharmacy);
        // log.info("id :"+result.getPhID());
    }
}
