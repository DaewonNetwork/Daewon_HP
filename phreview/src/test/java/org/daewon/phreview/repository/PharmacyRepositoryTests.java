package org.daewon.phreview.repository;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.dto.PharmacyDTO;
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
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class PharmacyRepositoryTests {
}
