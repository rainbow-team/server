package com.rainbow.supervision.controller;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.service.SastindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 */
@RestController
@RequestMapping("sastind")
public class SastindController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SastindService sastindService;

    /**
     * 保存国防科工局信息
     *
     * @param sastind
     * @return
     */
    @PostMapping("/addSastind")
    public ResponseBo addSastind(@RequestBody SupervisionSastind sastind) {
        int result = sastindService.addSastind(sastind);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改国防科工局信息
     *
     * @param sastind
     * @return
     */
    @PostMapping("/modifySastind")
    public ResponseBo modifySastind(@RequestBody SupervisionSastind sastind) {
        int result = sastindService.modifySastind(sastind);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 删除国防科工局信息
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteSastindById")
    public ResponseBo deleteSastind(String id) {
        int result = sastindService.deleteByKey(id);
        if (result == 1) {
            return ResponseBo.ok("删除成功");
        } else {
            return ResponseBo.error("删除失败");
        }
    }

    @PostMapping("/getAllSastinds")
    public List<SupervisionSastind> selectAllSastind(){
        return sastindService.selectAll();
    }

    @PostMapping("/getSastindList")
    public ResponseBo getSastindList(@RequestBody Page page){
        return sastindService.getSastindList(page);
    }

    @GetMapping("/getSastindById")
    public ResponseBo getSastindById(String id){
        SupervisionSastind supervisionSastind = sastindService.selectByKey(id);
        return  ResponseBo.ok(supervisionSastind);
    }
}
