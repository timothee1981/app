package royalstacks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import royalstacks.app.model.fakeDataGenerator.RecordGenerator;

@Controller
public class RecordGeneratorController {

    @Autowired
    RecordGenerator recordGenerator;

    @GetMapping(value = "/generator")
    public ModelAndView generateDbRecords() {
        recordGenerator.generateAllRecords(100, 1, 50);
        return new ModelAndView("homepage");
    }


}
