package API.Controllers;

import API.Services.IOService;
import Analysis.TEDAnalysis;
import Loader.Loader;
import Loader.PythonLoader;
import Program.Program;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import Report.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * restfulAPI controller
 * */
@RestController
public class PiController {
    /**
     * file upload
     * */
    @RequestMapping(value = "api/upload", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String,String>> uploadFile(
            @RequestParam("uploadfile") MultipartFile [] uploadfile,
            @RequestParam("uploadfile2") MultipartFile [] uploadfile2,
            //@RequestBody MultiValueMap<String, File> requestBody,
            //HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        Map<String,String> m = new HashMap<String,String>();
        try {
//             Get the filename and build the local file path (be sure that the
//             application have write permissions on such directory)
            String ID1 = UUID.randomUUID().toString();
            String ID2 = UUID.randomUUID().toString();
            String directoryOrigin = System.getProperty("user.dir")
                    + "/uploads/python/origin/"
                    + ID1;
            String directoryCompare = System.getProperty("user.dir")
                    + "/uploads/python/compare/"
                    + ID2;


            IOService.writeFiles(uploadfile, directoryOrigin );
            IOService.writeFiles(uploadfile2, directoryCompare );

            //String redirectURL = "/#!/analysis/" + ID1 + "/" + ID2;
            m.put("id1",ID1);
            m.put("id2",ID2);
            return new ResponseEntity<Map<String,String>>(m, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Map<String,String>>(m, HttpStatus.NO_CONTENT);
            //response.sendRedirect("/#!/error");
        }


    }


    /**
     * analysis
     * */
    @RequestMapping(value = "api/analysis/{projectid1}/{projectid2}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Report> runAnalysis(
            @PathVariable("projectid1") String projectId1,
            @PathVariable("projectid2") String projectId2,
            HttpServletResponse response) throws IOException {
        String directory = "uploads/python";

        String projectPath1 = directory + "/origin/" +  projectId1;
        String projectPath2 = directory + "/compare/" +  projectId2;

        Report report = IOService.analysis(projectPath1,projectPath2);
        return new ResponseEntity<Report>(report, HttpStatus.OK);


    }

}