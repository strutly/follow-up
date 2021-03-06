package com.tsingtec.follow.controller.mini;

import com.tsingtec.follow.config.jwt.JwtUtil;
import com.tsingtec.follow.entity.mini.Doctor;
import com.tsingtec.follow.entity.mini.Information;
import com.tsingtec.follow.entity.mini.ReviewPlan;
import com.tsingtec.follow.exception.DataResult;
import com.tsingtec.follow.service.mini.DoctorService;
import com.tsingtec.follow.service.mini.InformationService;
import com.tsingtec.follow.service.mini.ReviewPlanService;
import com.tsingtec.follow.utils.BeanMapper;
import com.tsingtec.follow.utils.HttpContextUtils;
import com.tsingtec.follow.vo.req.doctor.DoctorAddReqVO;
import com.tsingtec.follow.vo.resp.doctor.DoctorIndexRespVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author lj
 * @Date 2021/6/2 10:10
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "小程序接口")
@RequestMapping("/api/doctor")
public class DoctorMiniController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private ReviewPlanService reviewPlanService;

    @Resource(name="JwtUtil")
    private JwtUtil jwtUtil;


    @GetMapping("index")
    public DataResult index(){
        DoctorIndexRespVO doctorIndexRespVO = new DoctorIndexRespVO();
        String token = HttpContextUtils.getToken();
        Doctor doctor = doctorService.findByUid(jwtUtil.getClaim(token,"id"));
        List<Information> informations  = informationService.getByDid(doctor.getId());
        doctorIndexRespVO.setInformations(informations);
        List<ReviewPlan> reviewPlans = reviewPlanService.findByDid(doctor.getId());
        doctorIndexRespVO.setReviewPlans(reviewPlans);
        return DataResult.success(doctorIndexRespVO);
    }

    @GetMapping("info")
    public DataResult info(){
        String token = HttpContextUtils.getToken();
        Doctor doctor = doctorService.findByUid(jwtUtil.getClaim(token,"id"));
        return DataResult.success(BeanMapper.map(doctor,DoctorAddReqVO.class));
    }

    @PutMapping("info")
    private DataResult info(@RequestBody @Valid DoctorAddReqVO vo){
        String token = HttpContextUtils.getToken();
        Doctor doctor = doctorService.findByUid(jwtUtil.getClaim(token,"id"));
        BeanMapper.mapExcludeNull(vo,doctor);
        doctorService.save(doctor);
        return DataResult.success();
    }

}
