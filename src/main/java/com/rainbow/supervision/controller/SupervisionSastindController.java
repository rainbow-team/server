package com.rainbow.supervision.controller;

import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.service.SupervisionSastindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/5/11.
 */
@RestController("/sastind")
public class SupervisionSastindController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SupervisionSastindService sastindService;

    /**
     * 保存国防科工局信息
     * @param sastind
     * @return
     */
    @PostMapping("/add")
    public ResponseBo addSastind(@RequestBody SupervisionSastind sastind) {
        int result = sastindService.saveSastind(sastind);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    @PostMapping("/modify")
    public ResponseBo modifySastind(@RequestBody SupervisionSastind sastind) {
        int result = sastindService.modifySastind(sastind);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }
}
