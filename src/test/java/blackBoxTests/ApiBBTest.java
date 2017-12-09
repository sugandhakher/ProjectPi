package blackBoxTests;


import api.Controllers.PiController;
import api.Services.AnalysisService;

import api.Services.IOService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.*;
import report.Report;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ApiBBTest {

    private IOService ioservice;
    private MockMvc mvc;
    @Autowired
    private PiController controller;

    @Before
    public void init(){
        controller = new PiController();
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void testIOService() throws IOException{
        String directory = System.getProperty("user.dir");
        directory += "/uploads/test/IO";
        File dic = new File(directory);
        FileUtils.cleanDirectory(dic);
        byte b = 100;
        byte b2 = 10;
        byte [] bytearray = {b,b2};
        MultipartFile file []= {new MockMultipartFile("test.py", "test.py", "text", bytearray)};

        ioservice.writeFiles(file,directory);
        String pathRead = System.getProperty("user.dir") + "/uploads/test/IO/test.py";
        File fileRead = new File(pathRead);
        assertEquals(fileRead.exists(),true);
    }

    @Test
    public void testAnalysisService() throws IOException{
        String syspath = System.getProperty("user.dir");
        String path1 = syspath + "/uploads/test/other";
        Report report = AnalysisService.analysis(path1,path1);
        assertEquals(report.hasPlagiarism(),true);
    }


    @Test
    public void getAnalysisTest() throws Exception {
       String id1 = "0392fd60-e361-49c2-864e-07991787053d";
       String id2 = "c1322ea6-ee8a-4475-8b8e-cbca0600d768";
        //ResponseEntity<Report> entity = new ResponseEntity<Report>( HttpStatus.OK);

        ResponseEntity<Report> entity = controller.runAnalysis(id1,id2);
        assertEquals(entity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void postFileTest() throws Exception {
        String syspath = System.getProperty("user.dir");
        String path = syspath + "/uploads/test/other";
        String fileName = "simple.py";
        File testfile = new File(path + "/" + fileName);
        InputStream targetStream = new FileInputStream(testfile);
        MultipartFile file []= {new MockMultipartFile("test.py", "test.py", "text", targetStream)};
        ResponseEntity<Map<String, String>> entity = controller.uploadFile(file,file);
        String id1 = entity.getBody().get("id1");
        String id2 = entity.getBody().get("id2");
        assertEquals(id1.isEmpty(),false);
        assertEquals(id2.isEmpty(),false);
        String pathToOrigin = syspath + "/uploads/python/origin";
        String pathToCompare = syspath + "/uploads/python/compare";
        File originFile = new File(pathToOrigin + "/" + id1 + "/" + fileName);
        File originCompare = new File(pathToCompare + "/" + id2 + "/" + fileName);
        assertEquals(originFile.getName(),fileName);
        assertEquals(originFile.getName(),fileName);
        targetStream.close();
    }

}
